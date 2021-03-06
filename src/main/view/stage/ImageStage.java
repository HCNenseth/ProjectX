package main.view.stage;

import javafx.stage.Stage;
import main.view.scene.ImageScene;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImageStage.java
 */
public class ImageStage extends Stage
{
    /**
     * Instantiates a new Image stage.
     */
    public ImageStage()
    {
    }

    /**
     * Show image.
     *
     * @param image the image
     */
    public void showImage(File image)
    {
        setScene(new ImageScene(image.getAbsolutePath()).getScene());
        setTitle(image.getName());

        try {
            BufferedImage bi = ImageIO.read(image);
            setMaxHeight(bi.getHeight());
            setMinHeight(bi.getHeight());
            setMaxWidth(bi.getWidth());
            setMinWidth(bi.getWidth());
        } catch (IOException ioe) {
            System.out.println("Could not convert file to image");
        }
        show();
    }
}
