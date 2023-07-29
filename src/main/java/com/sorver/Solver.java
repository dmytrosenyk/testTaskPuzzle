package com.sorver;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {

  public static List<BufferedImage> solver(List<BufferedImage> bufferedImages, int rows, int columns) {

    LinkedList<LinkedList<ImageForSolving>> puzzles =new LinkedList<>();
    LinkedList<ImageForSolving> images = bufferedImages.stream()
        .map(ImageForSolving::new)
        .collect(Collectors.toCollection(LinkedList::new));

    for (int i = 0; i < rows; i++)
      puzzles.add(PuzzleBuilder.buildRow(images,columns));

    List<ImageForSolving> result = PuzzleBuilder.buildPuzzle(puzzles, rows)
        .stream()
        .flatMap(LinkedList::stream)
        .collect(Collectors.toCollection(LinkedList::new));

    return result.stream()
        .map(ImageForSolving::getImage)
        .toList();
  }
}
