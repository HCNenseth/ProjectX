package main.view.concrete;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.config.Config;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.controller.StatisticsController;

/**
 * SplashView.java
 */
public class SplashView
{
    private GridPane gridPane;
    private HBox hbox;
    private Image searchIcon, customerIcon, statisticsIcon;
    private ImageView searchIV, customerIV, statisticsIV;
    private Button search, customer, statistics;

    /**
     * Instantiates a new Splash view.
     */
    public SplashView()
    {
        hbox = new HBox();
        hbox.setSpacing(50);
        gridPane = new GridPane();

        searchIcon = new Image(Config.IMAGES + "search.png");
        customerIcon = new Image(Config.IMAGES + "customer.png");
        statisticsIcon = new Image(Config.IMAGES + "chart.png");

        searchIV = new ImageView();
        searchIV.setImage(searchIcon);
        searchIV.setFitWidth(80);
        searchIV.setFitHeight(80);
        searchIV.setPreserveRatio(true);
        searchIV.setSmooth(true);
        searchIV.setCache(true);

        customerIV = new ImageView();
        customerIV.setImage(customerIcon);
        customerIV.setFitHeight(80);
        customerIV.setFitWidth(80);
        customerIV.setPreserveRatio(true);
        customerIV.setSmooth(true);
        customerIV.setCache(true);

        statisticsIV = new ImageView();
        statisticsIV.setImage(statisticsIcon);
        statisticsIV.setFitWidth(80);
        statisticsIV.setFitHeight(80);
        statisticsIV.setPreserveRatio(true);
        statisticsIV.setSmooth(true);
        statisticsIV.setCache(true);

        search = new Button("", searchIV);
        search.setOnAction(e -> Mediator.inst.router(Signal.SEARCH, null));

        customer = new Button("", customerIV);
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));

        statistics = new Button("", statisticsIV);
        statistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.PERSON)));

        hbox.getChildren().addAll(search, customer, statistics);

        gridPane.getChildren().add(hbox);
        gridPane.setAlignment(Pos.CENTER);

    }

    /**
     * Show void.
     */
    public void show()
    {
        gridPane.setVisible(true);
    }

    /**
     * Hide void.
     */
    public void hide()
    {
        gridPane.setVisible(false);
    }

    /**
     * Gets splash pane.
     *
     * @return the splash pane
     */
    public GridPane getSplashPane()
    {
        return gridPane;
    }
}
