package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.view.StandardGridPane;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class InsuranceStatisticsView extends StandardGridPane
{
    private List<Insurance> insurances;
    private HashMap<String, Integer> data, histData;
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

        for (InsuranceType type : InsuranceType.values()) {
            data.put(type.getValue(), (int) insurances.stream()
                    .filter(i -> i.identify().equals(type)).count());
        }
    }

    public boolean isQuarterlyEqual(LocalDate date)
    {
        LocalDate b = LocalDate.now();

        return (date.getYear() == b.getYear())
                && (inPartition(date.getMonthValue()) == inPartition(b.getMonthValue()));
        /*
        if(date.getYear() == b.getYear())
        {
            if( quarter ( date.getMonthValue() ) == quarter(b.getMonthValue() ) )
            {
                return true;
            }
        }
        return false;
        */
    }

    public int inPartition(int x)
    {
        int[][] p = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};

        for (int i = 0; i < p.length; i++)
            for (int j = 0; j < p[i].length; j++)
                if (p[i][j] == x) return i;

        return -1;
    }

    public int quarter(int month)
    {
        if(month <= 3)
        {
            return 1;
        }
        else if(month <= 6)
        {
            return 2;
        }
        else if(month <= 9)
        {
            return 3;
        }
        else
        {
            return 4;
        }
    }

    public void generateHistoricData()
    {
        histData = new HashMap<>();
        LocalDate startDate = LocalDate.of(2010, 1, 1);
        LocalDate endDate = LocalDate.now();

        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(3) )
        {

            for(InsuranceType type : InsuranceType.values() )
            {
                histData.put(type.getValue(), insurances.stream()
                        .filter(i -> i.identify().equals(type)).filter()
            }
        }

        for(InsuranceType type : InsuranceType.values()) {
            histData.put(type.getValue(), (int) insurances.stream()
                    .filter(i -> i.identify().equals(type)).count());
        }

    }

    public void drawPieChart()
    {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        data.entrySet().stream().forEach(e ->
            pieChartData.add(new PieChart.Data(e.getKey(), e.getValue())));

        chart = new PieChart(pieChartData);
        chart.setTitle(Loc.c("insurance_distribution"));
        chart.setLabelsVisible(false);
        chart.setLegendSide(Side.TOP);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial");

        add(chart, 0, 0);
    }

    public void drawBarChart()
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle(Loc.c("summary"));
        xAxis.setLabel(Loc.c("insurance"));
        yAxis.setLabel(Loc.c("number"));

        XYChart.Series series1 = new XYChart.Series();

        data.entrySet().stream().forEach(e ->
                series1.getData().add(new XYChart.Data<>(e.getKey(), e.getValue())));

        bc.getData().add(series1);

        add(bc, 0, 2);
    }

    public void drawLineChart()
    {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(Loc.c("year"));

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle(Loc.c("Total cost per insurance type"));

        XYChart.Series series = new XYChart.Series();



    }

    @Override
    public StandardGridPane getNode() { return this; }
}
