package observer.tabs.POC;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.view.ObservablePane;
import main.view.SignalType;

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

    public ObserverTabPane()
    {
        observablePanes = new LinkedList<>();
        offlinePanes = new LinkedList<>();
    }

    @Override
    public void update(Observable obj, Object arg)
    {
        ObservablePane caller = (ObservablePane)obj; // unsafe
        SignalType signalType = (SignalType)arg; // unsafe

        switch (signalType) {
            case REFRESH:
                observablePanes.stream()
                        .filter(i -> i != caller)
                        .forEach(i ->
                                i.pushLabel("Received refresh from: " + caller.getId()));
                break;
            case DELETE:
                observablePanes.stream()
                        .filter(i -> i != caller)
                        .forEach(i -> i.pushLabel("Received delete from: " + caller.getId()));
                break;
            default:
                observablePanes.stream()
                        .filter(i -> i != caller)
                        .forEach(i -> i.pushLabel("Received something else..."));
        }
    }

    public void injectOfflineTab(String title, Boolean closeable)
    {
        OfflinePane offPane = new OfflinePane(title);
        offlinePanes.add(offPane);

        Tab tmp = new Tab(String.format("-- %s --", title));
        tmp.setContent(offPane.getPane());
        tmp.closableProperty().set(closeable);
        getTabs().addAll(tmp);
    }

    public void injectObservableTab(String title, Boolean closeable)
    {
        ObservablePane obsPane = new ObservablePane(this, title);
        observablePanes.add(obsPane);

        Tab tmp = new Tab(String.format("- %s %d -", title, obsPane.getId()));
        tmp.setContent(obsPane.getPane());
        tmp.closableProperty().set(closeable);
        getTabs().addAll(tmp);
    }

}
