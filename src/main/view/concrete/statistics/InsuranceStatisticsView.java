package main.view.concrete.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import main.model.Storage;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.model.insurance.Type;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    }

    public void generateData()
    {
        data = new HashMap<>();
        for(InsuranceType type : InsuranceType.values())
        {
            data.put(type.getValue(), 0);
            for(Insurance i : Insurance.getInsurances())
            {
                if(i.identify().equals(type))
                {
                    int count = data.containsKey(type.getValue()) ? data.get(type.getValue()) : 0;
                    data.put(type.getValue(), count + 1);
                }
            }
        }

        for(InsuranceType type : InsuranceType.values())
        {
            System.out.println(type + " " + data.get(type.getValue()));
        }
    }

    public void drawPieChart()
    {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for(Map.Entry<String, Integer> type : data.entrySet())
        {
            pieChartData.add(new PieChart.Data(type.getKey(), type.getValue()));
        }

        chart = new PieChart(pieChartData);
        //chart.setTitle("Insurances");
        add(chart, 0, 0);
    }

    public void drawGraph()
    {
        
    }

    @Override
    public StandardGridPane getNode() { return this; }
}
