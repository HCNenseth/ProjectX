package BorderPane.POC;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by Hans Christian on 12.04.2015.
 */
public class newPane
{

    private Pane pane;
    private int paddingX = 20, paddingY = 20;

    public newPane(String title)
    {
        this.pane = new Pane();
        createPane(title);
    }

    private void createPane(String title)
    {
        Label label = new Label(title);
        label.setLayoutX(paddingX);
        label.setLayoutY(paddingY);
        pane.getChildren().add(pane);
    }
}
