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
    private static int counter = 0;
    private int id, paddingX = 20, paddingY = 20;

    public ObservablePane(Observer observer, String title)
    {
        this.pane = new Pane();
        id = counter++;

        addObserver(observer);
        createPane(title);
    }

    private void createPane(String title)
    {
        Label label = new Label(title);
        label.setLayoutX(paddingX);
        label.setLayoutY(paddingY);

        Button b1 = new Button("Send refresh signal");
        b1.setLayoutX(paddingX);
        b1.setLayoutY(paddingY += 30);
        b1.setOnAction(e -> {
            setChanged();
            notifyObservers(SignalType.REFRESH);
        });

        Button b2 = new Button("Send delete signal");
        b2.setLayoutX(paddingX);
        b2.setLayoutY(paddingY += 30);
        b2.setOnAction(e -> {
            setChanged();
            notifyObservers(SignalType.DELETE);
        });

        pane.getChildren().addAll(label, b1, b2);
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

    public int getId() { return id; }

}
