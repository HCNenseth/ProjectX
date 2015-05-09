package main.view.concrete.insurance;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.property.VacationHouse;
import main.view.concrete.insurance.InsuranceView;

/**
 * Created by HansChristian on 30.04.2015.
 */
public class VacationHouseView extends InsuranceView<VacationHouse>
{
    public VacationHouseView(VacationHouse vacationHouse)
    {
        super(vacationHouse);
    }

    protected void childDraw()
    {
        // Street address
        add(new Label(Loc.c("street_address")), 0, rowNum);
        add(new Label(getInsurance().getStreetAddress()), 1, rowNum++);

        // Postal code
        add(new Label(Loc.c("postal_code")), 0, rowNum);
        add(new Label(getInsurance().getPostalCode()), 1, rowNum++);

        // City
        add(new Label(Loc.c("city")), 0, rowNum);
        add(new Label(getInsurance().getCity()), 1, rowNum++);

        // Year
        add(new Label(Loc.c("year_built")), 0, rowNum);
        add(new Label(getInsurance().getYear() + ""), 1, rowNum++);

        // Material
        add(new Label(Loc.c("material")), 0, rowNum);
        add(new Label(getInsurance().getMaterial().getValue()), 1, rowNum++);

        // Standard
        add(new Label(Loc.c("property_standard")), 0, rowNum);
        add(new Label(getInsurance().getStandard().getValue()), 1, rowNum++);

        // Standard
        add(new Label(Loc.c("square_meters")), 0, rowNum);
        add(new Label(getInsurance().getSquareMeter() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
