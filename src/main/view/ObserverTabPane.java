package main.view;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.model.Model;

import java.util.*;

/**
 * ObserverTabPane
 *
 * Main tab pane for application. Extends JavaFX TabPane and adds
 * some functionality on top. Implements Observer pattern for communication.
 *
 * @filename ObserverTabPane.java
 * @date 2015-04-26
 */
public class ObserverTabPane extends TabPane implements Observer
{
    private Map<Tab, ObservablePane> observablePaneMap;
    private SingleSelectionModel<Tab> selectionModel;

    public ObserverTabPane()
    {
        observablePaneMap = new HashMap<>();
        selectionModel = getSelectionModel();
    }

    /**
     * Checks if the observable tab pane is empty.
     * If it is, then show the splash screen.
     */
    public SplashPane showSplashScreen()
    {
        return new SplashPane();
    }

    /**
     * Observer pattern update method. This method can be used
     * to send a signal to all observable panes in the list.
     * @param obj
     * @param arg
     */
    @Override
    public void update(Observable obj, Object arg)
    {
        ObservablePane caller = (ObservablePane)obj; // unsafe
        SignalType signalType = (SignalType)arg; // unsafe

        switch (signalType) {
            case REFRESH:
                break;
            case DELETE:
                break;
        }
    }

    /**
     * Find and delete tab and list entry based on Model reference
     * @param ref
     */
    public void closeObservableTabs(Model ref)
    {
        Iterator iterator = observablePaneMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            Tab key = (Tab)pair.getKey();
            ObservablePane value = (ObservablePane)pair.getValue();
            if (value.hasReference(ref)) {
                getTabs().remove(key);
                iterator.remove();
            }
        }
    }

    /**
     * remove tab from map when it closes.
     * @param tab
     */
    private void closeTab(Tab tab)
    {
        if (observablePaneMap.containsKey(tab)) {
            observablePaneMap.remove(tab);
            if(getTabs().size() < 2)
            {
                showSplashScreen();
            }
        }
    }

    /**
     * Inject new tab without reference. A simple proxy method for
     * the method below (with reference)
     * @param title - tab title
     * @param content - tab content
     * @param closeable - tab attr
     */
    public void injectObservableTab(String title, Node content,
                                   Boolean closeable)
    {
        injectObservableTab(title, content, null, closeable);
    }

    /**
     * Inject new tab with reference
     * @param title - tab title
     * @param content - tab content
     * @param ref - tab model reference
     * @param closeable - tab attr
     */
    public void injectObservableTab(String title, Node content,
                                   Model ref, Boolean closeable)
    {
        System.out.println(getTabs().size());

        ObservablePane obsPane = new ObservablePane(this, title);
        if (ref != null) { obsPane.setReference(ref); }
        obsPane.setContent(content);

        Tab tab = new Tab(title);
        tab.setContent(obsPane.getPane());
        tab.closableProperty().set(closeable);
        tab.setOnCloseRequest(e -> closeTab(tab));

        // Add tab to tab pane
        getTabs().addAll(tab);

        // Add tab to hash map for later reference
        observablePaneMap.put(tab, obsPane);

        // Select new tab
        selectionModel.select(tab);
    }
}
