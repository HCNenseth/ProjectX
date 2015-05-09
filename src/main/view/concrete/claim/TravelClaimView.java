package main.view.concrete.claim;

import main.model.claim.Claim;
import main.model.claim.travel.TravelClaim;

/**
 * Created by Hans Christian on 08.05.2015.
 */
public class TravelClaimView extends ClaimView<TravelClaim>
{
    public TravelClaimView(Claim claim)
    {
        super(claim);

        addFields();
    }

    private void addFields()
    {

    }
}
