package main.view;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.model.Model;

import java.util.*;

/**
 * Created by alex on 4/10/15.
 */
public class ObserverTabPane extends TabPane implements Observer
{
    private Map<Tab, ObservablePane> observablePaneMap;
    private List<ObservablePane> observablePanes;
    private List<OfflinePane> offlinePanes;
    SingleSelectionModel<Tab> selectionModel;

    public ObserverTabPane()
    {
        observablePanes = new LinkedList<>();
        offlinePanes = new LinkedList<>();
        observablePaneMap = new HashMap<>();

        selectionModel = getSelectionModel();
    }

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

    public Tab injectOfflineTab(String title, Boolean closeable)
    {
        OfflinePane offPane = new OfflinePane(title);
        offlinePanes.add(offPane);

        Tab tab = new Tab(title);
        tab.setContent(offPane.getPane());
        tab.closableProperty().set(closeable);
        getTabs().addAll(tab);

        selectionModel.select(tab);

        return tab;
    }

    public Tab injectObservableTab(String title, Node content,
                                   Boolean closeable)
    {
        return injectObservableTab(title, content, null, closeable);
    }

    public Tab injectObservableTab(String title, Node content,
                                   Model ref, Boolean closeable)
    {
        ObservablePane obsPane = new ObservablePane(this, title);
        obsPane.setContent(content);
        if (ref != null) { obsPane.setReference(ref); }
        observablePanes.add(obsPane);

        Tab tab = new Tab(title);
        tab.setContent(obsPane.getPane());
        tab.closableProperty().set(closeable);
        getTabs().addAll(tab);

        observablePaneMap.put(tab, obsPane);

        selectionModel.select(tab);

        return tab;
    }
}
