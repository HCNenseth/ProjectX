package observer.tabs.POC;

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
    private List<ObservablePane> panes;

    public ObserverTabPane()
    {
        panes = new LinkedList<>();
    }

    @Override
    public void update(Observable obj, Object arg)
    {
        ObservablePane caller = (ObservablePane)obj;
        panes.stream()
                .filter(i -> i != caller)
                .forEach(i -> i.pushLabel("Received notification"));
    }

    public void injectTab(String title, Boolean closeable)
    {
        ObservablePane obsPane = new ObservablePane(this, title);
        panes.add(obsPane);

        Tab tmp = new Tab(String.format("-- %s --",title));
        tmp.setContent(obsPane.getPane());
        tmp.closableProperty().set(closeable);
        getTabs().addAll(tmp);
    }

}
