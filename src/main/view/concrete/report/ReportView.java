package main.view.concrete.report;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import main.view.StandardGridPane;

/**
 * ReportView.java
 */
abstract class ReportView extends StandardGridPane
{
    private int headerSize = 16;

    /**
     * Add header.
     *
     * @param string the string
     */
    protected void addHeader(String string)
    {
        Label label = new Label(string);
        label.setFont(new Font(headerSize));
        add(label, 0, rowNum++, 2, 1);
        addColSpan(new Separator());
    }

    /**
     * Add col span.
     *
     * @param node the node
     */
    private void addColSpan(Node node)
    {
        add(node, 0, rowNum++, 2, 1);
    }

    /**
     * Add key.
     *
     * @param string the string
     */
    protected void addKey(String string)
    {
        add(new Label(string), 0, rowNum);
    }

    /**
     * Add value.
     *
     * @param string the string
     */
    protected void addValue(String string)
    {
        add(new Label(string), 1, rowNum++);
    }

    public StandardGridPane getNode()
    {
        return this;
    }

}
