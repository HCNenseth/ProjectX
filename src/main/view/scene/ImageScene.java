package main.view.scene;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * ImageScene.java
 */
public class ImageScene
{
    private String image;
    private Scene scene;

    /**
     * Instantiates a new Image scene.
     *
     * @param image the image
     */
    public ImageScene(String image)
    {
        this.image = image;
        scene = new Scene(loadBrowser());
    }

    private WebView loadBrowser()
    {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("file:" + image);

        return browser;
    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene getScene()
    {
        return scene;
    }
}
