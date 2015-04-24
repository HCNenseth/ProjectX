package main.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.localization.Loc;

/**
 * Created by HansChristian on 23.04.2015.
 */
public class SidePane
{
    public SidePane()
    {
        VBox vBox = new VBox(5);
        Button btn = new Button("Test");
        vBox.getChildren().addAll(btn);


    }

}
