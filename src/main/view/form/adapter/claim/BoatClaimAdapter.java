package main.view.form.adapter.claim;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.vehicle.BoatClaim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BoatClaimAdapter.java
 */
public class BoatClaimAdapter extends ClaimAdapter<BoatClaim>
        implements Formable<BoatClaim>
{
    private FormChoiceNode<BoatClaim.Type> type;

    public BoatClaimAdapter(BoatClaim claim)
    {
        super(claim);
        initFields();
    }

    public BoatClaimAdapter(Person person, Insurance insurance)
    {
        super(person, insurance);
        initFields();
    }

    private void initFields()
    {
        List<BoatClaim.Type> typeList = new ArrayList<>(
                Arrays.asList(BoatClaim.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("boat_claim_type"), typeList)
                .active(editMode ? claim.getType() : BoatClaim.Type.A)
                .build();
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getVisibleNodes();
        tmp.add(type);
        return tmp;
    }

    @Override
    public void callback()
    {
        if (editMode) {
            setData();
            claim.setType(type.getData());
        } else {
            claim = new BoatClaim.Builder(person, insurance)
                    .dateOfDamages(dateOfDamages.getData())
                    .claimDate(claimDate.getData())
                    .desc(description.getValue())
                    .contacts(contacts.getValue())
                    .amount(Double.parseDouble(amount.getValue()))
                    .deductible(Double.parseDouble(deductible.getValue()))
                    .paymentStatus(paymentStatus.getData())
                    .status(status.getData())
                    .type(type.getData())
                    .build();
            Claim.saveNew(claim);
        }
        storeImage();
        callBackEvent.fire();
    }
}
