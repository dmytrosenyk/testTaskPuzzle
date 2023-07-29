package com.puzzle.window;

import com.puzzle.services.DefaultSettings;
import com.puzzle.action_listener.DragListener;
import com.puzzle.action_listener.DropListener;
import com.puzzle.elements.Puzzle;
import com.puzzle.elements.PuzzleFactory;
import com.puzzle.services.ReadImg;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class PuzzleWindow extends JFrame {
  private List<Puzzle> puzzles;

  private List<JPanel> solutions;
  private List<BufferedImage> imgForSolver;
  private final BufferedImage img;
  private final JPanel leftPanel = new JPanel();
  private final JPanel rightPanel = new JPanel();
  private final int rows;
  private final int columns;

  private final int width=1200;
  private final int height;

  //4<=rows(columns)<=25
  public PuzzleWindow(String imgPath, int rows, int columns){

    super("SplitPane example");
    try {
      this.img = ReadImg.readFile(imgPath);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }

    this.rows=rows;
    this.columns=columns;
    this.height = (int) (DefaultSettings.getHeight(img,width)/2*1.15);
    initUI();
    setVisible(true);
  }

  private void initUI(){
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


    leftPanel.setBackground(new Color(250, 166, 166));
    rightPanel.setBackground(Color.lightGray);
    setSize(width,height+40);
    JButton solveButton= new JButton("solve");
    solveButton.setPreferredSize(new Dimension(width,40));
    add(solveButton, BorderLayout.NORTH);
    solveButton.addActionListener(e -> new SolvingWindow(imgForSolver,columns,rows,height,width/2));

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, leftPanel, rightPanel);
    splitPane.setDividerLocation(getWidth() / 2);
    makeLeftTable(leftPanel);
    makeRightTable(rightPanel);
    setLocationRelativeTo(null);
    add(splitPane);
    setResizable(false);
  }
  private void makeLeftTable(JPanel panel){
    DragListener dlistener = new DragListener();
    DragSource ds = new DragSource();
    try {
      puzzles= PuzzleFactory.createPuzzlesFromImage(img,rows,columns, (int) ((width*0.95)/2));
      Collections.shuffle(puzzles);
      imgForSolver= puzzles.stream().map(Puzzle::getNoResizedImage).collect(Collectors.toList());
      puzzles.forEach(x->x.setNoResizedImage(null));
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (Puzzle puzzle : puzzles) {
      panel.add(puzzle);

      ds.createDefaultDragGestureRecognizer(puzzle, DnDConstants.ACTION_COPY, dlistener);
    }
    panel.setLayout(new GridLayout(rows,columns));
  }
  private void makeRightTable(JPanel panel){
    panel.setLayout(new GridLayout(rows,columns));

    solutions=new ArrayList<>();
    for (int i = 0; i < rows * columns; i++) {
      JPanel p = new JPanel();
      p.setBorder(BorderFactory.createLineBorder(Color.lightGray));
      solutions.add(p);

    }
    for (JPanel p : solutions) {
      panel.add(p);
      TransferHandler dnd = new TransferHandler() {
        private static final DataFlavor puzzleFlavor = new DataFlavor(Puzzle.class, "Puzzle");

        @Override
        public boolean canImport(TransferSupport support) {
          if (!support.isDrop()) {
            return false;
          }
          return support.isDataFlavorSupported(puzzleFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
          if (!canImport(support)) {
            return false;
          }

          Transferable tansferable = support.getTransferable();
          Puzzle ico;
          try {
            ico = (Puzzle) tansferable.getTransferData(puzzleFlavor);
          } catch (Exception e) {
            e.printStackTrace();
            return false;
          }
          p.add(ico);
          return true;
        }
      };
      p.setTransferHandler(dnd);
      new DropListener(p,solutions);

    }
  }
}