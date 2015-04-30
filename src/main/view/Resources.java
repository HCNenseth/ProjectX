package main.view;

import javafx.scene.layout.StackPane;
import main.view.concrete.SplashView;

/**
 * Created by alex on 4/23/15.
 */
public enum Resources
{
    inst;

    private ObserverTabPane observerTabPane;
    private SplashView splashView;
    private InfoBar infoBar;

    private StackPane stackPane;

    Resources()
    {
        stackPane = new StackPane();

        observerTabPane = new ObserverTabPane();
        splashView = new SplashView();

        stackPane.getChildren().add(splashView.getSplashPane());
        stackPane.getChildren().add(observerTabPane);

        infoBar = new InfoBar();
    }

    public ObserverTabPane getOtp()
    {
        return observerTabPane;
    }

    public InfoBar getInfoBar() { return infoBar; }

    public SplashView getSplashView() { return splashView; }

    public StackPane getStackPane() { return stackPane; }
}
