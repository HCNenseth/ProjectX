package main.view.scene;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Created by alex on 5/11/15.
 */
public class ImageScene
{
    private String image;
    private Scene scene;

    public ImageScene(String image)
    {
        this.image = image;
        scene = new Scene(loadBrowser());
    }

    private WebView loadBrowser() {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("file://" + image);

        return browser;
    }

    public Scene getScene() { return scene; }
}
