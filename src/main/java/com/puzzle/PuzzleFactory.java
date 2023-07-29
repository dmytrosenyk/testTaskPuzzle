package com.puzzle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PuzzleFactory {
  public static List<Puzzle> createPuzzles(BufferedImage img, int rows, int columns, int width) throws IOException {
    List<Image> divisionImageIntoParts = DivisionImage.divisionResizedImageIntoParts(img,rows,columns,width);
    List<Puzzle> puzzles=new ArrayList<>();
    int n=0;
    for (Image i :
        divisionImageIntoParts) {
      Puzzle puzzle = new Puzzle(new ImageIcon(i),n);
      puzzles.add(puzzle);
      n++;
    }
    return puzzles;
  }
}
