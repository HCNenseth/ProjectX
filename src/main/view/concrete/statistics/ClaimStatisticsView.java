package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.localization.Loc;
import main.model.claim.Claim;
import main.view.StandardGridPane;

import java.util.*;

/**
 * ClaimsStatisticsView.java
 */
public class ClaimStatisticsView extends StandardGridPane
{
    private List<Claim> dataSet;

    private HashMap<Claim.ClaimType, XYChart.Series> series;
    private HashMap<Claim.ClaimType, CheckBox> checkboxMap;
    private Set<Claim.ClaimType> types = new HashSet<>();

    private NumberAxis xAxis, yAxis;
    private LineChart<Number, Number> lineChart;
    private PieChart chart;

    private int lowerBound;
    private int upperBound;

    private final int cellGap = 5;
    private int rowNum = 0;

    public ClaimStatisticsView(List<Claim> claims)
    {
        super(ColumnCount.ONE);

        dataSet = claims;

        lowerBound = dataSet.stream()
                .mapToInt(p -> p.getDate().getYear())
                .min()
                .getAsInt();

        upperBound = dataSet.stream()
                .mapToInt(p -> p.getDate().getYear())
                .max()
                .getAsInt();

        series = new HashMap<>();
        checkboxMap = new HashMap<>();

        xAxis = new NumberAxis();
        xAxis.setForceZeroInRange(false);
        xAxis.setLabel(Loc.c("year"));

        yAxis = new NumberAxis();
        yAxis.setLabel(Loc.c("number"));

        lineChart = new LineChart<>(xAxis, yAxis);

        claims.stream().forEach(i -> types.add(i.identify()));

        initCheckboxes();
        drawPie();
        drawPlot();
    }

    private void initCheckboxes()
    {
        AnchorPane buttonPane = new AnchorPane();
        HBox wrapper = new HBox();
        wrapper.setSpacing(cellGap);

        types.stream().forEach(t -> {
            CheckBox c = new CheckBox(t.toString());
            c.setSelected(true);
            c.setOnAction(e -> {
                if ((checkboxMap.entrySet().stream()
                        .filter(i -> i.getValue().isSelected())
                        .count() == 0)) {
                    c.setSelected(true);
                }
                if (c.isSelected()) {
                    showCurve(t);
                } else {
                    hideCurve(t);
                }
            });

            checkboxMap.put(t, c);
            wrapper.getChildren().add(c);
        });

        AnchorPane.setLeftAnchor(wrapper, 0d);
        buttonPane.getChildren().add(wrapper);

        add(buttonPane, 0, rowNum++);
    }

    private void drawPie()
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        types.stream().forEach(e ->
                        pieChartData.add(new PieChart.Data(e.toString(), (int) dataSet.stream()
                                .filter(i -> i.identify().equals(e)).count()))
        );

        chart = new PieChart(pieChartData);

        chart.setTitle(String.format("%s %d - %d",
                Loc.c("claims_distribution"), lowerBound, upperBound));

        chart.setLabelsVisible(true);
        chart.setLegendVisible(false);

        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> checkboxMap.get(Claim.ClaimType.getType(data.getName())).fire());
        }
        add(chart, 0, rowNum++);
    }

    private void drawPlot()
    {
        types.stream().forEach(e -> series.put(e, new XYChart.Series()));

        for (int i = lowerBound; i <= upperBound; i++) {
            final int x = i;

            types.stream().forEach(e -> {
                series.get(e).getData().add(new XYChart.Data<>(x,
                        (int) dataSet.stream()
                                .filter(p -> p.identify().equals(e))
                                .filter(c -> c.getDate().getYear() == x)
                                .count()));
                series.get(e).setName(e.toString());
            });
        }

        series.entrySet().stream()
                .forEach(e -> lineChart.getData().add(e.getValue()));

        add(lineChart, 0, rowNum++);
    }

    private void showCurve(Claim.ClaimType type)
    {
        series.get(type).getNode().setVisible(true);
    }

    private void hideCurve(Claim.ClaimType type)
    {
        series.get(type).getNode().setVisible(false);
    }

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }
}
