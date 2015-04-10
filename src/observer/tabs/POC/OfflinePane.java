package observer.tabs.POC;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by alex on 4/10/15.
 */
public class OfflinePane
{
    private Pane pane;

    private int paddingX = 20, paddingY = 20;

    public OfflinePane(String title)
    {
        this.pane = new Pane();
        createPane(title);
    }

    private void createPane(String title)
    {
        Label label = new Label(title);
        label.setLayoutX(paddingX);
        label.setLayoutY(paddingY);

        pane.getChildren().addAll(label);
    }

    public Pane getPane()
    {
        return pane;
    }

}
