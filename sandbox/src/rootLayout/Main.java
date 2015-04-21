package it.osvold.Contacts;

import it.osvold.Contacts.controller.RootLayoutController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Hans Petter on 21.04.2015.
 */
public class Main extends Application {
    private Stage primaryStage;
    private RootLayoutController rootLayoutController;
    private Scene rootScene;
    private String appName = "";

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        initRootLayout();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the root layout.
     * With file menu.
     */
    public void initRootLayout()
    {
        // Ref to the Controller.
        rootLayoutController = new RootLayoutController();

        // Here lives the file menu.
        rootScene = rootLayoutController.getRootScene();

        updateWindow();
    }

    /**
     * It does not work for reloading the window :'(
     */
    public void updateWindow()
    {
        primaryStage.setScene(rootScene);
        primaryStage.setTitle(appName);
        primaryStage.show();
    }



}
