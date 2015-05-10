package main.view.concrete.claim;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.travel.TravelClaim;
import main.model.insurance.travel.Travel;

/**
 * Created by Hans Christian on 08.05.2015.
 */
public class TravelClaimView extends ClaimView<TravelClaim>
{
    private int rowNum = 0;
    private Travel travel;

    public TravelClaimView(Claim claim)
    {
        super(claim);

        addFields();
    }

    private void addFields()
    {
        add(new Label(Loc.c("continent")), 0, rowNum);
        add(new Label(travel.getType().getValue()), 1, rowNum++);
    }
}
