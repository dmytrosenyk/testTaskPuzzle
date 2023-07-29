package com.puzzle.window;

import com.puzzle.elements.Puzzle;
import com.puzzle.elements.PuzzleFactory;
import com.sorver.Solver;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class SolvingWindow extends JFrame {

  private final JPanel panel = new JPanel();
  private final int rows;
  private final int columns;

  private final int width;
  private final int height;

  List<BufferedImage> images;
  private List<Puzzle> puzzles;

  public SolvingWindow(List<BufferedImage> images, int columns, int rows, int height, int width) {

    super("Solver");
    this.images=images;
    this.rows = rows;
    this.columns = columns;
    this.width = width;

    this.height = height;
    initUI();

    setVisible(true);
  }

  private void initUI() {

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    panel.setBackground(Color.CYAN);
    setSize((int) (width*1.05), (int) (height*1.15));
    makeTable(panel);
    add(panel);
    setLocationRelativeTo(null);
    setResizable(false);
  }

  private void makeTable(JPanel panel) {

    try {
      puzzles = PuzzleFactory.createPuzzles(
          Solver.solver(images, rows, columns),
           (width)/columns,
           (height)/rows);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (Puzzle puzzle : puzzles) {
      panel.add(puzzle);
    }
    panel.setLayout(new GridLayout(rows, columns));
  }
}
