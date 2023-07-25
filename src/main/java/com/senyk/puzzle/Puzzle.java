package com.senyk.puzzle;

import javax.swing.*;

public class Puzzle extends JLabel {
  private int number;
  public Puzzle(Icon image,int number) {
    super(image);
    this.number=number;

  }

  public int getNumber() {

    return number;
  }

  public void setNumber(int number) {

    this.number = number;
  }
}
