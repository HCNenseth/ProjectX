package main.controller;

import main.config.Config;

import java.io.*;

/**
 * Created by HansPetter on 07.05.2015.
 */
public class ImageController
{
    private static String getFileExtention(String fileName)
    {
        if (fileName.lastIndexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        return "";
    }

    public static void storeImage(File file, String newFileName)
    {
        if (file == null) { return; }

        File dest = new File(String.format("%s%s.%s",
                Config.UPLOADS,
                newFileName,
                getFileExtention(file.getName())));


        InputStream inputStream;
        OutputStream outputStream;

        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(dest);

            byte[] buffer = new byte[4096];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
