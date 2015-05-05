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
 * Created by alex on 5/5/15.
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
        scene.getStylesheets().add("file:resources/css/main.css");
    }

    private void draw()
    {
        gridPane = new GridPane();

        hbox = new HBox();
        hbox.setSpacing(50);

        openExistingIcon = new Image("File:resources/images/Folder-Open-icon.png");
        createNewIcon = new Image("File:resources/images/Folder-Add-icon.png");

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
