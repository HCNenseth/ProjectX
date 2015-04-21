package observer.tabs.POC;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.view.BasePane;

/**
 * Created by alex on 4/10/15.
 */
public class OfflinePane
{
    private BasePane pane;

    public OfflinePane(String title)
    {
        this.pane = new BasePane(title);
    }

    public Pane getPane()
    {
        return pane;
    }

}
