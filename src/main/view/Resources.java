package main.view;

import javafx.application.HostServices;
import javafx.scene.layout.StackPane;
import main.view.concrete.SplashView;

/**
 * Resources.java
 */
public enum Resources
{
    inst;

    private SceneSwitch sceneSwitch;
    private ObserverTabPane observerTabPane;
    private SplashView splashView;
    private InfoBar infoBar;
    private HostServices hostServices;

    private StackPane stackPane;

    /**
     * Instantiates a new Resources.
     */
    Resources()
    {
        sceneSwitch = new SceneSwitch();

        stackPane = new StackPane();

        observerTabPane = new ObserverTabPane();
        splashView = new SplashView();

        stackPane.getChildren().add(observerTabPane);
        stackPane.getChildren().add(splashView.getSplashPane());

        infoBar = new InfoBar();
    }

    /**
     * Inject host services.
     *
     * @param hostServices the host services
     */
    public void injectHostServices(HostServices hostServices)
    {
        this.hostServices = hostServices;
    }

    /**
     * Gets scene switch.
     *
     * @return the scene switch
     */
    public SceneSwitch getSceneSwitch()
    {
        return sceneSwitch;
    }

    /**
     * Gets otp.
     *
     * @return the otp
     */
    public ObserverTabPane getOtp()
    {
        return observerTabPane;
    }

    /**
     * Gets info bar.
     *
     * @return the info bar
     */
    public InfoBar getInfoBar()
    {
        return infoBar;
    }

    /**
     * Gets splash view.
     *
     * @return the splash view
     */
    public SplashView getSplashView()
    {
        return splashView;
    }

    /**
     * Gets stack pane.
     *
     * @return the stack pane
     */
    public StackPane getStackPane()
    {
        return stackPane;
    }

    /**
     * Gets host services.
     *
     * @return the host services
     */
    public HostServices getHostServices()
    {
        return hostServices;
    }
}
