package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.property.PropertyClaim;
import main.model.claim.travel.TravelClaim;
import main.model.claim.vehicle.BoatClaim;
import main.model.claim.vehicle.CarClaim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.claim.ClaimView;
import main.view.form.Form;
import main.view.form.adapter.claim.*;

/**
 * ClaimController.java
 */
public class ClaimController
{
    private ClaimController() {}

    public static void create(Claim.ClaimType type, Person person, Insurance insurance)
    {
        String title;
        Form f = new Form();
        ClaimAdapter<? extends Claim> claimAdapter;

        switch (type) {
            case CAR:
                title = "new_car_claim";
                claimAdapter = new CarClaimAdapter(person, insurance);
                break;
            case BOAT:
                title = "new_boat_claim";
                claimAdapter = new BoatClaimAdapter(person, insurance);
                break;
            case PROPERTY:
                title = "new_property_claim";
                claimAdapter = new PropertyClaimAdapter(person, insurance);
                break;
            case TRAVEL:
                title = "new_travel_claim";
                claimAdapter = new TravelClaimAdapter(person, insurance);
                break;
            default:
                throw new IllegalStateException("Unknown claim type");
        }

        claimAdapter.setOnDoneAction(ClaimController::view);
        f.injectAdapter(claimAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c(title),
                f.getForm(), true);
    }

    public static void view(Claim claim)
    {
        // Remove all tabs dealing with this object
        Resources.inst.getOtp().closeObservableTabs(claim);

        ClaimView claimView = new ClaimView(claim);
        Resources.inst.getOtp().injectObservableTab(Loc.c("claim"),
                claimView.getNode(), claim, true);
    }

    public static void edit(Claim claim)
    {
        // Remove all tabs dealing with this object
        Resources.inst.getOtp().closeObservableTabs(claim);

        String title;
        Form f = new Form();
        ClaimAdapter<? extends Claim> claimAdapter;

        switch (claim.identify()) {
            case CAR:
                title = "edit_car_claim";
                claimAdapter = new CarClaimAdapter((CarClaim) claim);
                break;
            case BOAT:
                title = "edit_boat_claim";
                claimAdapter = new BoatClaimAdapter((BoatClaim) claim);
                break;
            case PROPERTY:
                title = "edit_property_claim";
                claimAdapter = new PropertyClaimAdapter((PropertyClaim) claim);
                break;
            case TRAVEL:
                title = "edit_travel_claim";
                claimAdapter = new TravelClaimAdapter((TravelClaim) claim);
                break;
            default:
                throw new IllegalStateException("Unknown claim type");
        }

        claimAdapter.setOnDoneAction(ClaimController::view);
        f.injectAdapter(claimAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c(title),
                f.getForm(), claim, true);
    }
}
