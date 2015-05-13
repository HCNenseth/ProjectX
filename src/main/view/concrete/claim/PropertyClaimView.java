package main.view.concrete.claim;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.claim.property.PropertyClaim;

/**
 * PropertyClaimView.java
 */
public class PropertyClaimView extends ClaimView<PropertyClaim>
{
    public PropertyClaimView(PropertyClaim claim)
    {
        super(claim);
        addFields();
    }

    private void addFields()
    {
        // type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(claim.getType().getValue()), 1, rowNum++);
    }
}
