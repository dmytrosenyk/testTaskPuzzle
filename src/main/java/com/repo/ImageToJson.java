package com.repo;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ImageToJson {

  public static void imageListToJson(List<ImagePOJO> imagePOJO, String fileName) throws IOException {
    Gson gson = new Gson();
    gson.toJson(imagePOJO, new FileWriter(fileName));
  }
}
