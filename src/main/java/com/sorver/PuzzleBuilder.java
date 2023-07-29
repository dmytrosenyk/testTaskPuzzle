package com.sorver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleBuilder {

  public static LinkedList<ImageForSolving> buildRow(LinkedList<ImageForSolving> images, int columns){

    LinkedList<ImageForSolving> row = new LinkedList<>();
    boolean wayOfBuildIsRight = true;
    PuzzleBuilder p = new PuzzleBuilder();
    while (columns>0) {
      if (row.isEmpty()){
        row.addFirst(images.get(0));
        images.remove(0);
      }
      else if (wayOfBuildIsRight){
        List<Integer> countCompatibility= new ArrayList<>();
        for (ImageForSolving image : images) {
          countCompatibility.add(ImageCompatibility.countCompatibilityOfImages(row.getLast().getRIGHT(), image.getLEFT()));
        }
        int maxPosition = p.getMaxPosition(countCompatibility);

        if (maxPosition == -1) {
          wayOfBuildIsRight = false;
          columns++;
        }
        else {
          row.addLast(images.get(maxPosition));
          images.remove(maxPosition);
        }
      }
      else {
        List<Integer> countCompatibility = new ArrayList<>();
        for (ImageForSolving image : images) {
          countCompatibility.add(ImageCompatibility.countCompatibilityOfImages(row.getFirst().getLEFT(), image.getRIGHT()));
        }
        int maxPosition = p.getMaxPosition(countCompatibility);
        if (maxPosition!=-1) {
          row.addFirst(images.get(maxPosition));
          images.remove(maxPosition);
        }
        else {
          row.addFirst(images.get(0));
          images.remove(0);

        }
      }
      columns--;
    }

    return row;
  }

  public static LinkedList<LinkedList<ImageForSolving>> buildPuzzle(LinkedList<LinkedList<ImageForSolving>> images, int rows){

    LinkedList<LinkedList<ImageForSolving>> puzzle = new LinkedList<>();
    boolean wayOfBuildIsBottom = true;
    PuzzleBuilder p = new PuzzleBuilder();
    while (rows>0) {
      if (puzzle.isEmpty()){
        puzzle.addFirst(images.get(0));
        images.remove(0);
      }
      else if (wayOfBuildIsBottom){
        List<Integer> countCompatibility = new ArrayList<>();
        for (LinkedList<ImageForSolving> image : images) {
          countCompatibility.add(ImageCompatibility.countCompatibilityOfImages(
              puzzle.getLast()
                  .stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getBOTTOM()))
                  .toArray(),
              image.stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getTOP()))
                  .toArray()));
        }
        int maxPosition = p.getMaxPosition(countCompatibility);

        if (maxPosition == -1) {
          wayOfBuildIsBottom = false;
          rows++;
        }
        else {
          puzzle.addLast(images.get(maxPosition));
          images.remove(puzzle.getLast());
        }
      }
      else {
        List<Integer> countCompatibility = new ArrayList<>();
        for (LinkedList<ImageForSolving> image : images) {
          countCompatibility.add(ImageCompatibility.countCompatibilityOfImages(
              puzzle.getFirst()
                  .stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getTOP()))
                  .toArray(),
              image.stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getBOTTOM()))
                  .toArray()));
        }
        int maxPosition = p.getMaxPosition(countCompatibility);
        if (maxPosition!=-1) {
          puzzle.addFirst(images.get(maxPosition));
          images.remove(puzzle.getFirst());
        }
        else{
          puzzle.addFirst(images.get(0));
          images.remove(0);
        }
      }
      rows--;
    }

    return puzzle;
  }

  private int getMaxPosition(List<Integer> counts){

    int maxCount = 0;
    int maxPosition = -1;

    for (int i = 0; i < counts.size(); i++) {
      int currentPercent = counts.get(i);
      if (currentPercent > maxCount) {
        maxCount = currentPercent;
        maxPosition = i;
      }
    }

    return maxPosition;
  }
}
