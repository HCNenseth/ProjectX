package main.view.concrete.insurance;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.property.House;

/**
 * Created by alex on 4/28/15.
 */
public class HouseView extends InsuranceView<House>
{
    public HouseView(House house)
    {
        super(house);
        addFields();
    }

    private void addFields()
    {
        // Street address
        getNode().add(new Label(Loc.c("street_address")), 0, rowNum);
        getNode().add(new Label(getInsurance().getStreetAddress()), 1, rowNum++);

        // Postal code
        getNode().add(new Label(Loc.c("postal_code")), 0, rowNum);
        getNode().add(new Label(getInsurance().getPostalCode()), 1, rowNum++);

        // City
        getNode().add(new Label(Loc.c("city")), 0, rowNum);
        getNode().add(new Label(getInsurance().getCity()), 1, rowNum++);

        // Year
        getNode().add(new Label(Loc.c("year_built")), 0, rowNum);
        getNode().add(new Label(getInsurance().getYear() + ""), 1, rowNum++);

        // Material
        getNode().add(new Label(Loc.c("material")), 0, rowNum);
        getNode().add(new Label(getInsurance().getMaterial().getValue()), 1, rowNum++);

        // Standard
        getNode().add(new Label(Loc.c("property_standard")), 0, rowNum);
        getNode().add(new Label(getInsurance().getStandard().getValue()), 1, rowNum++);

        // Standard
        getNode().add(new Label(Loc.c("square_meters")), 0, rowNum);
        getNode().add(new Label(getInsurance().getSquareMeter() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
