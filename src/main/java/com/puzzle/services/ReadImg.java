package com.puzzle.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ReadImg {
  public static BufferedImage readFile(String imgPath) throws IOException {

    return ImageIO.read(new File(imgPath));
  }
}
