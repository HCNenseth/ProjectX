package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.ClaimView;

/**
 * Created by alex on 4/26/15.
 */
class ClaimController
{
    private ClaimController() {}


    public static void create(Person person, Insurance insurance)
    {

    }

    public static void view(Claim claim)
    {
        ClaimView claimView = new ClaimView(claim);
        Resources.inst.getOtp().injectObservableTab(Loc.get("claim"),
                claimView.getNode(), claim, true);

    }

    public static void edit(Claim claim)
    {
        // TODO ClaimAdapter not implemented yet.
    }
}
