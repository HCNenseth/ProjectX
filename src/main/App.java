package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.config.Config;
import main.localization.Loc;
import main.model.Storage;
import main.preference.Pref;
import main.view.Resources;
import main.view.menubar.MenuBar;

import java.io.IOException;

/**
 * Created by alex on 4/22/15.
 */
public class App extends Application
{
    private BorderPane bp;
    private Pane sidePane;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // MUST BE FIRST!
        init();

        bp = new BorderPane();
        sidePane = new Pane();

        bp.setTop(new MenuBar());
        bp.setCenter(Resources.inst.getStackPane());
        bp.setBottom(Resources.inst.getInfoBar().getMain());
        bp.setLeft(sidePane);

        Scene scene = new Scene(bp, Config.WIDTH, Config.HEIGHT);
        scene.getStylesheets().add("file:resources/css/main.css");

        primaryStage.setTitle(Config.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.getIcons()
                .add(new Image("file:resources/images/glyphicons-41-stats.png"));

        primaryStage.show();
    }

    public void init()
    {
        if (Pref.inst.has("language")) {
            Loc.setActiveLang(Pref.inst.get("language"));
        }

        if (Pref.inst.has("last_used_file")) {
            Storage.injectFilename(Pref.inst.get("last_used_file"));
            try {
                Storage.getInstance().read();
            } catch (IOException | ClassNotFoundException e) {
                // TODO do something meaningful with this error.
                System.out.println("error reading from file");
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
