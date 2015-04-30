package main.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.localization.Loc;

/**
 * Created by alex on 4/30/15.
 */
public class InfoBar
{
    private GridPane main;
    private Button xout;
    private int msg = 95, button = 5;

    public InfoBar()
    {
        main = new GridPane();
        main.setVisible(false);

        ColumnConstraints msgColumn = new ColumnConstraints();
        msgColumn.setPercentWidth(msg);

        ColumnConstraints btnColumn = new ColumnConstraints();
        btnColumn.setPercentWidth(button);

        main.getColumnConstraints().add(0, msgColumn);
        main.getColumnConstraints().add(1, btnColumn);

        xout = new Button(Loc.get("close"));
        xout.setOnAction(e -> hide());

        main.setPadding(new Insets(10));

        main.add(xout, 1, 0);
    }

    public GridPane getMain() { return main; }

    private void hide() { main.setVisible(false); }

    private void show() { main.setVisible(true); }

    public void setAndShow(String string)
    {
        Label label = new Label(string);
        label.setTextFill(Color.RED);
        main.add(label, 0, 0);
        show();
    }

}
