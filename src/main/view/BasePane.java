package main.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Created by alex on 4/18/15.
 */
public class BasePane extends Pane
{
    private Label title;
    private BorderPane borderPane;
    private int titleSize = 20;
    private int padding = 10;

    public BasePane(String title)
    {
        this.borderPane = new BorderPane();
        this.title = new Label(title);
        this.title.setPadding(new Insets(padding));
        this.title.setFont(new Font(titleSize));

        borderPane.topProperty().set(this.title);

        getChildren().addAll(borderPane);
    }

    public void setContent(Node node)
    {
        borderPane.centerProperty().set(node);
        borderPane.setPadding(new Insets(padding));
    }
}
