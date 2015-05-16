package main.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.localization.Loc;

/**
 * InfoBar.java
 */
public class InfoBar extends GridPane
{
    private Button xout;
    private int msg = 95, button = 5;

    /**
     * Instantiates a new Info bar.
     */
    public InfoBar()
    {
        setVisible(false);

        ColumnConstraints msgColumn = new ColumnConstraints();
        msgColumn.setPercentWidth(msg);

        ColumnConstraints btnColumn = new ColumnConstraints();
        btnColumn.setPercentWidth(button);

        getColumnConstraints().add(0, msgColumn);
        getColumnConstraints().add(1, btnColumn);

        xout = new Button(Loc.c("close"));
        xout.setOnAction(e -> hide());

        setPadding(new Insets(10));

        add(xout, 1, 0);
    }

    /**
     * Gets main.
     *
     * @return the main
     */
    public GridPane getMain()
    {
        return this;
    }

    private void hide()
    {
        setVisible(false);
    }

    private void show()
    {
        setVisible(true);
    }

    /**
     * Sets and show.
     *
     * @param string the string
     */
    public void setAndShow(String string)
    {
        Label label = new Label(string);
        label.setTextFill(Color.RED);
        add(label, 0, 0);
        show();
    }
}
