package main.view;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.config.Config;
import main.model.Storage;
import main.view.scene.MainScene;
import main.view.scene.ProjectDialogScene;

/**
 * Created by alex on 5/5/15.
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
        // Compatibility code
        primaryStage.setMaxHeight(Config.HEIGHT);
        primaryStage.setMinHeight(Config.HEIGHT);
        primaryStage.setMaxWidth(Config.WIDTH);
        primaryStage.setMinWidth(Config.WIDTH);

        primaryStage.show();
    }

    public void setProjectDialogWindow()
    {
        primaryStage.close();
        primaryStage.setScene(new ProjectDialogScene().getScene());

        // Compatibility code.
        primaryStage.setMaxHeight(Config.PD_HEIGHT);
        primaryStage.setMinHeight(Config.PD_HEIGHT);
        primaryStage.setMaxWidth(Config.PD_WIDTH);
        primaryStage.setMinWidth(Config.PD_WIDTH);

        primaryStage.show();
    }

    public boolean hasStage() { return primaryStage == null; }
}
