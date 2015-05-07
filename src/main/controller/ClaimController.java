package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.claim.ClaimView;
import main.view.form.Form;
import main.view.form.adapter.claim.CarClaimAdapter;
import main.view.form.adapter.claim.ClaimAdapter;

/**
 * Created by alex on 4/26/15.
 */
public class ClaimController
{
    private ClaimController() {}

    public static void create(Claim.ClaimType type, Person person, Insurance insurance)
    {
        Form f = new Form();
        ClaimAdapter claimAdapter;
        switch (type) {
            case CAR:
                claimAdapter = new CarClaimAdapter(person, insurance);
                break;
            case BOAT:
                claimAdapter = new CarClaimAdapter(person, insurance);
                break;
            case PROPERTY:
                claimAdapter = new CarClaimAdapter(person, insurance);
                break;
            case TRAVEL:
                claimAdapter = new CarClaimAdapter(person, insurance);
                break;
            default:
                throw new IllegalStateException("Unknown claim type");
        }
        //claimAdapter.setOnDoneAction(ClaimController::view);
        f.injectAdapter(claimAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c("new_claim"),
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
