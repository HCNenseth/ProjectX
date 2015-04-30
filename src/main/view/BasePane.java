package main.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import main.view.concrete.SplashView;

/**
 * Created by alex on 4/18/15.
 */
public class BasePane extends BorderPane
{
    private Label title;
    private int titleSize = 20;
    private int padding = 10;

    public BasePane(String title)
    {
        this.title = new Label(title);
        this.title.setPadding(new Insets(padding));
        this.title.setFont(new Font(titleSize));

        topProperty().set(this.title);
    }

    public void setContent(Node node)
    {
        centerProperty().set(node);
        setPadding(new Insets(padding));
    }
}
