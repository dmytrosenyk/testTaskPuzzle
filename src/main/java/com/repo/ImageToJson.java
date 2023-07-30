package com.repo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ImageToJson {

  public static void imageListToJson(List<ImagePOJO> imagePOJO, String fileName) throws IOException {
    Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .create();
    Writer writer = new FileWriter(fileName);
    gson.toJson(imagePOJO,writer);
    writer.flush();
    writer.close();
  }
}
