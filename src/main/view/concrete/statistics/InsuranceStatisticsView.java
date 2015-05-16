package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.view.StandardGridPane;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * InsuranceStatisticsView.java
 */
public class InsuranceStatisticsView extends StandardGridPane
{
    private List<Insurance> insurances;
    private Set<InsuranceType> types = new HashSet<>();
    private HashMap<InsuranceType, XYChart.Series> series = new HashMap<>();
    private HashMap<InsuranceType, CheckBox> checkboxMap = new HashMap<>();

    private NumberAxis xAxis, yAxis;
    private LineChart<Number, Number> lineChart;
    private PieChart chart;

    private final int lowerBound;
    private final int upperBound;

    private final int cellGap = 5;
    private int rowNum = 0;

    /**
     * Instantiates a new Insurance statistics view.
     *
     * @param insurances the insurances
     */
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

        xAxis = new NumberAxis();
        xAxis.setForceZeroInRange(false);
        xAxis.setLabel(Loc.c("year"));

        yAxis = new NumberAxis();
        yAxis.setLabel(Loc.c("number"));

        lineChart = new LineChart<>(xAxis, yAxis);

        insurances.stream().forEach(i -> types.add(i.identify()));

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
            CheckBox c = new CheckBox(t.getValue());
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
                        pieChartData.add(new PieChart.Data(e.getValue(), (int) insurances.stream()
                                .filter(i -> i.identify().equals(e)).count()))
        );

        chart = new PieChart(pieChartData);

        chart.setTitle(String.format("%s %s - %s",
                Loc.c("insurance_distribution"), lowerBound, upperBound));

        chart.setLabelsVisible(true);
        chart.setLegendVisible(false);

        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> checkboxMap.get(InsuranceType.getType(data.getName())).fire());
        }
        add(chart, 0, rowNum++);
    }

    private void drawPlot()
    {

        types.stream().forEach(e -> series.put(e, new XYChart.Series()));

        for (int i = lowerBound; i < upperBound; i++) {
            final int x = i;

            types.stream().forEach(e -> {
                series.get(e).getData().add(new XYChart.Data<>(x,
                        (int) insurances.stream()
                                .filter(p -> p.identify().equals(e))
                                .filter(c -> c.getDate().getYear() == x)
                                .count()));
                series.get(e).setName(e.getValue());
            });
        }

        series.entrySet().stream()
                .forEach(e -> lineChart.getData().add(e.getValue()));

        add(lineChart, 0, rowNum++);
    }

    private void showCurve(InsuranceType type)
    {
        series.get(type).getNode().setVisible(true);
    }

    private void hideCurve(InsuranceType type)
    {
        series.get(type).getNode().setVisible(false);
    }

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }
}
