package main.controller.adapter.insurance;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import main.localization.Loc;
import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * InsuranceAdapter.java
 *
 * @param <T>  - Concrete insurance adapter.
 */
public abstract class InsuranceAdapter<T extends Insurance>
        implements Formable<T>
{

    private FormLabelNode customerNode;
    private FormValueNode amount;
    private FormValueNode deductible;
    private FormValueNode premium;
    private FormTextAreaNode desc;
    private FormChoiceNode<Status> status;

    private Person customer;
    private boolean editMode = false;
    private T insurance = null;

    /**
     * The Call back event.
     */
    protected ButtonBase callBackEvent = new ButtonBase()
    {
        @Override
        public void fire()
        {
            fireEvent(new ActionEvent());
        }
    };

    /**
     * Instantiates a new Insurance adapter.
     *
     * @param customer the customer
     * @param insurance the insurance
     */
    public InsuranceAdapter(Person customer, T insurance)
    {
        if (customer == null || insurance == null)
            throw new IllegalStateException("Error in data");

        this.customer = customer;
        this.insurance = insurance;
        this.editMode = true;
        initialize();
    }

    /**
     * Instantiates a new Insurance adapter.
     *
     * @param customer the customer
     */
    public InsuranceAdapter(Person customer)
    {
        if (customer == null)
            throw new IllegalStateException("Cannot operate on customer-less insurance!");

        this.customer = customer;
        initialize();
    }

    private void initialize()
    {
        customerNode = new FormLabelNode.Builder(Loc.c("customer"), customer.getName())
                .build();

        premium = new FormValueNode.Builder(Loc.c("premium"))
                .regex(StringMatcher.getFloat())
                .value(editMode ? Double.toString(insurance.getPremium()) : "")
                .error(Loc.c("error_premium"))
                .build();

        amount = new FormValueNode.Builder(Loc.c("amount"))
                .regex(StringMatcher.getFloat())
                .value(editMode ? Double.toString(insurance.getAmount()) : "")
                .error(Loc.c("error_amount"))
                .build();

        deductible = new FormValueNode.Builder(Loc.c("deductible"))
                .regex(StringMatcher.getFloat())
                .value(editMode ? Double.toString(insurance.getDeductible()) : "")
                .error(Loc.c("error_amount"))
                .build();

        desc = new FormTextAreaNode.Builder(Loc.c("desc"))
                .regex(StringMatcher.getBaseString())
                .value(editMode ? insurance.getDesc() : "")
                .error(Loc.c("error_desc"))
                .required(false)
                .build();

        List<Status> statusList = new ArrayList<>(Arrays.asList(Status.values()));
        status = new FormChoiceNode.Builder<>(Loc.c("status"), statusList)
                .active(editMode ? insurance.getStatus() : Status.ACTIVE)
                .build();
    }

    /**
     * Gets nodes.
     *
     * @return the nodes
     */
    protected List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(customerNode);
        tmp.add(amount);
        tmp.add(premium);
        tmp.add(deductible);
        tmp.add(desc);
        tmp.add(status);
        return tmp;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
/* GETTERS */
    protected double getAmount()
    {
        return Double.parseDouble(amount.getValue());
    }

    /**
     * Gets deductible.
     *
     * @return the deductible
     */
    protected double getDeductible()
    {
        return Double.parseDouble(deductible.getValue());
    }

    /**
     * Gets premium.
     *
     * @return the premium
     */
    protected double getPremium()
    {
        return Double.parseDouble(premium.getValue());
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    protected String getDescription()
    {
        return desc.getValue();
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    protected Status getStatus()
    {
        return status.getData();
    }

    /**
     * Gets edit mode.
     *
     * @return the edit mode
     */
    protected boolean getEditMode()
    {
        return editMode;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    protected Person getCustomer()
    {
        return customer;
    }

    /**
     * Gets insurance.
     *
     * @return the insurance
     */
    protected T getInsurance()
    {
        return insurance;
    }

    /**
     * Sets insurance.
     *
     * @param insurance the insurance
     */
/* SETTERS */
    protected void setInsurance(T insurance)
    {
        this.insurance = insurance;
    }

    /* OVERRIDES */
    @Override
    public void setOnDoneAction(Consumer<T> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
