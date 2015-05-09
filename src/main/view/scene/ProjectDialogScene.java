package main.view.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.config.Config;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;
import main.preference.Pref;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    private Button openExisting, createNew, langEN, langNO, langIT, langFR;

    private int linesGap = 10, btnSize = 80,
                appNameSize = 20, appNamePadding = 5,
                rowNum = 0;


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

        gridPane.add(appName, 0, rowNum++, 2, 1);

        gridPane.add(openExisting, 0, rowNum);
        gridPane.add(createNew, 1, rowNum++);
        gridPane.add(new Label(Loc.c("open_existing")), 0, rowNum);
        gridPane.add(new Label(Loc.c("create_new")), 1, rowNum++);

        ToggleGroup languages = new ToggleGroup();
        for(String s : Loc.getLanguages())
        {
            RadioButton tmp = new RadioButton(Loc.c(s));

            // TODO fix this bug
            System.out.println(Config.IMAGES + s + ".png");
            Image image = new Image(Config.IMAGES + s +".png");
            tmp.setGraphic(new ImageView(image));

            tmp.setToggleGroup(languages);

            if(Loc.getActiveLang().equals(s))
            {
                tmp.setSelected(true);
            }

            tmp.setOnAction(e -> {
                Loc.setActiveLang(s);
                Pref.inst.put("language", s);
            });

            gridPane.add(tmp, 0, rowNum++);
        }



    }

    public Scene getScene() { return scene; }

}
