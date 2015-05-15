package main.view.concrete;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import main.controller.ReportController;
import main.controller.StatisticsController;
import main.localization.Loc;
import main.model.Model;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.StandardGridPane;
import main.view.table.SugarTable;
import main.view.table.Table;

import java.util.LinkedList;
import java.util.List;

/**
 * SearchResultView.java
 */
public class SearchResultView extends StandardGridPane
{
    private int rowNum = 0;
    private int headerSize = 16;

    public SearchResultView()
    {
        super(ColumnCount.ONE);
    }

    public void addTable(Table<? extends Model> table, Model.ModelType type, String title)
    {
        // if table is empty - return.
        if (table.getItems().size() == 0) { return; }

        SugarTable sugarTable = new SugarTable(table, type, title);
        add(sugarTable.getNode(), 0, rowNum++);
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
