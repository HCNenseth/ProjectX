package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.localization.Loc;
import main.model.insurance.Insurance;

/**
 * Created by alex on 4/28/15.
 */
public class InsuranceView<T extends Insurance>
{
    private GridPane gp;
    private T insurance;
    protected int rowNum = 0;

    public InsuranceView(T insurance)
    {
        this.insurance = insurance;
        gp = new GridPane();

        initInsuranceFields();
    }

    public void initInsuranceFields()
    {
        gp.add(new Label(Loc.get("customer")), 0, rowNum);
        gp.add(new Label(insurance.getCustomer().getName()), 1, rowNum++);

        gp.add(new Label(Loc.get("premium")), 0, rowNum);
        gp.add(new Label(insurance.getPremium() + ""), 1, rowNum++);

        gp.add(new Label(Loc.get("amount")), 0, rowNum);
        gp.add(new Label(insurance.getAmount() + ""), 1, rowNum++);

        gp.add(new Label(Loc.get("deductible")), 0, rowNum);
        gp.add(new Label(insurance.getDeductible() + ""), 1, rowNum++);

        gp.add(new Label(Loc.get("description")), 0, rowNum);
        gp.add(new Label(insurance.getDesc()), 1, rowNum++);
    }

    public T getInsurance() { return insurance; }

    public GridPane getNode() { return gp; }
}
