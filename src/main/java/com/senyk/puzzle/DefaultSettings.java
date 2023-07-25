package com.senyk.puzzle;

import java.awt.image.BufferedImage;

public class DefaultSettings {
  public static String DEFAULT_IMG= "/Users/dmytrosenyk/Desktop/roadToJunior/testTaskPuzzle/src/main/resources/f1.jpg";

  public static int getHeight(BufferedImage img, int newWidth) {

    double ratio = newWidth / (double) img.getWidth();
    return (int) (img.getHeight() * ratio);
  }
}
