package com.sorver;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixel {

  public static int[][] pixelArray(BufferedImage image){

    int width = image.getWidth();
    int height = image.getHeight();
    int[][] arr =new int[height][width];
    for(int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        Color c = new Color(image.getRGB(j, i));
        arr[i][j]=c.getRGB();
      }
    }

    return arr;
  }
}
