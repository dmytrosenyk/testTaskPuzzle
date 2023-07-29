package com.puzzle.elements;

import static com.puzzle.services.DefaultSettings.getBufferedImageFromImage;
import static com.puzzle.services.DefaultSettings.getHeight;

import com.puzzle.services.DivisionImage;
import com.puzzle.services.ResizeImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PuzzleFactory {
  public static List<Puzzle> createPuzzlesFromImage(BufferedImage img, int rows, int columns, int new_width) throws IOException {

    List<Image> divisionNoResizedImageIntoParts = DivisionImage.divisionImageIntoParts(img,rows,columns);
    List<Puzzle> puzzles=new ArrayList<>();
    int new_width_of_cell=new_width/columns;
    int n=0;
    for (Image i :
        divisionNoResizedImageIntoParts) {
      Puzzle puzzle = new Puzzle(
          new ImageIcon(
              ResizeImage.resize(
                  getBufferedImageFromImage(i),
                  new_width_of_cell,
                  getHeight(getBufferedImageFromImage(i), new_width_of_cell))),
          n);
      puzzle.setNoResizedImage(getBufferedImageFromImage(i));
      puzzles.add(puzzle);
      n++;
    }

    return puzzles;
  }

  public static List<Puzzle> createPuzzles(List<BufferedImage> images,int w, int h) throws IOException {

    List<Puzzle> puzzles=new ArrayList<>();

    for (Image i :
        images) {
      Puzzle puzzle = new Puzzle(new ImageIcon(ResizeImage.resize(getBufferedImageFromImage(i),w,h)));
      puzzles.add(puzzle);
    }

    return puzzles;
  }

}
