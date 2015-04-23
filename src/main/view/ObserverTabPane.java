package main.view;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by alex on 4/10/15.
 */
public class ObserverTabPane extends TabPane implements Observer
{
    private List<ObservablePane> observablePanes;
    private List<OfflinePane> offlinePanes;
    SingleSelectionModel<Tab> selectionModel;

    public ObserverTabPane()
    {
        observablePanes = new LinkedList<>();
        offlinePanes = new LinkedList<>();

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

    public void injectOfflineTab(String title, Boolean closeable)
    {
        OfflinePane offPane = new OfflinePane(title);
        offlinePanes.add(offPane);

        Tab tmp = new Tab(title);
        tmp.setContent(offPane.getPane());
        tmp.closableProperty().set(closeable);
        getTabs().addAll(tmp);
    }

    public void injectObservableTab(String title, Boolean closeable)
    {
        injectObservableTab(title, null, closeable);
    }

    public void injectObservableTab(String title, Node content, Boolean closeable)
    {
        ObservablePane obsPane = new ObservablePane(this, title);
        obsPane.setContent(content);
        observablePanes.add(obsPane);

        Tab tmp = new Tab(title);
        tmp.setContent(obsPane.getPane());
        tmp.closableProperty().set(closeable);
        getTabs().addAll(tmp);

        selectionModel.select(tmp);
    }

}
