package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import main.localization.Loc;
import main.view.StandardGridPane;
import main.view.table.Table;

/**
 * Created by alex on 4/24/15.
 */
public class SearchResultView extends StandardGridPane
{
    private int rowNum = 0;
    private int headerSize = 16;

    public void addTable(Table<?> table, String label)
    {
        // if table is empty - return.
        if (table.getItems().size() == 0) { return; }

        Label l = new Label(label);
        l.setFont(new Font(headerSize));
        add(l, 0, rowNum++);
        add(table, 0, rowNum++);
    }

    @Override
    public StandardGridPane getNode()
    {
        if (rowNum == 0) {
            Label label = new Label(Loc.c("no_results"));
            label.setFont(new Font(headerSize));
            add(label, 0, 0);
        }
        return this;
    }
}
