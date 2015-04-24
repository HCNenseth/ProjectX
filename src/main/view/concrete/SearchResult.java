package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import main.localization.Loc;
import main.view.BasePane;
import main.view.table.ClaimTable;
import main.view.table.InsuranceTable;
import main.view.table.PersonTable;
import main.view.table.Table;

/**
 * Created by alex on 4/24/15.
 */
public class SearchResult extends BasePane
{
    private GridPane gp;
    private int rowNum = 0;

    public SearchResult()
    {
        super(Loc.get("search_results"));
        gp = new GridPane();
    }

    public void addTable(Table<?> table, String label)
    {
        gp.add(new Label(label), 0, rowNum++);
        gp.add(table, 0, rowNum++);
    }

    public GridPane getNode()
    {
        return gp;
    }
}
