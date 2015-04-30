package main.view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Created by alex on 4/30/15.
 */
public class StandardGridPane extends GridPane
{
    private int vgap = 5, hgap = 5;
    private int key = 20, value = 80;

    public StandardGridPane() { this(2); }

    public StandardGridPane(int colCount)
    {
        switch (colCount) {
            case 1: setupOneColumn(); break;
            default: setupTwoColumns();
        }

        setHgap(hgap);
        setVgap(vgap);
    }

    private void setupTwoColumns()
    {
        ColumnConstraints keyColumn = new ColumnConstraints();
        //keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(key);

        ColumnConstraints valueColumn = new ColumnConstraints();
        //valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(value);

        getColumnConstraints().add(0, keyColumn);
        getColumnConstraints().add(1, valueColumn);
    }

    private void setupOneColumn()
    {
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        getColumnConstraints().add(0, mainColumn);
    }
}