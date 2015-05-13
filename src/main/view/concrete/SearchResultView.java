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
    private int cellGap = 5;

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

        HBox rightBox = new HBox();
        rightBox.setSpacing(cellGap);

        Hyperlink statistics = new Hyperlink(Loc.c("statistics"));
        Hyperlink report = new Hyperlink(Loc.c("report"));

        rightBox.getChildren().addAll(statistics, report);

        List convert;
        switch (type) {
            case PERSON:
                convert = new LinkedList<Person>();
                table.getItems().stream().forEach(convert::add);
                statistics.setOnAction(e ->
                        StatisticsController.loadPersonStatistics(convert));
                report.setOnAction(e ->
                        ReportController.loadPersonReport(convert,
                                Loc.c("person_report")));
                break;
            case INSURANCE:
                convert = new LinkedList<Insurance>();
                table.getItems().stream().forEach(convert::add);
                statistics.setOnAction(e ->
                        StatisticsController.loadInsuranceStatistics(convert));
                report.setOnAction(e ->
                        ReportController.loadInsuranceReport(convert,
                                Loc.c("insurance_report")));
                break;
            case CLAIM:
                convert = new LinkedList<Claim>();
                table.getItems().stream().forEach(convert::add);
                statistics.setOnAction(e ->
                        StatisticsController.loadClaimsStatistics(convert));
                report.setOnAction(e ->
                        ReportController.loadClaimReport(convert,
                                Loc.c("claim_report")));
                break;
        }

        AnchorPane.setRightAnchor(rightBox, 0d);

        header.getChildren().addAll(l, rightBox);

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
