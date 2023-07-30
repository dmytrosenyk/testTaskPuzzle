package com.puzzle.services;

import java.util.Random;

public class RandomName {

  public static String randName(){

    int leftLimit = 97;
    int rightLimit = 122;
    int targetStringLength = 6;
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
