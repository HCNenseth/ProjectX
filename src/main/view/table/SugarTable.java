package main.view.table;

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

import java.util.LinkedList;
import java.util.List;

/**
 * SugarTable.java
 */
public class SugarTable extends StandardGridPane
{
    private Table<? extends Model> table;
    private Model.ModelType type;

    private static int headerSize = 16;
    private static int cellGap = 5;


    /**
     * Instantiates a new Sugar table.
     *
     * @param table the table
     * @param type the type
     * @param title the title
     */
    public SugarTable(Table<? extends Model> table,
                      Model.ModelType type, String title)
    {
        super(ColumnCount.ONE);

        this.table = table;
        this.type = type;

        insertHeader(title);
        insertTable();
    }

    private void insertHeader(String title)
    {
        AnchorPane header = new AnchorPane();

        // title
        Label label = new Label(String.format("%s (%d)",
                title, table.getItems().size()));
        label.setFont(new Font(headerSize));
        AnchorPane.setLeftAnchor(label, 0d);

        header.getChildren().add(label);

        // links
        if (table.getItems().size() > 0) {
            HBox links = generateLinks();
            AnchorPane.setRightAnchor(links, 0d);
            header.getChildren().add(links);
        }

        add(header, 0, rowNum++);
    }

    private void insertTable()
    {
        add(table, 0, rowNum++);
    }

    private HBox generateLinks()
    {
        HBox buttonWrapper = new HBox();
        buttonWrapper.setSpacing(cellGap);

        Hyperlink statistics = new Hyperlink(Loc.c("statistics"));
        Hyperlink report = new Hyperlink(Loc.c("report"));

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
            default:
                convert = new LinkedList<Claim>();
                table.getItems().stream().forEach(convert::add);
                statistics.setOnAction(e ->
                        StatisticsController.loadClaimsStatistics(convert));
                report.setOnAction(e ->
                        ReportController.loadClaimReport(convert,
                                Loc.c("claim_report")));
                break;
        }

        buttonWrapper.getChildren().addAll(statistics, report);
        return buttonWrapper;
    }

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }
}
