package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import main.localization.Loc;
import main.model.claim.Claim;
import main.view.StandardGridPane;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class ClaimStatisticsView extends StandardGridPane
{
    private List<Claim> claims;
    private final int lowerBound;
    private final int upperBound;

    public ClaimStatisticsView(List<Claim> claims)
    {
        super(1);
        this.claims = claims;

        lowerBound = claims.stream()
                .mapToInt(p -> p.getDate().getYear())
                .min()
                .getAsInt();

        upperBound = claims.stream()
                .mapToInt(p -> p.getDate().getYear())
                .max()
                .getAsInt();

        drawPlot();

     }

    private void drawPlot() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel(Loc.c("month"));

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle(Loc.c("claims") + " " + lowerBound + " - " + upperBound);

        List<XYChart.Series> series = new LinkedList<>();

        for(Claim.ClaimType type : Claim.ClaimType.values())
        {
            series.add(new XYChart.Series());
        }

        for(int i = lowerBound; i < upperBound; i++)
        {
            final int x = i;

            int counter = 0;
            for(Claim.ClaimType type : Claim.ClaimType.values())
            {
                series.get(counter++).getData().add(new XYChart.Data<>(x, (int) claims.stream()
                        .filter(p -> p.identify().equals(type))
                        .filter(c -> c.getDate().getYear() == x)
                        .count()
                ));
            }
        }

        for(XYChart.Series s : series)
        {
            lineChart.getData().add(s);
        }

        add(lineChart, 0, 0);

    }





    @Override
    public StandardGridPane getNode() { return this; }
}
