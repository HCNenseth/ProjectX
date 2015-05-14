package main.view;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.config.Config;
import main.model.Storage;
import main.view.scene.MainScene;
import main.view.scene.ProjectDialogScene;

/**
 * SceneSwitch.java
 */
public class SceneSwitch
{
    private Stage primaryStage;

    public SceneSwitch() { }

    public void setStage(Stage stage)
    {
        primaryStage = stage;
        primaryStage.setTitle(Config.APP_NAME);
        primaryStage.getIcons()
                .add(new Image("file:resources/images/companyIcon.png"));
    }

    public void setMainWindow()
    {
        primaryStage.close();
        primaryStage.setScene(new MainScene().getScene());

        primaryStage.setTitle(String.format("%s - [%s]",
                Config.APP_NAME, Storage.getInstance().getFilename()));

        primaryStage.show();
    }

    public void setProjectDialogWindow()
    {
        primaryStage.close();
        primaryStage.setScene(new ProjectDialogScene().getScene());

        primaryStage.show();
    }

    public boolean hasStage() { return primaryStage == null; }
}
