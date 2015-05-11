package main.view.form.adapter.claim;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import main.controller.ImageController;
import main.localization.Loc;
import main.model.Status;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * ClaimAdapter.java
 */
public abstract class ClaimAdapter<T extends Claim> implements Formable<T>
{
    private FormLabelNode personNode;
    private FormLabelNode insuranceNode;
    protected FormDateNode dateOfDamages;
    protected FormDateNode claimDate;
    protected FormValueNode contacts;
    protected FormValueNode amount;
    protected FormValueNode deductible;
    protected FormTextAreaNode description;
    protected FormImageNode image;
    protected FormChoiceNode<Claim.PaymentStatus> paymentStatus;
    protected FormChoiceNode<Status> status;

    protected Person person;
    protected Insurance insurance;
    protected T claim;
    protected boolean editMode = false;

    private final int standardYear = 2014;
    private final int standardMonth = 01;
    private final int standardDay = 01;

    protected ButtonBase callBackEvent = new ButtonBase()
    {
        @Override
        public void fire()
        {
            fireEvent(new ActionEvent());
        }
    };

    // edit constructor
    public ClaimAdapter(T claim)
    {
        if (claim != null) {
            this.claim = claim;
            person = claim.getCustomer();
            insurance = claim.getInsurance();
            editMode = true;
        }
        initNodes();
    }

    // create constructor
    public ClaimAdapter(Person person, Insurance insurance)
    {
        this.person = person;
        this.insurance = insurance;
        initNodes();
    }

    private void initNodes()
    {
        personNode = new FormLabelNode.Builder(Loc.c("customer"),
                person.getName()).build();

        insuranceNode = new FormLabelNode.Builder(Loc.c("insurance"),
                insurance.identify().getValue()).build();

        dateOfDamages = new FormDateNode.Builder(Loc.c("date_of_damages"),
                editMode ? claim.getDateOfDamages() : LocalDate.of(standardYear,
                        standardMonth,
                        standardDay))
                .build();

        claimDate = new FormDateNode.Builder(Loc.c("date_of_claim"),
                editMode ? claim.getClaimDate() : LocalDate.of(standardYear,
                        standardMonth,
                        standardDay))
                .build();

        description = new FormTextAreaNode.Builder(Loc.c("description"))
                .value(editMode ? claim.getDesc() : "")
                .error(Loc.c("description_error"))
                .regex(StringMatcher.getBaseString())
                .build();

        contacts = new FormValueNode.Builder(Loc.c("contacts"))
                .value(editMode ? claim.getContacts() : "")
                .regex(StringMatcher.getBaseString())
                .error(Loc.c("claim_contacts_error"))
                .required(false)
                .build();

        amount = new FormValueNode.Builder(Loc.c("amount"))
                .value(editMode ? Double.toString(claim.getAmount()) : "")
                .regex(StringMatcher.getFloat())
                .error(Loc.c("claim_amount_error"))
                .build();

        deductible = new FormValueNode.Builder(Loc.c("deductible"))
                .value(editMode ? Double.toString(claim.getDeductible()) : "")
                .regex(StringMatcher.getFloat())
                .error(Loc.c("claim_deductible_error"))
                .build();

        image = new FormImageNode.Builder(Loc.c("image")).build();

        List<Claim.PaymentStatus> paymentStatusList = new ArrayList<>(
                Arrays.asList(Claim.PaymentStatus.values()));
        paymentStatus = new FormChoiceNode.Builder<>(Loc.c("payment_status"),
                paymentStatusList)
                .active(editMode ? claim.getPaymentStatus() : Claim.PaymentStatus.A)
                .build();

        List<Status> statusList = new ArrayList<>(Arrays.asList(Status.values()));
        status = new FormChoiceNode.Builder<>(Loc.c("status"), statusList)
                .active(editMode ? claim.getStatus() : Status.ACTIVE)
                .build();
    }

    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(personNode);
        tmp.add(insuranceNode);
        tmp.add(dateOfDamages);
        tmp.add(claimDate);
        tmp.add(description);
        tmp.add(contacts);
        tmp.add(amount);
        tmp.add(deductible);
        tmp.add(image);
        tmp.add(paymentStatus);
        tmp.add(status);
        return tmp;
    }

    protected void setData()
    {
        claim.setDateOfDamages(dateOfDamages.getData());
        claim.setClaimDate(claimDate.getData());
        claim.setDesc(description.getValue());
        claim.setContacts(contacts.getValue());
        claim.setAmount(Double.parseDouble(amount.getValue()));
        claim.setDeductible(Double.parseDouble(deductible.getValue()));
        claim.setPaymentStatus(paymentStatus.getData());
        claim.setStatus(status.getData());
    }

    protected void storeImage()
    {
        if (image.getData() == null) { return; }

        String fileName = ImageController.storeImage(image.getData(),
                String.format("Claim-%s-%s", claim.getId(), claim.identify().getValue()));

        claim.setFilePathImage(fileName);
    }

    @Override
    public void setOnDoneAction(Consumer<T> c)
    {
        callBackEvent.setOnAction(e -> c.accept(claim));
    }
}
