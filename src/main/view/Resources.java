package main.view;

/**
 * Created by alex on 4/23/15.
 */
public enum Resources
{
    inst;

    private ObserverTabPane observerTabPane;
    private InfoBar infoBar;

    Resources()
    {
        observerTabPane = new ObserverTabPane();
        infoBar = new InfoBar();
    }

    public ObserverTabPane getOtp()
    {
        return observerTabPane;
    }

    public InfoBar getInfoBar()
    {
        return infoBar;
    }
}
