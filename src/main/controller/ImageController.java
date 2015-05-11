package main.controller;

import main.config.Config;

import java.io.*;

/**
 * ImageController.java
 */
public class ImageController
{
    private static String getFileExtension(String fileName)
    {
        if (fileName.lastIndexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        return "";
    }

    public static String storeImage(File file, String newFileName)
    {
        if (file == null) { return null; }

        String output = "";

        File dest = new File(String.format("%s%s.%s",
                Config.UPLOADS,
                newFileName,
                getFileExtension(file.getName())));


        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = new FileOutputStream(dest))
        {

            byte[] buffer = new byte[Config.MAX_UPLOAD_FILESIZE];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            output = dest.getName();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
