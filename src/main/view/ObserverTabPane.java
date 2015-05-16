package main.view;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.config.Config;

import java.util.*;

/**
 * ObserverTabPane.java
 *
 * Main tab pane for application. Extends JavaFX TabPane and adds
 * some functionality on top. Implements Observer pattern for communication.
 *
 * @filename ObserverTabPane.java
 * @date 2015 -04-26
 */
public class ObserverTabPane extends TabPane implements Observer
{
    private Map<Tab, ObservablePane> observablePaneMap;
    private SingleSelectionModel<Tab> selectionModel;

    /**
     * Instantiates a new Observer tab pane.
     */
    public ObserverTabPane()
    {
        observablePaneMap = new HashMap<>();
        selectionModel = getSelectionModel();
    }

    /**
     * Observer pattern update method. This method can be used
     * to send a signal to all observable panes in the list.
     *
     * @param obj
     * @param arg
     */
    @Override
    public void update(Observable obj, Object arg)
    {
        ObservablePane caller = (ObservablePane) obj; // unsafe
        SignalType signalType = (SignalType) arg; // unsafe

        switch (signalType) {
            case REFRESH:
                break;
            case DELETE:
                break;
        }
    }

    /**
     * Find and delete tab and list entry based on Model reference
     *
     * @param ref the ref
     */
    public void closeObservableTabs(Object ref)
    {
        Iterator iterator = observablePaneMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Tab key = (Tab) pair.getKey();
            ObservablePane value = (ObservablePane) pair.getValue();
            if (value.hasReference(ref)) {
                getTabs().remove(key);
                iterator.remove();
            }
        }
    }

    /**
     * remove tab from map when it closes.
     *
     * @param tab
     */
    private void closeTab(Tab tab)
    {
        if (observablePaneMap.containsKey(tab)) {
            observablePaneMap.remove(tab);
        }

        if (getTabs().size() < 2) {
            Resources.inst.getSplashView().show();
        }

    }

    /**
     * Close all tabs.
     */
    public void closeAllTabs()
    {
        observablePaneMap.clear();
        getTabs().clear();
        Resources.inst.getSplashView().show();
    }

    /**
     * Inject offline tab.
     *
     * @param title the title
     * @param content the content
     * @param image the image
     * @param closeable the closeable
     */
    public void injectOfflineTab(String title, Node content,
                                 String image, boolean closeable)
    {
        Resources.inst.getSplashView().hide();

        BasePane bp = new BasePane(title);
        bp.setContent(content);

        Tab tab = new Tab(title);
        tab.setContent(bp);
        tab.setClosable(closeable);
        tab.setOnCloseRequest(e -> closeTab(tab));
        tab.setGraphic(new ImageView(new Image(Config.IMAGES + image)));

        getTabs().add(tab);

        selectionModel.select(tab);
    }

    /**
     * Inject new tab with reference
     *
     * @param title - tab title
     * @param content - tab content
     * @param ref - tab model reference
     * @param image the image
     * @param closeable - tab attr
     */
    public void injectObservableTab(String title, Node content,
                                    Object ref, String image, boolean closeable)
    {
        // hide splash view...
        Resources.inst.getSplashView().hide();

        ObservablePane obsPane = new ObservablePane(this, title);
        if (ref != null) {
            obsPane.setReference(ref);
        }
        obsPane.setContent(content);

        Tab tab = new Tab(title);
        tab.setContent(obsPane.getPane());
        tab.closableProperty().set(closeable);
        tab.setOnCloseRequest(e -> closeTab(tab));
        tab.setGraphic(new ImageView(new Image(Config.IMAGES + image)));

        // Add tab to tab pane
        getTabs().addAll(tab);

        // Add tab to hash map for later reference
        observablePaneMap.put(tab, obsPane);

        // Select new tab
        selectionModel.select(tab);
    }
}
