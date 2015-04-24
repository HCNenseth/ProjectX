package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import main.App;
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
    private int headerSize = 16;

    public SearchResult()
    {
        super(Loc.get("search_results"));
        gp = new GridPane();
        gp.setMinWidth(App.WIDTH - (App.WIDTH / 20));
        gp.setMinHeight(App.HEIGHT - (App.HEIGHT / 20));

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
