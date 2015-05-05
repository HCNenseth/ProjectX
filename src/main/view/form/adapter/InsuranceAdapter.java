package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormLabelNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;

// TODO this code is not tested.

public abstract class InsuranceAdapter<T extends Insurance>
{

    private FormLabelNode customerNode;

    private FormValueNode amount;

    private FormValueNode desc;

    private FormValueNode premium;

    private FormChoiceNode status;

    private Person customer;

    private boolean editMode = false;

    private T insurance = null;

    private FormValueNode deductible;

    public InsuranceAdapter(Person customer, T ref)
    {
        this.customer = customer;
        this.insurance = ref;
        this.editMode = true;
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
         customerNode = new FormLabelNode.Builder(Loc.c("customer"), customer.getName())
                 .build();

         premium = new FormValueNode.Builder(Loc.c("premium"))
                .regex(StringMatcher.getDigit())
                .value(editMode ? Double.toString(insurance.getPremium()) : "")
                .error(Loc.c("error_premium"))
                .build();

         amount = new FormValueNode.Builder(Loc.c("amount"))
                .regex(StringMatcher.getDigit())
                .value(editMode ? Double.toString(insurance.getAmount()) : "")
                .error(Loc.c("error_amount"))
                .build();

         deductible = new FormValueNode.Builder(Loc.c("deductible"))
                .regex(StringMatcher.getDigit())
                .value(editMode ? Double.toString(insurance.getDeductible()) : "")
                .error(Loc.c("error_amount"))
                .build();

         desc = new FormValueNode.Builder(Loc.c("desc"))
                .regex(StringMatcher.getBaseString())
                .value(editMode ? insurance.getDesc() : "")
                .error(Loc.c("error_desc"))
                 .required(false)
                .build();

         List<Enum> statusList = new ArrayList<>();
         for (Status s : Status.values()) { statusList.add(s); }

         status = new FormChoiceNode.Builder<>(Loc.c("status"), statusList)
                .active(Status.ACTIVE)
                .build();
    }

    protected List<FormNode> getNodes(List<FormNode> tmp)
    {
        tmp.add(0, customerNode);
        tmp.add(amount);
        tmp.add(premium);
        tmp.add(deductible);
        tmp.add(desc);
        tmp.add(status);
        return tmp;
    }

    protected FormValueNode getAmount()
    {
        return amount;
    }

    protected FormValueNode getPremium()
    {
        return premium;
    }

    protected FormValueNode getDesc()
    {
        return desc;
    }

    protected FormChoiceNode getStatus()
    {
        return status;
    }

    protected FormValueNode getDeductible() { return deductible; }

    protected boolean getEditMode()
    {
        return editMode;
    }

    protected Person getCustomer()
    {
        return customer;
    }

    protected T getInsurance()
    {
        return insurance;
    }

    protected void setInsurance(T insurance)
    {
        this.insurance = insurance;
    }

}
