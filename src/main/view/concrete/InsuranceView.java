package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.App;
import main.controller.TableController;
import main.localization.Loc;
import main.model.insurance.Insurance;

/**
 * Created by alex on 4/28/15.
 */
public abstract class InsuranceView<T extends Insurance>
{
    private GridPane gp;
    private T insurance;
    protected int rowNum = 0;

    public InsuranceView(T insurance)
    {
        this.insurance = insurance;
        gp = new GridPane();

        gp.setMinWidth(App.WIDTH - (App.WIDTH / 20));

        ColumnConstraints keyColumn = new ColumnConstraints();
        //keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(20);

        ColumnConstraints valueColumn = new ColumnConstraints();
        //valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(80);

        gp.getColumnConstraints().add(0, keyColumn);
        gp.getColumnConstraints().add(1, valueColumn);

        gp.setHgap(5);
        gp.setVgap(5);

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

    public GridPane getNode() { return gp; }
}
