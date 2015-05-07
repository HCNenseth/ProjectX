package main.view.concrete;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import main.controller.ClaimController;
import main.controller.InsuranceController;
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

        initButtonPanel();
        initInsuranceFields();
    }

    public void initButtonPanel()
    {
        ToolBar buttonPane = new ToolBar();

        // car
        Button b1 = new Button(Loc.c("claim"));
        b1.setOnAction(e -> ClaimController.create(insurance.getCustomer(), insurance));

        // edit person
        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> InsuranceController.edit(insurance));

        buttonPane.getItems().addAll(b1, editButton);

        gp.add(buttonPane, 0, rowNum++, 2, 1);
    }


    public void initInsuranceFields()
    {
        gp.add(new Label(Loc.c("customer")), 0, rowNum);
        gp.add(new Label(insurance.getCustomer().getName()), 1, rowNum++);

        gp.add(new Label(Loc.c("premium")), 0, rowNum);
        gp.add(new Label(insurance.getPremium() + ""), 1, rowNum++);

        gp.add(new Label(Loc.c("amount")), 0, rowNum);
        gp.add(new Label(insurance.getAmount() + ""), 1, rowNum++);

        gp.add(new Label(Loc.c("deductible")), 0, rowNum);
        gp.add(new Label(insurance.getDeductible() + ""), 1, rowNum++);

        gp.add(new Label(Loc.c("description")), 0, rowNum);
        gp.add(new Label(insurance.getDesc()), 1, rowNum++);

        gp.add(new Label(Loc.c("status")), 0, rowNum);
        gp.add(new Label(insurance.getStatus().getValue()), 1, rowNum++);

        gp.add(new Separator(), 0, rowNum++, 2, 1);
    }

    protected void addClaimsTable()
    {
        getNode().add(new Label(Loc.c("claims")), 0, rowNum++);
        getNode().add(TableController.getClaimsTable(getInsurance().getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public T getInsurance() { return insurance; }

    public StandardGridPane getNode() { return gp; }
}
