package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import main.view.StandardGridPane;
import main.view.table.Table;

/**
 * Created by alex on 4/24/15.
 */
public class SearchResultView
{
    private StandardGridPane gp;
    private int rowNum = 0;
    private int headerSize = 16;

    public SearchResultView()
    {
        gp = new StandardGridPane(1);
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
