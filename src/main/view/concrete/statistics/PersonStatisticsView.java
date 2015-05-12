package main.view.concrete.statistics;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import main.localization.Loc;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class PersonStatisticsView extends StandardGridPane
{
    private List<Person> persons;
    private final int lowerBound = 1930;
    private final int upperBound = 2010;
    private final int spacing = 5;

    public PersonStatisticsView(List<Person> persons)
    {
        super(1);
        this.persons = persons;

        drawPlot();
    }

    private void drawPlot()
    {
        NumberAxis xAxis = new NumberAxis(lowerBound  -1, upperBound, spacing);
        xAxis.setLabel(Loc.c("year"));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(Loc.c("count"));

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.getData().add(getAgeData());

        add(lineChart, 0, 0);
    }

    private XYChart.Series<Number, Number> getAgeData()
    {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(Loc.c("age"));

        for (int i = lowerBound; i < upperBound; i++) {
            final int x = i;

            series.getData().add(new XYChart.Data<>(x,
                    (int) persons.stream()
                            .filter(p -> p.getDateOfBirth().getYear() == x).count()));

        }

        return series;
    }


    @Override
    public StandardGridPane getNode() { return this; }
}
