package main.controller.adapter.claim;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.travel.TravelClaim;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TravelClaimAdapter.java
 */
public class TravelClaimAdapter extends ClaimAdapter<TravelClaim>
        implements Formable<TravelClaim>
{
    private FormChoiceNode<TravelClaim.Type> type;
    private FormChoiceNode<Travel.Continent> continent;

    /**
     * Instantiates a new Travel claim adapter.
     *
     * @param claim the claim
     */
    public TravelClaimAdapter(TravelClaim claim)
    {
        super(claim);
        initFields();
    }

    /**
     * Instantiates a new Travel claim adapter.
     *
     * @param person the person
     * @param insurance the insurance
     */
    public TravelClaimAdapter(Person person, Insurance insurance)
    {
        super(person, insurance);
        initFields();
    }

    private void initFields()
    {
        List<TravelClaim.Type> typeList = new ArrayList<>(
                Arrays.asList(TravelClaim.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("travel_claim_type"), typeList)
                .active(editMode ? claim.getType() : TravelClaim.Type.A)
                .build();

        List<Travel.Continent> continentList = new ArrayList<>(
                Arrays.asList(Travel.Continent.values()));
        continent = new FormChoiceNode.Builder<>(Loc.c("continent"), continentList)
                .active(editMode ? claim.getContinent() : Travel.Continent.A)
                .build();
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getVisibleNodes();
        tmp.add(type);
        tmp.add(continent);
        return tmp;
    }

    @Override
    public void callback()
    {
        if (editMode) {
            setData();
            claim.setType(type.getData());
            claim.setContinent(continent.getData());
        } else {
            claim = new TravelClaim.Builder(person, insurance)
                    .dateOfDamages(dateOfDamages.getData())
                    .claimDate(claimDate.getData())
                    .desc(description.getValue())
                    .contacts(contacts.getValue())
                    .amount(Double.parseDouble(amount.getValue()))
                    .deductible(Double.parseDouble(deductible.getValue()))
                    .paymentStatus(paymentStatus.getData())
                    .status(status.getData())
                    .continent(continent.getData())
                    .type(type.getData())
                    .build();
            Claim.saveNew(claim);
        }
        storeImage();
        callBackEvent.fire();
    }
}
