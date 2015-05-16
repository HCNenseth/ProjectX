package main.view.concrete.insurance;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.vehicle.Boat;

/**
 * BoatView.java
 */
public class BoatView extends InsuranceView<Boat>
{
    /**
     * Instantiates a new Boat view.
     *
     * @param boat the boat
     */
    public BoatView(Boat boat)
    {
        super(boat);
    }

    protected void childDraw()
    {
        // Owner
        add(new Label(Loc.c("owner")), 0, rowNum);
        add(new Label(getInsurance().getOwner()), 1, rowNum++);

        // Type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(getInsurance().getType().getValue()), 1, rowNum++);

        // registration year
        add(new Label(Loc.c("registration_year")), 0, rowNum);
        add(new Label(getInsurance().getRegistration() + ""), 1, rowNum++);

        // license plate
        add(new Label(Loc.c("license_plate")), 0, rowNum);
        add(new Label(getInsurance().getLicensePlate()), 1, rowNum++);

        // length
        add(new Label(Loc.c("horse_power")), 0, rowNum);
        add(new Label(getInsurance().getHorsePower() + ""), 1, rowNum++);

        // propulsion
        add(new Label(Loc.c("propulsion")), 0, rowNum);
        add(new Label(getInsurance().getPropulsion() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
