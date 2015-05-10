package main.view.concrete.claim;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import main.controller.ClaimController;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.property.House;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;
import main.view.StandardGridPane;

/**
 * Created by alex on 4/28/15.
 */
public class ClaimView<T extends Claim> extends StandardGridPane
{
    private int rowNum = 0;
    private Claim claim;

    public ClaimView(Claim claim)
    {
        this.claim = claim;

        initButtonPanel();
        initFields();
    }

    public void initButtonPanel()
    {
        ToolBar buttonPane = new ToolBar();

        // edit person
        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> ClaimController.edit(claim));

        buttonPane.getItems().addAll(editButton);

        add(buttonPane, 0, rowNum++, 2, 1);
    }

    public void initFields()
    {
        // claimId
        add(new Label(Loc.c("claim_id")), 0 , rowNum);
        add(new Label(claim.getId() + ""), 1, rowNum++);

        // customer
        add(new Label(Loc.c("customer")), 0, rowNum);
        add(new Label(claim.getCustomer().getName()), 1, rowNum++);

        // insurance
        add(new Label(Loc.c("insurance")), 0, rowNum);
        switch (claim.getInsurance().identify()) {
            case CAR:
                add(new Label(((Car) claim.getInsurance()).getType().getValue()), 1, rowNum++);
                break;
            case BOAT:
                add(new Label(((Boat) claim.getInsurance()).getType().getValue()), 1, rowNum++);
                break;
            case HOUSE:
                add(new Label(((House) claim.getInsurance()).getType().getValue()), 1, rowNum++);
                break;
            case TRAVEL:
                //gp.add(new Label(((Travel)claim.getInsurance()).getType()), 1, rowNum++);
                add(new Label("Travel-replace-me"), 1, rowNum++);
                break;
            default:
                add(new Label(Loc.c("unknown")), 1, rowNum++);
        }

        // desc
        add(new Label(Loc.c("description")), 0, rowNum);
        add(new Label(claim.getDesc()), 1, rowNum++);

        // contacts
        add(new Label(Loc.c("contacts")), 0, rowNum);
        add(new Label(claim.getContacts()), 1, rowNum++);

        // amount
        add(new Label(Loc.c("amount")), 0, rowNum);
        add(new Label(claim.getAmount() + ""), 1, rowNum++);

        // date
        add(new Label(Loc.c("date")), 0, rowNum);
        add(new Label(claim.getDate().toString()), 1, rowNum++);

        // type
        add(new Label(Loc.c("type")), 0, rowNum);
     //   add(new Label(claim.getType().getValue()), 1, rowNum++);

        // paymentStatus
        add(new Label(Loc.c("payment_status")), 0, rowNum);
        add(new Label(claim.getPaymentStatus().getValue()), 1, rowNum++);

        // status
        add(new Label(Loc.c("status")), 0, rowNum);
        add(new Label(claim.getStatus().getValue()), 1, rowNum++);
    }

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }

}
