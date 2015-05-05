package main;

import javafx.application.Application;
import javafx.application.Platform;
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
import main.view.SceneSwitch;
import main.view.menubar.MenuBar;

import java.io.IOException;

/**
 * Created by alex on 4/22/15.
 */
public class App extends Application
{
    private boolean dialogMode = true;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Resources.inst.getSceneSwitch().setStage(primaryStage);

        if (dialogMode) {
            Resources.inst.getSceneSwitch().setProjectDialogWindow();
        } else {
            Resources.inst.getSceneSwitch().setMainWindow();
        }
    }

    /**
     * Init method kicked of by Application (JavaFX)
     */
    @Override
    public void init()
    {
        if (Pref.inst.has("language")) {
            Loc.setActiveLang(Pref.inst.get("language"));
        }

        if (Pref.inst.has("last_used_file")) {
            Storage.injectFilename(Pref.inst.get("last_used_file"));
            try {
                Storage.getInstance().read();
                dialogMode = false;
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
