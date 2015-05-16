package main.view.concrete.claim;


import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.claim.vehicle.CarClaim;
import main.view.stage.ImageStage;

/**
 * CarClaimView.java
 */
public class CarClaimView extends ClaimView<CarClaim>
{
    /**
     * Instantiates a new Car claim view.
     *
     * @param claim the claim
     */
    public CarClaimView(CarClaim claim)
    {
        super(claim);
    }

    @Override
    public void childFields()
    {
        // type
        add(new Label(Loc.c("type")), 0, rowNum);
        add(new Label(claim.getType().getValue()), 1, rowNum++);

        // damage report
        if (!claim.getDamageReportFileName().equals("")) {
            add(new Label(Loc.c("damage_report")), 0, rowNum);
            Hyperlink hyperlink = new Hyperlink(claim.getDamageReportFileName());
            hyperlink.setOnAction(e ->
                            new ImageStage().showImage(claim.getDamageReportFile())
            );
            add(hyperlink, 1, rowNum++);
        }
    }
}
