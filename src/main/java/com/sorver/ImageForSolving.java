package com.sorver;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ImageForSolving {
  private int[] TOP;
  private int[] LEFT;
  private int[] RIGHT;
  private int[] BOTTOM;
  private BufferedImage image;

  public ImageForSolving(BufferedImage image) {

    this.image = image;
    int[][] pixels =Pixel.pixelArray(image);
    this.TOP=setTop(pixels);
    this.BOTTOM=setBottom(pixels);
    this.LEFT=setLeft(pixels);
    this.RIGHT=setRight(pixels);
  }

  private int[] setTop(int[][] arr){

    return arr[0];
  }
  private int[] setBottom(int[][] arr){

    return arr[arr.length-1];
  }
  private int[] setLeft(int[][] arr){

    return Arrays.stream(arr)
        .mapToInt(row -> row[0])
        .toArray();
  }
  private int[] setRight(int[][] arr){

    return Arrays.stream(arr)
        .mapToInt(row -> row[row.length - 1])
        .toArray();
  }

  public int[] getTOP() {

    return TOP;
  }

  public int[] getLEFT() {

    return LEFT;
  }

  public int[] getRIGHT() {

    return RIGHT;
  }

  public int[] getBOTTOM() {

    return BOTTOM;
  }

  public BufferedImage getImage() {

    return image;
  }
}
