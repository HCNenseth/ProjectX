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

    /**
     * Instantiates a new Scene switch.
     */
    public SceneSwitch()
    {
    }

    /**
     * Sets stage.
     *
     * @param stage the stage
     */
    public void setStage(Stage stage)
    {
        primaryStage = stage;
        primaryStage.setTitle(Config.APP_NAME);
        primaryStage.getIcons()
                .add(new Image("file:resources/images/companyIcon.png"));
    }

    /**
     * Sets main window.
     */
    public void setMainWindow()
    {
        primaryStage.close();
        primaryStage.setScene(new MainScene().getScene());

        primaryStage.setTitle(String.format("%s - [%s]",
                Config.APP_NAME, Storage.getInstance().getFilename()));

        primaryStage.show();
    }

    /**
     * Sets project dialog window.
     */
    public void setProjectDialogWindow()
    {
        primaryStage.close();
        primaryStage.setScene(new ProjectDialogScene().getScene());

        primaryStage.show();
    }

    /**
     * Has stage.
     *
     * @return the boolean
     */
    public boolean hasStage()
    {
        return primaryStage == null;
    }
}
