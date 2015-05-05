package main.view.scene;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import main.config.Config;
import main.view.Resources;
import main.view.menubar.MenuBar;

/**
 * Created by alex on 5/5/15.
 */
public class MainScene
{
    private Scene scene;
    private BorderPane bp;

    public MainScene()
    {
        bp = new BorderPane();

        bp.setTop(new MenuBar());
        bp.setCenter(Resources.inst.getStackPane());
        bp.setBottom(Resources.inst.getInfoBar().getMain());

        scene = new Scene(bp, Config.WIDTH, Config.HEIGHT);
        scene.getStylesheets().add("file:resources/css/main.css");
    }

    public Scene getScene() { return scene; }
}
