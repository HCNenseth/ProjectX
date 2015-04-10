package observer.tabs.POC;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by alex on 4/10/15.
 */
public class ObservablePane extends Observable
{
    private Pane pane;

    private int paddingX = 20, paddingY = 20;

    public ObservablePane(Observer observer, String title)
    {
        addObserver(observer);
        this.pane = new Pane();

        createPane(title);
    }

    private void createPane(String title)
    {
        Label label = new Label(title);
        label.setLayoutX(paddingX);
        label.setLayoutY(paddingY);

        Button b = new Button("Notify everyone!");
        b.setLayoutX(paddingX);
        b.setLayoutY(paddingY += 20);
        b.setOnAction(e -> {
            setChanged();
            notifyObservers();
        });

        pane.getChildren().addAll(label, b);
    }

    public void pushLabel(String text)
    {
        Label label = new Label(text);
        label.setLayoutX(paddingX);
        label.setLayoutY(paddingY += 30);
        pane.getChildren().add(label);
    }

    public Pane getPane()
    {
        return pane;
    }

}
