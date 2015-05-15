package main.view.concrete.claim;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.claim.travel.TravelClaim;

/**
 * Created by Hans Christian on 08.05.2015.
 */
public class TravelClaimView extends ClaimView<TravelClaim>
{

    public TravelClaimView(TravelClaim claim)
    {
        super(claim);
    }

    @Override
    public void childFields()
    {
        // type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(claim.getType().getValue()), 1, rowNum++);

        // continent
        add(new Label(Loc.c("continent")), 0, rowNum);
        add(new Label(claim.getContinent().getValue()), 1, rowNum++);
    }
}
