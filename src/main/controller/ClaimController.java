package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.claim.ClaimView;
import main.view.form.Form;
import main.view.form.adapter.claim.*;

/**
 * Created by alex on 4/26/15.
 */
public class ClaimController
{
    private ClaimController() {}

    public static void create(Claim.ClaimType type, Person person, Insurance insurance)
    {
        String title = "";
        Form f = new Form();
        ClaimAdapter claimAdapter;
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
                title = "new_propery_claim";
                claimAdapter = new PropertyClaimAdapter(person, insurance);
                break;
            case TRAVEL:
                title = "new_travel_claim";
                claimAdapter = new TravelClaimAdapter(person, insurance);
                break;
            default:
                throw new IllegalStateException("Unknown claim type");
        }
        //claimAdapter.setOnDoneAction(ClaimController::view);
        f.injectAdapter(claimAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c(title),
                f.getForm(), true);
    }

    public static void view(Claim claim)
    {
        ClaimView claimView = new ClaimView(claim);
        Resources.inst.getOtp().injectObservableTab(Loc.c("claim"),
                claimView.getNode(), claim, true);

    }

    public static void edit(Claim claim)
    {
        Resources.inst.getOtp().closeObservableTabs(claim);

        Form f = new Form();
        //ClaimAdapter claimAdapter = new ClaimAdapter(claim);
        //claimAdapter.setOnDoneAction(ClaimController::view);
        //f.injectAdapter(claimAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c("claim"),
                f.getForm(), claim, true);
    }
}
