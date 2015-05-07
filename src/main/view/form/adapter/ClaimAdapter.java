package main.view.form.adapter;

import main.config.Config;
import main.localization.Loc;
import main.model.Status;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormDateNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by HansChristian on 28.04.2015.
 */
public class ClaimAdapter implements Formable<Claim>
{

    private FormValueNode description;
    private FormChoiceNode status;
    private FormValueNode contacts;
    private FormDateNode registrationDate;
    private FormDateNode dateOfDamages;

    private Person person;
    private Insurance insurance;
    private Claim claim;
    private boolean editMode = false;

    // edit constructor
    public ClaimAdapter(Claim claim)
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
        this(null);
        this.person = person;
        this.insurance = insurance;
    }

    private void initNodes()
    {
        description = new FormValueNode.Builder(Loc.c("desc"))
                .value(editMode ? claim.getDesc() : "")
                .regex(StringMatcher.getBaseString())
                .error(Loc.c("claim_description_error"))
                .build();

        contacts = new FormValueNode.Builder(Loc.c("contacts"))
                .value(editMode ? claim.getContacts() : "")
                .regex(StringMatcher.getBaseString())
                .error(Loc.c("claim_contacts_error"))
                .build();

        List<Enum> statusList = new ArrayList();
        for (Status s : Status.values()) { statusList.add(s); }

        status = new FormChoiceNode.Builder<>(Loc.c("status"), statusList)
                .active(editMode ? claim.getStatus() : Status.ACTIVE)
                .build();

        registrationDate = new FormDateNode.Builder(Loc.c("date_of_registration")
                ,LocalDate.of(Config.STANDARD_YEAR, Config.STANDARD_MONTH, Config.STANDARD_DAY))
                .build();

        dateOfDamages = new FormDateNode.Builder(Loc.c("date_of_damages")
                ,LocalDate.of(Config.STANDARD_YEAR, Config.STANDARD_MONTH, Config.STANDARD_DAY))
                .build();

    }

    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(description);
        tmp.add(contacts);
        tmp.add(dateOfDamages);
        tmp.add(registrationDate);
        tmp.add(status);
        return tmp;
    }

    @Override
    public void callback()
    {
        if (editMode) {

        } else {
            claim = new Claim.Builder(person, insurance).build();
            Claim.saveNew(claim);
        }

    }

    @Override
    public void setOnDoneAction(Consumer<Claim> c)
    {
       callBackEvent.setOnAction(e -> c.accept(claim));
    }
}
