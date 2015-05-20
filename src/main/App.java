package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.preference.Pref;
import main.view.Resources;

import java.io.IOException;

/**
 * App.java
 */
public class App extends Application
{
    private boolean dialogMode = true;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Resources.inst.getSceneSwitch().setStage(primaryStage);
        Resources.inst.injectHostServices(getHostServices());

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
                e.printStackTrace();
            }
        }

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
