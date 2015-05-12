package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.view.StandardGridPane;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class InsuranceStatisticsView extends StandardGridPane
{
    private List<Insurance> insurances;
    private HashMap<String, Integer> data;
    private PieChart chart;

    public InsuranceStatisticsView(List<Insurance> insurances)
    {
        super(1);
        this.insurances = insurances;

        // generate data
        generateData();

        // show data as pie chart
        drawPieChart();

        // show data as graph
        drawBarChart();
    }

    public void generateData()
    {
        data = new HashMap<>();

        for(InsuranceType type : InsuranceType.values()) {
            data.put(type.getValue(), (int) insurances.stream()
                    .filter(i -> i.identify().equals(type)).count());
        }
    }

    public void drawPieChart()
    {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        data.entrySet().stream().forEach(e ->
            pieChartData.add(new PieChart.Data(e.getKey(), e.getValue())));

        chart = new PieChart(pieChartData);
        add(chart, 0, 0);
    }

    public void drawBarChart()
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle(Loc.c("summary"));
        xAxis.setLabel(Loc.c("insurance"));
        yAxis.setLabel(Loc.c("value"));

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Total");

        data.entrySet().stream().forEach(e ->
                series1.getData().add(new XYChart.Data<>(e.getKey(), e.getValue())));

        bc.getData().add(series1);

        add(bc, 0, 2);
    }

    @Override
    public StandardGridPane getNode() { return this; }
}
