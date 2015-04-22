package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.view.menubar.MenuBar;

/**
 * Created by alex on 4/22/15.
 */
public class App extends Application
{
    private BorderPane bp = new BorderPane();
    private MenuBar menuBar = new MenuBar();
    private final int HEIGHT = 600;
    private final int WIDTH = 800;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        bp.setTop(menuBar);

        Scene scene = new Scene(bp, WIDTH, HEIGHT);

        primaryStage.setTitle("Appname be here");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
