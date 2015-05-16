package main.view.concrete.claim;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.claim.vehicle.BoatClaim;

/**
 * BoatClaimView.java
 */
public class BoatClaimView extends ClaimView<BoatClaim>
{
    /**
     * Instantiates a new Boat claim view.
     *
     * @param claim the claim
     */
    public BoatClaimView(BoatClaim claim)
    {
        super(claim);
    }

    @Override
    public void childFields()
    {
        // type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(claim.getType().getValue()), 1, rowNum++);
    }
}
