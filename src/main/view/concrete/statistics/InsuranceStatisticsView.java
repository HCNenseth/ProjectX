package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.view.StandardGridPane;
import sun.plugin.javascript.navig.Anchor;

import java.time.LocalDate;
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
    private NumberAxis xAxis, yAxis;
    private LineChart<Number, Number> lineChart;
    private Button refresh;

    public InsuranceStatisticsView(List<Insurance> insurances)
    {
        super(ColumnCount.ONE);

        this.insurances = insurances;

        lowerBound = insurances.stream()
                .mapToInt(i -> i.getDate().getYear())
                .min()
                .getAsInt();

        upperBound = insurances.stream()
                .mapToInt(i -> i.getDate().getYear())
                .max()
                .getAsInt();

        initButtonPane();
        drawPie();
        drawPlot();
    }

    private void initButtonPane()
    {
        AnchorPane buttonPane = new AnchorPane();
        HBox buttons = new HBox();
        for(InsuranceType t : InsuranceType.values())
        {
            Button tmp = new Button(t.getValue());
            tmp.setOnAction(e -> drawPlot(t.getValue()));
            buttons.getChildren().add(tmp);
        }

        refresh = new Button(Loc.c("refresh"));
        refresh.setOnAction(e -> drawPlot());
        buttons.getChildren().add(refresh);

        AnchorPane.setLeftAnchor(buttons, 0d);
        buttonPane.getChildren().add(buttons);
        add(buttonPane, 0, 0);
    }

    private void drawPlot()
    {
        if(getNode().getChildren().size() > 2)
        {
            getNode().getChildren().remove(2);
        }

        xAxis = new NumberAxis(lowerBound, upperBound, 1);
        yAxis = new NumberAxis();

        xAxis.setLabel(Loc.c("year"));

        lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle(Loc.c("insurances") + " " + lowerBound + " - " + upperBound);

        List<XYChart.Series> series = new LinkedList<>();

        for(InsuranceType t : InsuranceType.values())
        {
            series.add(new XYChart.Series());
        }


        for(int i = lowerBound; i < upperBound; i++)
        {
            int x = i;

            int counter = 0;
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
        if(getNode().getChildren().size() > 2)
        {
            getNode().getChildren().remove(2);
        }

        InsuranceType type = InsuranceType.HOUSE;
        for(InsuranceType t : InsuranceType.values())
        {
            if(name.equals(t.getValue()))
            {
                type = t;
            }
        }

        xAxis = new NumberAxis(lowerBound, upperBound, 1);
        yAxis = new NumberAxis();

        xAxis.setLabel(Loc.c("year"));

        lineChart = new LineChart<>(xAxis, yAxis);

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
        lineChart.setLegendVisible(false);


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
        chart.setLegendVisible(false);

        for(final PieChart.Data data : chart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                drawPlot(data.getName());
            });
        }

        add(chart, 0, 1);

    }

    @Override
    public StandardGridPane getNode() { return this; }
}
