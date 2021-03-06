package main.controller.adapter.claim;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.property.PropertyClaim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PropertyClaimAdapter.java
 */
public class PropertyClaimAdapter extends ClaimAdapter<PropertyClaim>
        implements Formable<PropertyClaim>
{
    private FormChoiceNode<PropertyClaim.Type> type;

    /**
     * Instantiates a new Property claim adapter.
     *
     * @param claim the claim
     */
    public PropertyClaimAdapter(PropertyClaim claim)
    {
        super(claim);
        initFields();
    }

    /**
     * Instantiates a new Property claim adapter.
     *
     * @param person the person
     * @param insurance the insurance
     */
    public PropertyClaimAdapter(Person person, Insurance insurance)
    {
        super(person, insurance);
        initFields();
    }

    private void initFields()
    {
        List<PropertyClaim.Type> typeList = new ArrayList<>(
                Arrays.asList(PropertyClaim.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("property_claim_type"), typeList)
                .active(editMode ? claim.getType() : PropertyClaim.Type.A)
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
            claim = new PropertyClaim.Builder(person, insurance)
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
