package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.config.Config;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.property.House;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;

/**
 * Created by alex on 4/28/15.
 */
public class ClaimView
{
    private GridPane gp;
    private int rowNum = 0;
    private Claim claim;

    public ClaimView(Claim claim)
    {
        gp = new GridPane();
        gp.setMinWidth(Config.WIDTH - (Config.WIDTH / 20));

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

        initFields();
    }

    public void initFields()
    {
        // customer
        gp.add(new Label(Loc.get("customer")), 0, rowNum);
        gp.add(new Label(claim.getCustomer().getName()), 1, rowNum++);

        // insurance
        gp.add(new Label(Loc.get("insurance")), 0, rowNum);
        switch (claim.getInsurance().identify()) {
            case CAR:
                gp.add(new Label(((Car)claim.getInsurance()).getType()), 1, rowNum++);
                break;
            case BOAT:
                gp.add(new Label(((Boat)claim.getInsurance()).getType()), 1, rowNum++);
                break;
            case HOUSE:
                gp.add(new Label(((House)claim.getInsurance()).getType().getValue()), 1, rowNum++);
                break;
            case TRAVEL:
                //gp.add(new Label(((Travel)claim.getInsurance()).getType()), 1, rowNum++);
                gp.add(new Label("Travel-replace-me"), 1, rowNum++);
                break;
            default:
                gp.add(new Label(Loc.get("unknown")), 1, rowNum++);
        }

        // desc
        gp.add(new Label(Loc.get("description")), 0, rowNum);
        gp.add(new Label(claim.getDesc()), 1, rowNum++);

        // contacts
        gp.add(new Label(Loc.get("contacts")), 0, rowNum);
        gp.add(new Label(claim.getContacts()), 1, rowNum++);

        // amount
        gp.add(new Label(Loc.get("amount")), 0, rowNum);
        gp.add(new Label(claim.getAmount() + ""), 1, rowNum++);

        // date
        gp.add(new Label(Loc.get("date")), 0, rowNum);
        gp.add(new Label(claim.getDate().toString()), 1, rowNum++);

        // type
        gp.add(new Label(Loc.get("type")), 0, rowNum);
        gp.add(new Label(claim.getType().getValue()), 1, rowNum++);

        // paymentStatus
        gp.add(new Label(Loc.get("payment_status")), 0, rowNum);
        gp.add(new Label(claim.getPaymentStatus().getValue()), 1, rowNum++);

        // status
        gp.add(new Label(Loc.get("status")), 0, rowNum);
        gp.add(new Label(claim.getStatus().getValue()), 1, rowNum++);
    }

    public GridPane getNode()
    {
        return gp;
    }

}
