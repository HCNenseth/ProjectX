package main.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

/**
 * ObservablePane.java
 *
 *
 * Tabs for ObserverTabPane. Extends Observable for observer pattern
 * with ObserverTabPane.
 *
 * @filename ObservablePane.java
 * @date 2015 -04-26
 */
public class ObservablePane extends Observable
{
    private BasePane pane;
    private Object reference;

    /**
     * Instantiates a new Observable pane.
     *
     * @param observer the observer
     * @param title the title
     */
    public ObservablePane(Observer observer, String title)
    {
        this.pane = new BasePane(title);
        addObserver(observer);
    }

    /**
     * Gets pane.
     *
     * @return the pane
     */
    public Pane getPane()
    {
        return pane;
    }

    /**
     * Sets content.
     *
     * @param node the node
     */
    public void setContent(Node node)
    {
        pane.setContent(node);
    }

    /**
     * Has reference.
     *
     * @param reference the reference
     * @return the boolean
     */
    public boolean hasReference(Object reference)
    {
        return this.reference != null && this.reference.equals(reference);
    }

    /**
     * Sets reference.
     *
     * @param reference the reference
     */
    public void setReference(Object reference)
    {
        this.reference = reference;
    }
}
