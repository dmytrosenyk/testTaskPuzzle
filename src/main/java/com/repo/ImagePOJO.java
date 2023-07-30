package com.repo;

import java.awt.image.BufferedImage;

public class ImagePOJO {

  private Integer number;
  private String bufferedImage;

  public ImagePOJO(Integer number, String bufferedImage) {

    this.number = number;
    this.bufferedImage = bufferedImage;
  }

  public Integer getNumber() {

    return number;
  }

  public void setNumber(Integer number) {

    this.number = number;
  }

  public String getBufferedImage() {

    return bufferedImage;
  }

  public void setBufferedImage(String bufferedImage) {

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
