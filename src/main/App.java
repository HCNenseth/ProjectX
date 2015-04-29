package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.config.Config;
import main.localization.Loc;
import main.view.Resources;
import main.view.form.Form;
import main.view.form.adapter.SearchAdapter;
import main.view.menubar.MenuBar;

/**
 * Created by alex on 4/22/15.
 */
public class App extends Application
{
    private BorderPane bp = new BorderPane();
    private MenuBar menuBar = new MenuBar();
    private Pane sidePane = new Pane();

    public static final String PERSONS = "persons";
    public static final String INSURANCES = "insurances";
    public static final String CLAIMS = "claims";

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        bp.setTop(menuBar);
        bp.setCenter(Resources.inst.getOtp());
        bp.setLeft(sidePane);

        Form f = new Form();
        f.injectAdapter(new SearchAdapter());

        Resources.inst.getOtp().injectObservableTab(Loc.get("search"),
                f.getForm(), true);

        Scene scene = new Scene(bp, Config.WIDTH, Config.HEIGHT);
        scene.getStylesheets().add("file:resources/css/main.css");

        primaryStage.setTitle(Config.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.getIcons()
                .add(new Image("file:resources/images/glyphicons-41-stats.png"));

        /* compatibility code */
        primaryStage.setMaxHeight(Config.HEIGHT);
        primaryStage.setMinHeight(Config.HEIGHT);
        primaryStage.setMaxWidth(Config.WIDTH);
        primaryStage.setMinWidth(Config.WIDTH);

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
