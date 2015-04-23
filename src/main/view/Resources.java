package main.view;

/**
 * Created by alex on 4/23/15.
 */
public enum Resources
{
    inst;

    private ObserverTabPane observerTabPane;

    Resources() { observerTabPane = new ObserverTabPane(); }

    public ObserverTabPane getOtp()
    {
        return observerTabPane;
    }
}
