package main.view.concrete;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.vehicle.Boat;

/**
 * Created by alex on 4/28/15.
 */
public class BoatView extends InsuranceView<Boat>
{
    public BoatView(Boat boat)
    {
        super(boat);

        addFields();
    }

    private void addFields()
    {
        // Type
        getNode().add(new Label(Loc.get("type")), 0, rowNum);
        getNode().add(new Label(getInsurance().getType()), 1, rowNum++);

        // registration year
        getNode().add(new Label(Loc.get("registration_year")), 0, rowNum);
        getNode().add(new Label(getInsurance().getRegistrationYear() + ""), 1, rowNum++);

        // license plate
        getNode().add(new Label(Loc.get("license_plate")), 0, rowNum);
        getNode().add(new Label(getInsurance().getLicencePlate()), 0, rowNum++);

        // length
        getNode().add(new Label(Loc.get("horse_power")), 0, rowNum);
        getNode().add(new Label(getInsurance().getHorsePower() + ""), 1, rowNum++);

        // propulsion
        getNode().add(new Label(Loc.get("propulsion")), 0, rowNum);
        getNode().add(new Label(getInsurance().getPropulsion() + ""), 1, rowNum++);

        addClaimsTable();
    }
}