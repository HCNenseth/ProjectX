package main.view.concrete.statistics;

import javafx.scene.control.Label;
import main.model.insurance.Insurance;
import main.view.StandardGridPane;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class InsuranceStatisticsView extends StandardGridPane
{
    private List<Insurance> insurances;

    public InsuranceStatisticsView(List<Insurance> insurances)
    {
        super(1);
        this.insurances = insurances;

        add(new Label("Fill me up baby!"), 0, 0);
    }

    @Override
    public StandardGridPane getNode() { return this; }
}
