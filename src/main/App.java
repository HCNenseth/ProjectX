package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
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
                f.getForm(), false);

        Scene scene = new Scene(bp, WIDTH, HEIGHT);
        scene.getStylesheets().add("file:resources/css/test.css");

        primaryStage.setTitle("Appname be here");
        primaryStage.setScene(scene);
        primaryStage.getIcons()
                .add(new Image("file:resources/images/glyphicons-41-stats.png"));

        /* compatibility code */
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinWidth(WIDTH);

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
