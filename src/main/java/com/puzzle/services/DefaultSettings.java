package com.puzzle.services;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DefaultSettings {
  public static String DEFAULT_IMG= "/Users/dmytrosenyk/Desktop/roadToJunior/testTaskPuzzle/src/main/resources/f1.jpg";

  public static int getHeight(BufferedImage img, int newWidth) {

    double ratio = newWidth / (double) img.getWidth();
    return (int) (img.getHeight() * ratio);
  }

  public static BufferedImage getBufferedImageFromImage(Image image){

    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_RGB);

    Graphics g = bufferedImage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();

    return bufferedImage;
  }


}
