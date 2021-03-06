package main.view.concrete.insurance;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.controller.ClaimController;
import main.controller.InsuranceController;
import main.controller.PersonController;
import main.controller.TableController;
import main.localization.Loc;
import main.model.Model;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.view.StandardGridPane;
import main.view.table.SugarTable;

/**
 * InsuranceView.java
 * @param <T>  the type parameter
 */
public abstract class InsuranceView<T extends Insurance> extends StandardGridPane
{
    private T insurance;

    protected int rowNum = 0;
    private boolean drawn = false;
    private int cellGap = 5;

    /**
     * Instantiates a new Insurance view.
     *
     * @param insurance the insurance
     */
    public InsuranceView(T insurance)
    {
        this.insurance = insurance;
        draw();
    }

    private void draw()
    {
        if (drawn) {
            getChildren().clear();
            rowNum = 0;
        }

        initButtonPanel();
        initInsuranceFields();
        childDraw();
        drawn = true;
    }

    private void initButtonPanel()
    {
        AnchorPane buttonPane = new AnchorPane();

        Button b1 = new Button(Loc.c("view_customer"));
        b1.setOnAction(e -> PersonController.view(insurance.getCustomer()));

        Button b2 = new Button(Loc.c("new_claim"));
        switch (insurance.identify()) {
            case CAR:
                b2.setOnAction(e -> ClaimController.create(Claim.ClaimType.CAR,
                        insurance.getCustomer(), insurance));
                break;
            case BOAT:
                b2.setOnAction(e -> ClaimController.create(Claim.ClaimType.BOAT,
                        insurance.getCustomer(), insurance));
                break;
            case VACATION_HOUSE:
            case HOUSE:
                b2.setOnAction(e -> ClaimController.create(Claim.ClaimType.PROPERTY,
                        insurance.getCustomer(), insurance));
                break;
            case TRAVEL:
                b2.setOnAction(e -> ClaimController.create(Claim.ClaimType.TRAVEL,
                        insurance.getCustomer(), insurance));
                break;
        }

        HBox leftAlignedButtons = new HBox(b1, b2);
        leftAlignedButtons.setSpacing(cellGap);
        AnchorPane.setLeftAnchor(leftAlignedButtons, 0d);

        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> InsuranceController.edit(insurance));

        Button refreshButton = new Button(Loc.c("refresh"));
        refreshButton.setOnAction(e -> draw());

        HBox rightAlignedButtons = new HBox(editButton, refreshButton);
        rightAlignedButtons.setSpacing(cellGap);
        AnchorPane.setRightAnchor(rightAlignedButtons, 0d);

        buttonPane.getChildren().addAll(leftAlignedButtons, rightAlignedButtons);

        add(buttonPane, 0, rowNum++, 2, 1);
    }


    private void initInsuranceFields()
    {
        add(new Label(Loc.c("insurance_id")), 0, rowNum);
        add(new Label(insurance.getId() + ""), 1, rowNum++);

        add(new Label(Loc.c("customer")), 0, rowNum);
        add(new Label(insurance.getCustomer().getName()), 1, rowNum++);

        add(new Label(Loc.c("date")), 0, rowNum);
        add(new Label(insurance.getDate().toString()), 1, rowNum++);

        add(new Label(Loc.c("premium")), 0, rowNum);
        add(new Label(insurance.getPremium() + ""), 1, rowNum++);

        add(new Label(Loc.c("amount")), 0, rowNum);
        add(new Label(insurance.getAmount() + ""), 1, rowNum++);

        add(new Label(Loc.c("deductible")), 0, rowNum);
        add(new Label(insurance.getDeductible() + ""), 1, rowNum++);

        add(new Label(Loc.c("description")), 0, rowNum);
        add(new Label(insurance.getDesc()), 1, rowNum++);

        add(new Label(Loc.c("status")), 0, rowNum);
        add(new Label(insurance.getStatus().getValue()), 1, rowNum++);

        add(new Separator(), 0, rowNum++, 2, 1);
    }

    /**
     * Add claims table.
     */
    protected void addClaimsTable()
    {
        getNode().add(new SugarTable(TableController.getClaimsTable(getInsurance().getClaims().stream())
                        .getTable(), Model.ModelType.CLAIM, Loc.c("claims")).getNode(),
                0, rowNum++, 2, 1);
    }

    /**
     * Gets insurance.
     *
     * @return the insurance
     */
    public T getInsurance()
    {
        return insurance;
    }

    /**
     * Child draw.
     */
    protected abstract void childDraw();

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }
}
