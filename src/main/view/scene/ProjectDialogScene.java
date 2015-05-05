package main.view.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.config.Config;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;

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
    private HBox hbox;

    private Image openExistingIcon, createNewIcon;
    private ImageView openExistingImageView, createNewImageView;
    private Button openExisting, createNew;

    public ProjectDialogScene()
    {
        draw();

        scene = new Scene(gridPane, Config.PD_WIDTH, Config.PD_HEIGHT);
        scene.getStylesheets().add(Config.CSS + "main.css");
    }

    private void draw()
    {
        gridPane = new GridPane();

        hbox = new HBox();
        hbox.setSpacing(50);

        openExistingIcon = new Image(Config.IMAGES + "open131.png");
        createNewIcon = new Image(Config.IMAGES + "add118.png");

        openExistingImageView = new ImageView();
        openExistingImageView.setImage(openExistingIcon);
        openExistingImageView.setFitWidth(80);
        openExistingImageView.setFitHeight(80);
        openExistingImageView.setPreserveRatio(true);
        openExistingImageView.setSmooth(true);
        openExistingImageView.setCache(true);
        openExisting = new Button("", openExistingImageView);
        openExisting.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.OPEN)));

        createNewImageView = new ImageView();
        createNewImageView.setImage(createNewIcon);
        createNewImageView.setFitHeight(80);
        createNewImageView.setFitWidth(80);
        createNewImageView.setPreserveRatio(true);
        createNewImageView.setSmooth(true);
        createNewImageView.setCache(true);
        createNew = new Button("", createNewImageView);
        createNew.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW)));

        hbox.getChildren().addAll(openExisting, createNew);

        gridPane.add(hbox, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
    }

    public Scene getScene() { return scene; }

}
