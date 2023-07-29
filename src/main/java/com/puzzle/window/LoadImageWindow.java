package com.puzzle.window;

import com.puzzle.services.DefaultSettings;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class LoadImageWindow extends JFrame{
  int rows, columns;

  public LoadImageWindow(String title,int rows,int columns) {
    super(title);
    this.columns=columns;
    this.rows=rows;
  }

  private void initUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 400);
    setLocationRelativeTo(null);

    createUI();
    setVisible(true);

  }

  private void createUI(){
    JPanel panel = new JPanel();
    LayoutManager layout = new FlowLayout();
    panel.setLayout(layout);

    JButton buttonChoosingIMG = new JButton("choose own img");
    JButton buttonStart = new JButton("start");
    final JLabel label = new JLabel();
    panel.add(buttonChoosingIMG);
    panel.add(buttonStart);

    String[] imgPath = {DefaultSettings.DEFAULT_IMG};
    label.setText(imgPath[0]);
    panel.add(label);

    buttonChoosingIMG.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.addChoosableFileFilter(new ImageFilter());
      fileChooser.setAcceptAllFileFilterUsed(false);

      int option = fileChooser.showOpenDialog(this);
      if(option == JFileChooser.APPROVE_OPTION){
        File file = fileChooser.getSelectedFile();
        label.setText("File Selected: " + file.getAbsolutePath());
        imgPath[0] = file.getAbsolutePath();
      }else{
        label.setText(imgPath[0]);
      }
    });
    buttonStart.addActionListener(e -> new PuzzleWindow(imgPath[0],rows,columns));


    getContentPane().add(panel, BorderLayout.CENTER);
  }

  static class ImageFilter extends FileFilter {
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";

    @Override
    public boolean accept(File f) {
      if (f.isDirectory()) {
        return true;
      }

      String extension = getExtension(f);
      if (extension != null) {
        return extension.equals(JPEG) || extension.equals(JPG);
      }
      return false;
    }

    @Override
    public String getDescription() {
      return "Image Only";
    }

    String getExtension(File f) {
      String ext = null;
      String s = f.getName();
      int i = s.lastIndexOf('.');

      if (i > 0 &&  i < s.length() - 1) {
        ext = s.substring(i+1).toLowerCase();
      }
      return ext;
    }
  }

  public static void test(int rows, int columns) {
    LoadImageWindow loadImageWindow = new LoadImageWindow("img",rows,columns);
    loadImageWindow.initUI();
  }
}

