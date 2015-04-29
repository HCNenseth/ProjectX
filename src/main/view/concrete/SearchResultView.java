package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import main.config.Config;
import main.view.table.Table;

/**
 * Created by alex on 4/24/15.
 */
public class SearchResultView
{
    private GridPane gp;
    private int rowNum = 0;
    private int headerSize = 16;

    public SearchResultView()
    {
        gp = new GridPane();

        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);

        gp.getColumnConstraints().add(0, mainColumn);
        gp.setHgap(5);
        gp.setVgap(5);
    }

    public void addTable(Table<?> table, String label)
    {
        Label l = new Label(label);
        l.setFont(new Font(headerSize));
        gp.add(l, 0, rowNum++);
        gp.add(table, 0, rowNum++);
    }

    public GridPane getNode()
    {
        return gp;
    }
}
