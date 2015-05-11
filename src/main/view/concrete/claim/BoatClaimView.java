package main.view.concrete.claim;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.claim.vehicle.BoatClaim;

/**
 * BoatClaimView.java
 */
public class BoatClaimView extends ClaimView<BoatClaim>
{
    public BoatClaimView(BoatClaim claim)
    {
        super(claim);
        addFields();
    }

    private void addFields()
    {
        // type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(claim.identify().getValue()), 1, rowNum++);
    }
}
