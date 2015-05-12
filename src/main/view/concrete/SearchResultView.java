package main.view.concrete;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.controller.StatisticsController;
import main.localization.Loc;
import main.model.Model;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.StandardGridPane;
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
        super(1);
    }

    public void addTable(Table<? extends Model> table, Model.ModelType type, String title)
    {
        // if table is empty - return.
        if (table.getItems().size() == 0) { return; }

        table.getItems().size();

        Label l = new Label(String.format("%s (%d)",
                title, table.getItems().size()));
        l.setFont(new Font(headerSize));

        AnchorPane header = new AnchorPane();
        AnchorPane.setLeftAnchor(l, 0d);

        Hyperlink generate = new Hyperlink(Loc.c("generate_statistics"));

        List convert;
        switch (type) {
            case PERSON:
                convert = new LinkedList<Person>();
                table.getItems().stream().forEach(convert::add);
                generate.setOnAction(e ->
                        StatisticsController.loadPersonStatistics(convert));
                break;
            case INSURANCE:
                convert = new LinkedList<Insurance>();
                table.getItems().stream().forEach(convert::add);
                generate.setOnAction(e ->
                        StatisticsController.loadInsuranceStatistics(convert));
                break;
            case CLAIM:
                convert = new LinkedList<Claim>();
                table.getItems().stream().forEach(convert::add);
                generate.setOnAction(e ->
                        StatisticsController.loadClaimsStatistics(convert));
                break;
        }

        AnchorPane.setRightAnchor(generate, 0d);

        header.getChildren().addAll(l, generate);

        add(header, 0, rowNum++);
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
