package main.view.concrete;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.property.VacationHouse;

/**
 * Created by HansChristian on 30.04.2015.
 */
public class VacationHouseView extends InsuranceView<VacationHouse>
{
    public VacationHouseView(VacationHouse vacationHouse)
    {
        super(vacationHouse);
        addFields();
    }

    public void addFields()
    {
        // Street address
        getNode().add(new Label(Loc.get("street_address")), 0, rowNum);
        getNode().add(new Label(getInsurance().getStreetAddress()), 1, rowNum++);

        // Postal code
        getNode().add(new Label(Loc.get("postal_code")), 0, rowNum);
        getNode().add(new Label(getInsurance().getPostalCode()), 1, rowNum++);

        // City
        getNode().add(new Label(Loc.get("city")), 0, rowNum);
        getNode().add(new Label(getInsurance().getCity()), 1, rowNum++);

        // Year
        getNode().add(new Label(Loc.get("year_built")), 0, rowNum);
        getNode().add(new Label(getInsurance().getYear() + ""), 1, rowNum++);

        // Material
        getNode().add(new Label(Loc.get("material")), 0, rowNum);
        getNode().add(new Label(getInsurance().getMaterial().getValue()), 1, rowNum++);

        // Standard
        getNode().add(new Label(Loc.get("house_standard")), 0, rowNum);
        getNode().add(new Label(getInsurance().getStandard().getValue()), 1, rowNum++);

        // Standard
        getNode().add(new Label(Loc.get("square_meters")), 0, rowNum);
        getNode().add(new Label(getInsurance().getSquareMeter() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
