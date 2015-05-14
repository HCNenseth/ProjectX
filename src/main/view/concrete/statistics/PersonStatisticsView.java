package main.view.concrete.statistics;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import main.localization.Loc;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.util.List;

/**
 * PersonStatistics.java
 */
public class PersonStatisticsView extends StandardGridPane
{
    private List<Person> persons;
    private final int lowerBound;
    private final int upperBound;

    public PersonStatisticsView(List<Person> persons)
    {
        super(ColumnCount.ONE);

        this.persons = persons;

        lowerBound = persons.stream()
                .mapToInt(p -> p.getDateOfBirth().getYear())
                .min()
                .getAsInt();

        upperBound = persons.stream()
                .mapToInt(p -> p.getDateOfBirth().getYear())
                .max()
                .getAsInt();

        drawPlot();
    }

    private void drawPlot()
    {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setForceZeroInRange(false);
        xAxis.setLabel(Loc.c("year"));

        NumberAxis yAxis = new NumberAxis();

        StackedAreaChart<Number, Number> lineChart = new StackedAreaChart<>(xAxis, yAxis);

        lineChart.getData().add(getAgeData());

        add(lineChart, 0, 0);
    }

    private XYChart.Series<Number, Number> getAgeData()
    {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(Loc.c("age"));

        for (int i = lowerBound; i <= upperBound; i++) {
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
