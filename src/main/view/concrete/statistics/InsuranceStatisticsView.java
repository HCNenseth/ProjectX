package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.view.StandardGridPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InsuranceStatisticsView extends StandardGridPane
{
    private List<Insurance> insurances;
    private HashMap<String, Integer> data;
    private HashMap<LocalDate, HashMap<String, Integer>> histData;
    private PieChart chart;
    private final int lowerBound;
    private final int upperBound;
    private final NumberAxis xAxis, yAxis;
    private final LineChart<Number, Number> lineChart;

    public InsuranceStatisticsView(List<Insurance> insurances)
    {
        super(1);
        this.insurances = insurances;

        lowerBound = insurances.stream()
                .mapToInt(i -> i.getDate().getYear())
                .min()
                .getAsInt();

        upperBound = insurances.stream()
                .mapToInt(i -> i.getDate().getYear())
                .max()
                .getAsInt();

        xAxis = new NumberAxis(lowerBound, upperBound, 1);
        yAxis = new NumberAxis();

        xAxis.setLabel(Loc.c("year"));

        lineChart = new LineChart<>(xAxis, yAxis);

        drawPie();
        drawPlot();
    }

    private void drawPlot()
    {
        lineChart.setTitle(Loc.c(Loc.l("insurances") + " " + lowerBound + " - " + upperBound));

        List<XYChart.Series> series = new LinkedList<>();

        for(InsuranceType t : InsuranceType.values())
        {
            series.add(new XYChart.Series());
        }

        int counter = 0;
        for(int i = lowerBound; i < upperBound; i++)
        {
            int x = i;


            for(InsuranceType t : InsuranceType.values())
            {
                series.get(counter).getData().add(new XYChart.Data<>(x, insurances.stream()
                            .filter(p -> p.identify().equals(t))
                            .filter(c -> c.getDate().getYear() == x)
                            .count()
                ));
                series.get(counter++).setName(Loc.c(t.getValue()));
            }
        }

        for(XYChart.Series s : series)
        {
            lineChart.getData().add(s);
        }

        add(lineChart, 0, 2);
    }

    private void drawPlot(String name)
    {
        if(getNode().getChildren().size() > 1)
        {
            getNode().getChildren().remove(1);
        }

        InsuranceType type = InsuranceType.HOUSE;
        for(InsuranceType t : InsuranceType.values())
        {
            if(name.equals(t.getValue()))
            {
                type = t;
            }
        }


        lineChart.setTitle(Loc.c(type.getValue()) + " " + Loc.l("insurances") + " " + lowerBound + " - " + upperBound);

        XYChart.Series series = new XYChart.Series();
        for(int i = lowerBound; i < upperBound; i++)
        {
            int x = i;
            InsuranceType t = type;

            series.getData().add(new XYChart.Data<>(x, (int) insurances.stream()
                    .filter(p -> p.identify().equals(t))
                    .filter(d -> d.getDate().getYear() == x)
                    .count()
                    ));
        }

        lineChart.getData().add(series);

        add(lineChart, 0, 2);
    }

    private void drawPie()
    {
        data = new HashMap<>();
        for(InsuranceType type : InsuranceType.values())
        {
            data.put(type.getValue(), (int) insurances.stream()
                    .filter(i -> i.identify().equals(type)).count());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        data.entrySet().stream().forEach(e ->
                pieChartData.add(new PieChart.Data(e.getKey(), e.getValue())));

        chart = new PieChart(pieChartData);
        chart.setTitle(Loc.c("insurance_distribution"));
        chart.setLabelsVisible(true);

        for(final PieChart.Data data : chart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                drawPlot(data.getName());
            });
        }

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial");

        add(chart, 0, 0);

    }

    @Override
    public StandardGridPane getNode() { return this; }
}
