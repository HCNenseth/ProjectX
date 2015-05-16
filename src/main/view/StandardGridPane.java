package main.view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * StandardGridPane.java
 */
public abstract class StandardGridPane extends GridPane
{
    private int vgap = 5, hgap = 5;
    private int key = 20, value = 80;
    /**
     * The Row num.
     */
    protected int rowNum = 0;

    private ColumnCount columnCount;

    /**
     * The enum Column count.
     */
    public enum ColumnCount
    {
        /**
         * The ONE.
         */
        ONE, /**
     * The TWO.
     */
    TWO, /**
     * The THREE.
     */
    THREE
    }

    /**
     * Instantiates a new Standard grid pane.
     */
    public StandardGridPane()
    {
        this(ColumnCount.TWO);
    }

    /**
     * Instantiates a new Standard grid pane.
     *
     * @param columnCount the column count
     */
    public StandardGridPane(ColumnCount columnCount)
    {
        this.columnCount = columnCount;

        switch (columnCount) {
            case ONE:
                setupOneColumn();
                break;
            case THREE:
                setupThreeColumn();
                break;
            case TWO:
            default:
                setupTwoColumns();
        }

        setHgap(hgap);
        setVgap(vgap);
    }

    private void setupOneColumn()
    {
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        getColumnConstraints().add(0, mainColumn);
    }

    private void setupTwoColumns()
    {
        ColumnConstraints keyColumn = new ColumnConstraints();
        keyColumn.setPercentWidth(key);

        ColumnConstraints valueColumn = new ColumnConstraints();
        valueColumn.setPercentWidth(value);

        getColumnConstraints().add(0, keyColumn);
        getColumnConstraints().add(1, valueColumn);
    }

    private void setupThreeColumn()
    {
        ColumnConstraints keyColumn = new ColumnConstraints();
        keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(10);

        ColumnConstraints valueColumn = new ColumnConstraints();
        valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(60);

        ColumnConstraints errorColumn = new ColumnConstraints();
        errorColumn.setHgrow(Priority.ALWAYS);
        errorColumn.setPercentWidth(30);

        getColumnConstraints().add(0, keyColumn);
        getColumnConstraints().add(1, valueColumn);
        getColumnConstraints().add(2, errorColumn);
    }

    /**
     * Gets node.
     *
     * @return the node
     */
    public abstract StandardGridPane getNode();
}
