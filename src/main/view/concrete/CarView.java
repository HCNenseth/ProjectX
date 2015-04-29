package main.view.concrete;

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

        addFields();
    }

    private void addFields()
    {
        // Type
        getNode().add(new Label(Loc.get("type")), 0, rowNum);
        getNode().add(new Label(getInsurance().getType()), 1, rowNum++);

        getNode().add(new Label(Loc.get("model_year")), 0, rowNum);
        getNode().add(new Label(getInsurance().getModelYear() + ""), 1, rowNum++);

        // registration year
        if (getInsurance().getRegistration() != null) {
            getNode().add(new Label(Loc.get("registration_year")), 0, rowNum);
            getNode().add(new Label(getInsurance().getRegistration().toString()), 1, rowNum++);
        }

        // license plate
        getNode().add(new Label(Loc.get("license_plate")), 0, rowNum);
        getNode().add(new Label(getInsurance().getLicencePlate()), 1, rowNum++);

        // horse_power
        getNode().add(new Label(Loc.get("horse_power")), 0, rowNum);
        getNode().add(new Label(getInsurance().getHorsePower() + ""), 1, rowNum++);

        // propulsion
        getNode().add(new Label(Loc.get("propulsion")), 0, rowNum);
        getNode().add(new Label(getInsurance().getPropulsion() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
