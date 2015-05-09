package main.view.concrete.claim;

import main.model.claim.Claim;
import main.model.claim.property.PropertyClaim;

/**
 * Created by Hans Christian on 08.05.2015.
 */
public class PropertyClaimView extends ClaimView<PropertyClaim>
{
    public PropertyClaimView(Claim claim)
    {
        super(claim);

        addFields();
    }

    private void addFields()
    {

    }
}

