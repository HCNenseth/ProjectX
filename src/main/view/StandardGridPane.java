package main.view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by alex on 4/30/15.
 */
public abstract class StandardGridPane extends GridPane
{
    private int vgap = 5, hgap = 5;
    private int key = 20, value = 80;

    public StandardGridPane() { this(2); }

    public StandardGridPane(int colCount)
    {
        switch (colCount) {
            case 1: setupOneColumn(); break;
            case 3: setupThreeColumn(); break;
            default: setupTwoColumns();
        }

        setHgap(hgap);
        setVgap(vgap);
    }

    private void setupOneColumn()
    {
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        getColumnConstraints().add(0, mainColumn);
    }

    private void setupTwoColumns()
    {
        ColumnConstraints keyColumn = new ColumnConstraints();
        keyColumn.setPercentWidth(key);

        ColumnConstraints valueColumn = new ColumnConstraints();
        valueColumn.setPercentWidth(value);

        getColumnConstraints().add(0, keyColumn);
        getColumnConstraints().add(1, valueColumn);
    }

    private void setupThreeColumn()
    {
        ColumnConstraints keyColumn = new ColumnConstraints();
        keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(10);

        ColumnConstraints valueColumn = new ColumnConstraints();
        valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(60);

        ColumnConstraints errorColumn = new ColumnConstraints();
        errorColumn.setHgrow(Priority.ALWAYS);
        errorColumn.setPercentWidth(30);

        getColumnConstraints().add(0, keyColumn);
        getColumnConstraints().add(1, valueColumn);
        getColumnConstraints().add(2, errorColumn);
    }

    public abstract StandardGridPane getNode();
}
