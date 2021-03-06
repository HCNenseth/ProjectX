package main.controller.adapter.claim;

import main.controller.ImageController;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.vehicle.CarClaim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormImageNode;
import main.view.form.node.FormNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CarClaimAdapter.java
 */
public class CarClaimAdapter extends ClaimAdapter<CarClaim>
        implements Formable<CarClaim>
{
    private FormChoiceNode<CarClaim.Type> type;
    private FormImageNode damageReportImage;

    /**
     * Instantiates a new Car claim adapter.
     *
     * @param claim the claim
     */
    public CarClaimAdapter(CarClaim claim)
    {
        super(claim);
        initFields();
    }

    /**
     * Instantiates a new Car claim adapter.
     *
     * @param person the person
     * @param insurance the insurance
     */
    public CarClaimAdapter(Person person, Insurance insurance)
    {
        super(person, insurance);
        initFields();
    }

    private void initFields()
    {
        List<CarClaim.Type> typeList = new ArrayList<>(
                Arrays.asList(CarClaim.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("car_claim_type"), typeList)
                .active(editMode ? claim.getType() : CarClaim.Type.A)
                .build();

        damageReportImage = new FormImageNode.Builder(Loc.c("claim_damage_report"))
                .build();
    }

    /**
     * Store damage report.
     */
    private void storeDamageReport()
    {
        if (damageReportImage.getData() == null) {
            return;
        }

        String fileName = ImageController.storeImage(damageReportImage.getData(),
                String.format("CarClaim-Damagereport-%s-%s",
                        claim.getId(), claim.identify().getValue()));

        claim.setDamageReportFileName(fileName);
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getVisibleNodes();
        tmp.add(type);
        tmp.add(damageReportImage);
        return tmp;
    }

    @Override
    public void callback()
    {
        if (editMode) {
            setData();
            claim.setType(type.getData());
        } else {
            claim = new CarClaim.Builder(person, insurance)
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
        storeDamageReport();
        callBackEvent.fire();
    }
}
