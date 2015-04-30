package main.view.concrete;

import javafx.scene.control.Label;
import main.controller.TableController;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.view.StandardGridPane;

/**
 * Created by alex on 4/28/15.
 */
public abstract class InsuranceView<T extends Insurance>
{
    private StandardGridPane gp;
    private T insurance;
    protected int rowNum = 0;

    public InsuranceView(T insurance)
    {
        this.insurance = insurance;
        gp = new StandardGridPane();

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

    protected void addClaimsTable()
    {
        getNode().add(new Label(Loc.get("claims")), 0, rowNum++);
        getNode().add(TableController.getClaimsTable(getInsurance().getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public T getInsurance() { return insurance; }

    public StandardGridPane getNode() { return gp; }
}
