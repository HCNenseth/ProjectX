package main.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * BasePane.java
 */
class BasePane extends BorderPane
{
    private final Label title;
    private final int titleSize = 20;
    private final int padding = 10;

    /**
     * Instantiates a new Base pane.
     *
     * @param title the title
     */
    public BasePane(String title)
    {
        this.title = new Label(title);
        this.title.setPadding(new Insets(padding));
        this.title.setFont(new Font(titleSize));

        topProperty().set(this.title);
    }

    /**
     * Sets content.
     *
     * @param node the node
     */
    public void setContent(Node node)
    {
        centerProperty().set(node);
        setPadding(new Insets(padding));
    }
}
