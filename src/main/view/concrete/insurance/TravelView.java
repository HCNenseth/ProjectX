package main.view.concrete.insurance;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.travel.Travel;

/**
 * Created by alex on 4/28/15.
 */
public class TravelView extends InsuranceView<Travel>
{
    public TravelView(Travel travel)
    {
        super(travel);
    }

    protected void childDraw()
    {
        // continent
        add(new Label(Loc.c("continent")), 0, rowNum);
        add(new Label(getInsurance().getType() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
