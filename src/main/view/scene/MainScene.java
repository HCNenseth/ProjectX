package main.view.scene;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import main.config.Config;
import main.view.Resources;
import main.view.menubar.MainMenuBar;

/**
 * MainScene.java
 */
public class MainScene
{
    private Scene scene;
    private BorderPane bp;

    /**
     * Instantiates a new Main scene.
     */
    public MainScene()
    {
        bp = new BorderPane();

        bp.setTop(new MainMenuBar());
        bp.setCenter(Resources.inst.getStackPane());
        bp.setBottom(Resources.inst.getInfoBar().getMain());

        scene = new Scene(bp, Config.WIDTH, Config.HEIGHT);
        scene.getStylesheets().add("file:resources/css/main.css");
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
