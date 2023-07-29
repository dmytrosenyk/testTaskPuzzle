package com.sorver;

import java.util.stream.IntStream;

public class ImageCompatibility {

  public static int countCompatibilityOfImages(int[] arr1, int[] arr2){

    if (arr1.length != arr2.length)
      return 0;
    return (int) IntStream.range(0, arr1.length)
        .filter(i -> arr1[i] == arr2[i])
        .count();
  }
}
