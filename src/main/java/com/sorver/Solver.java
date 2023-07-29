package com.sorver;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {

  public static List<BufferedImage> solver(List<BufferedImage> bufferedImages, int rows, int columns) {

    LinkedList<LinkedList<Image>> puzzles =new LinkedList<>();
    LinkedList<Image> images = bufferedImages.stream()
        .map(Image::new)
        .collect(Collectors.toCollection(LinkedList::new));

    for (int i = 0; i < rows; i++)
      puzzles.add(PuzzleBuilder.buildRow(images,columns));

    List<Image> result = PuzzleBuilder.buildPuzzle(puzzles, rows)
        .stream()
        .flatMap(LinkedList::stream)
        .collect(Collectors.toCollection(LinkedList::new));

    return result.stream()
        .map(Image::getImage)
        .toList();
  }
}
