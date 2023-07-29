package com.puzzle.elements;

import java.awt.image.BufferedImage;
import javax.swing.*;

public class Puzzle extends JLabel {
  private int number;
  private BufferedImage noResizedImage;
  public Puzzle(Icon image,int number) {
    super(image);
    this.number=number;

  }
  public Puzzle(Icon image) {
    super(image);
  }
  public BufferedImage getNoResizedImage() {

    return noResizedImage;
  }

  public void setNoResizedImage(BufferedImage noResizedImage) {

    this.noResizedImage = noResizedImage;
  }

  public int getNumber() {

    return number;
  }

  public void setNumber(int number) {

    this.number = number;
  }
}
