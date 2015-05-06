package main.view.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import main.config.Config;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;

/**
 * ProjectDialog Scene
 *
 * This file both draws and sets the Project Dialog window. This because
 * the codebase is so small, and splitting it up would only make things
 * more messy.
 */
public class ProjectDialogScene
{
    private Scene scene;
    private GridPane gridPane;

    private Image openExistingIcon, createNewIcon;
    private ImageView openExistingImageView, createNewImageView;
    private Button openExisting, createNew;

    private int linesGap = 10, btnSize = 80,
                appNameSize = 20, appNamePadding = 5;


    public ProjectDialogScene()
    {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(linesGap);
        gridPane.setVgap(linesGap);

        draw();

        scene = new Scene(gridPane, Config.PD_WIDTH, Config.PD_HEIGHT);
        scene.getStylesheets().add(Config.CSS + "main.css");
    }

    private void draw()
    {
        openExistingIcon = new Image(Config.IMAGES + "open131.png");
        createNewIcon = new Image(Config.IMAGES + "add118.png");

        openExistingImageView = new ImageView();
        openExistingImageView.setImage(openExistingIcon);
        openExistingImageView.setFitWidth(btnSize);
        openExistingImageView.setFitHeight(btnSize);
        openExistingImageView.setPreserveRatio(true);
        openExistingImageView.setSmooth(true);
        openExistingImageView.setCache(true);
        openExisting = new Button("", openExistingImageView);
        openExisting.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.OPEN)));

        createNewImageView = new ImageView();
        createNewImageView.setImage(createNewIcon);
        createNewImageView.setFitHeight(btnSize);
        createNewImageView.setFitWidth(btnSize);
        createNewImageView.setPreserveRatio(true);
        createNewImageView.setSmooth(true);
        createNewImageView.setCache(true);
        createNew = new Button("", createNewImageView);
        createNew.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW)));

        Label appName = new Label(Config.APP_NAME);
        appName.setFont(new Font(appNameSize));
        appName.setPadding(new Insets(appNamePadding));

        gridPane.add(appName, 0, 0, 2, 1);

        gridPane.add(openExisting, 0, 1);
        gridPane.add(new Label(Loc.c("open_existing")), 0, 2);

        gridPane.add(createNew, 1, 1);
        gridPane.add(new Label(Loc.c("create_new")), 1, 2);
    }

    public Scene getScene() { return scene; }

}
