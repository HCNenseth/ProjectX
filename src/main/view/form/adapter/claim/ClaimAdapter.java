package main.view.form.adapter.claim;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
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
import java.util.List;

/**
 * Created by HansChristian on 28.04.2015.
 */
public abstract class ClaimAdapter<T extends Claim> implements Formable<T>
{
    private FormLabelNode personNode;
    private FormLabelNode insuranceNode;
    private FormDateNode dateofDamages;
    private FormDateNode claimDate;
    private FormTextAreaNode description;
    private FormValueNode contacts;
    private FormValueNode amount;
    private FormValueNode deductible;
    private FormImageNode image;
    private FormChoiceNode paymentStatus;
    private FormChoiceNode status;

    private Person person;
    private Insurance insurance;
    private Claim claim;
    protected boolean editMode = false;

    private final int standardYear = 2014;
    private final int standardMonth = 01;
    private final int standrdDay = 01;


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

        dateofDamages = new FormDateNode.Builder(Loc.c("date_of_damages"),
                editMode ? claim.getDateOfDamages() : LocalDate.of(standardYear,
                        standardMonth,
                        standrdDay))
                .build();

        claimDate = new FormDateNode.Builder(Loc.c("date_of_claim"),
                editMode ? claim.getClaimDate() : LocalDate.of(standardYear,
                        standardMonth,
                        standrdDay))
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

        List<Enum> paymentStatusList = new ArrayList();
        for (Claim.PaymentStatus s : Claim.PaymentStatus.values()) {
            paymentStatusList.add(s);
        }

        paymentStatus = new FormChoiceNode.Builder<>(Loc.c("payment_status"),
                paymentStatusList)
                .active(editMode ? claim.getPaymentStatus() : Claim.PaymentStatus.A)
                .build();

        List<Enum> statusList = new ArrayList();
        for (Status s : Status.values()) {
            statusList.add(s);
        }

        status = new FormChoiceNode.Builder<>(Loc.c("status"), statusList)
                .active(editMode ? claim.getStatus() : Status.ACTIVE)
                .build();

    }

    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(personNode);
        tmp.add(insuranceNode);
        tmp.add(dateofDamages);
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
}
