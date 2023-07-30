package com.repo;

import java.awt.image.BufferedImage;

public class ImagePOJO {

  private Integer number;
  private BufferedImage bufferedImage;

  public Integer getNumber() {

    return number;
  }

  public void setNumber(Integer number) {

    this.number = number;
  }

  public BufferedImage getBufferedImage() {

    return bufferedImage;
  }

  public void setBufferedImage(BufferedImage bufferedImage) {

    this.bufferedImage = bufferedImage;
  }

  @Override
  public String toString() {

    return "ImagePOJO{" +
        "number=" + number +
        ", bufferedImage=" + bufferedImage +
        '}';
  }
}
