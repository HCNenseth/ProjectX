package main.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by HansPetter on 30.04.2015.
 */
public class SplashPane {

    private GridPane gridPane;
    private Pane searchPane, customerPane, statisticsPane;

    private Label searchLabel, customerLabel, statisticsLabel;

    private final String SEARCH = "Search";
    private final String CUSTOMER = "Customer";
    private final String STATISTICS = "Statistics";

    public SplashPane()
    {
        gridPane = new GridPane();

        searchLabel = new Label(SEARCH);
        customerLabel = new Label(CUSTOMER);
        statisticsLabel = new Label(STATISTICS);

        searchPane = new Pane();
        searchPane.setPrefSize(50, 50);
        searchPane.getChildren().add(searchLabel);

        customerPane = new Pane();
        customerPane.setPrefSize(50, 50);
        customerPane.getChildren().add(customerLabel);

        statisticsPane = new Pane();
        statisticsPane.setPrefSize(50, 50);
        statisticsPane.getChildren().add(statisticsLabel);

        gridPane.setConstraints(searchPane, 2, 2);

        gridPane.setConstraints(customerPane, 4, 2);

        gridPane.setConstraints(statisticsPane, 3, 4);

        gridPane.getChildren().addAll(searchPane, customerPane, statisticsPane);

    }

    public GridPane getSplashPane()
    {
        return gridPane;
    }
}
