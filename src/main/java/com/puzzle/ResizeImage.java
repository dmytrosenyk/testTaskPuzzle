package com.puzzle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ResizeImage {

  public static BufferedImage resize(BufferedImage src, int nW, int nH){

    BufferedImage resizedImage = new BufferedImage(nW, nH, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(src, 0, 0, nW, nH, null);
    g.dispose();
    return resizedImage;
  }
}
