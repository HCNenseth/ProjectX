package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.config.Config;
import main.localization.Loc;
import main.preference.Preferences;
import main.view.Resources;
import main.view.menubar.MenuBar;

/**
 * Created by alex on 4/22/15.
 */
public class App extends Application
{
    private BorderPane bp;
    private MenuBar menuBar;
    private Pane sidePane;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // MUST BE FIRST!
        init();

        bp = new BorderPane();
        menuBar = new MenuBar();
        sidePane = new Pane();

        bp.setTop(menuBar);
        bp.setCenter(Resources.inst.getOtp());
        bp.setBottom(Resources.inst.getInfoBar().getMain());
        bp.setLeft(sidePane);

        /*
        Form f = new Form();
        f.injectAdapter(new SearchAdapter());
        Resources.inst.getOtp().injectObservableTab(Loc.get("search"),
                f.getForm(), true);
        */

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
        if (Preferences.inst.has("language")) {
            Loc.setActiveLang(Preferences.inst.get("language"));
        }

        if (Preferences.inst.has("last_used_file")) {
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
