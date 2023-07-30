package com.repo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class SubImagePackager {

    public static void writeListImgToFile(Map<Integer, BufferedImage> images, String name){

        try {
            for (Map.Entry<Integer, BufferedImage> entry : images.entrySet()) {
                String nameFile = "src/main/resources/sub_img/sub-"+name+"_"+entry.getKey()+".jpg";
                File outputfile = new File(nameFile);
                ImageIO.write(entry.getValue(), "jpg", outputfile);
            }
        }
        catch (IOException ignored) {
        }
    }

    public static List<BufferedImage> readListImg(String name){

        String nameFile = "src/main/resources/sub_img/sub-"+name+"_";
        List<BufferedImage> images=new ArrayList<>();
        int i=0;

        while (true){
            try {
                BufferedImage image= ReadImg.readFile(nameFile+i+".jpg");
                images.add(image);
                i++;
            }
            catch (IOException e) {
                return images;
            }
        }
    }
}
