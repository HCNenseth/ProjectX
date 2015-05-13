package main.view.concrete.report;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.InsuranceType;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.util.List;
import java.util.stream.IntStream;

/**
 * ReportView.java
 */
abstract class ReportView extends StandardGridPane
{
    protected int rowNum = 0;
    private int headerSize = 16;

    protected void addHeader(String string)
    {
        Label label = new Label(string);
        label.setFont(new Font(headerSize));
        add(label, 0, rowNum++, 2, 1);
        addColSpan(new Separator());
    }

    protected void addColSpan(Node node)
    {
        add(node, 0, rowNum++, 2, 1);
    }

    protected void addKey(String string)
    {
        add(new Label(string), 0, rowNum);
    }

    protected void addValue(String string)
    {
        add(new Label(string), 1, rowNum++);
    }

    public StandardGridPane getNode() { return this; }

}