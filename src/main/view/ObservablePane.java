package main.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import main.view.form.Form;
import main.view.form.adapter.PersonAdapter;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by alex on 4/10/15.
 */
public class ObservablePane extends Observable
{
    private BasePane pane;

    public ObservablePane(Observer observer, String title)
    {
        this.pane = new BasePane(title);
        addObserver(observer);
    }

    public Pane getPane()
    {
        return pane;
    }

    public void setContent(Node node)
    {
        pane.setContent(node);
    }

}
