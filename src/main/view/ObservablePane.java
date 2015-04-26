package main.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import main.model.Model;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by alex on 4/10/15.
 */
public class ObservablePane extends Observable
{
    private BasePane pane;
    private Model reference;

    public ObservablePane(Observer observer, String title)
    {
        this.pane = new BasePane(title);
        addObserver(observer);
    }

    public Pane getPane() { return pane; }

    public void setContent(Node node) { pane.setContent(node); }

    public boolean hasReference(Model reference)
    {
        if (this.reference != null && this.reference.equals(reference))
            return true;

        return false;
    }

    public void setReference(Model reference) { this.reference = reference; }
}
