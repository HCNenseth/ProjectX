package main.view.concrete.insurance;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.vehicle.Car;

/**
 * Created by alex on 4/28/15.
 */
public class CarView extends InsuranceView<Car>
{
    public CarView(Car car)
    {
        super(car);
    }

    protected void childDraw()
    {
        // Owner
        add(new Label(Loc.c("owner")), 0, rowNum);
        add(new Label(getInsurance().getOwner()), 1 ,rowNum++);

        // Bonus
        add(new Label(Loc.c("bonus")), 0, rowNum);
        add(new Label(String.format("%d%%", getInsurance().getBonus())), 1, rowNum++);

        // Type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(getInsurance().getType().getValue()), 1, rowNum++);

        add(new Label(Loc.c("model_year")), 0, rowNum);
        add(new Label(getInsurance().getModelYear() + ""), 1, rowNum++);

        // registration year
        if (getInsurance().getRegistration() != null) {
            add(new Label(Loc.c("registration_year")), 0, rowNum);
            add(new Label(getInsurance().getRegistration().toString()), 1, rowNum++);
        }

        // license plate
        add(new Label(Loc.c("license_plate")), 0, rowNum);
        add(new Label(getInsurance().getLicensePlate()), 1, rowNum++);

        // horse_power
        add(new Label(Loc.c("horse_power")), 0, rowNum);
        add(new Label(getInsurance().getHorsePower() + ""), 1, rowNum++);

        // propulsion
        add(new Label(Loc.c("propulsion")), 0, rowNum);
        add(new Label(getInsurance().getPropulsion() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
