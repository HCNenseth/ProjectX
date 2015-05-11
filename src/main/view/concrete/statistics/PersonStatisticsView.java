package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import main.model.person.Person;
import main.view.Resources;
import main.view.StandardGridPane;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class PersonStatisticsView extends StandardGridPane
{
    private List<Person> persons;

    public PersonStatisticsView(List<Person> persons)
    {
        super(1);
        this.persons = persons;

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setData(getChartData());
        lineChart.setTitle("speculations");
        Resources.inst.getStackPane().getChildren().add(lineChart);

        // add(new Label("Fill me up baby!"), 0, 0);
    }

    private ObservableList<XYChart.Series<String, Double>> getChartData()
    {
        double javaValue = persons.size();
/*                .stream()
                .mapToInt(p -> p.getInsurances().size())
                .average()
                .getAsDouble();
*/
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();

        XYChart.Series<String, Double> persons = new XYChart.Series<>();

        persons.setName("Persons");

        for (int i = 2011; i < 2021; i++) {
            persons.getData().add(new XYChart.Data(Integer.toString(i), javaValue));
            javaValue = javaValue + 4 * Math.random() - .2;

        }
        answer.addAll(persons);
        return answer;
    }


    @Override
    public StandardGridPane getNode() { return this; }
}
