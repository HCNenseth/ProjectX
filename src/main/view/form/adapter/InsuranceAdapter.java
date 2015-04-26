package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;

// TODO this code is not tested. 

abstract class InsuranceAdapter {

    /**
     * Insurance amount.
     * Maximum possible payment for a claim.
     */
    private FormValueNode amount;

    /**
     * Insurance description.
     * A short description.
     */
    private FormValueNode desc;

    /**
     * Insurance premium.
     * The yearly cost of the insurance.
     */
    private FormValueNode premium;

    /**
     * Insurance status.
     * ACTIVE
     * INACTIVE
     * PENDING
     * INCOMPLETE
     */
    private FormChoiceNode status;

    /**
     * Reference to the customer.
     */

    private Person customer;

    /**
     * Create new Insurance or edit existing.
     */
    private boolean editMode = false;


    /**
     * Constants for the FormDateNode
     */
    private static int standardYear = 1970;
    private static int standardMonth = 1;
    private static int standardDay = 1;

    public InsuranceAdapter(Person customer, boolean editMode)
    {
        this.customer = customer;
        this.editMode = editMode;
        initialize();
    }

    public InsuranceAdapter(Person customer)
    {
        if(customer != null)
        {
            this.customer = customer;
            initialize();
        }
    }

    private void initialize()
    {
        premium = new FormValueNode.Builder(Loc.get("premium"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_premium"))
                .build();

        amount = new FormValueNode.Builder(Loc.get("amount"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_amount"))
                .build();

        desc = new FormValueNode.Builder(Loc.get("desc"))
                .regex(StringMatcher.getBaseString())
                .error(Loc.get("error_desc"))
                .build();

        List<Enum> statusList = new ArrayList<>();
        for(Status s : Status.values())
        {
            statusList.add(s);
        }

        status = new FormChoiceNode.Builder(Loc.get("status"), statusList)
                .active(Status.ACTIVE)
                .build();
    }

    protected List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();

        tmp.add(amount);
        tmp.add(premium);
        tmp.add(desc);
        tmp.add(status);

        return tmp;
    }

    protected FormValueNode amount()
    {
        return this.amount;
    }

    protected FormValueNode premium()
    {
        return this.premium;
    }

    protected FormValueNode desc()
    {
        return this.desc;
    }

    protected FormChoiceNode status()
    {
        return this.status;
    }

    protected boolean editMode()
    {
        return this.editMode;
    }

}
