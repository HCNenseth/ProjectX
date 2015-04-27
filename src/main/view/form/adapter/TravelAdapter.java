package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by HansChristian on 24.04.2015.
 */
public class TravelAdapter extends InsuranceAdapter<Travel> implements Formable<Travel>
{
    public TravelAdapter(Person customer, Travel travel)
    {
        super(customer, travel);
        initNodes();
    }

    public TravelAdapter(Person customer)
    {
        super(customer);
        initNodes();

    }

    private void initNodes()
    {

    }

    private TravelAdapter()
    {
        super(null);
        return;
    }

    @Override
    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = super.getNodes();
        return tmp;
    }

    @Override
    public void callback()
    {
        if (super.getEditMode()) {
            super.getInsurance().setPremium(Integer.parseInt(super.getPremium().getValue()));
            super.getInsurance().setAmount(Integer.parseInt(super.getAmount().getValue()));
            super.getInsurance().setStatus((Status) super.getStatus().getData());

            System.out.println(super.getInsurance());
            return;
        }

        Travel travel = new Travel.Builder(super.getCustomer())
                .premium(Integer.parseInt(super.getPremium().getValue()))
                .amount(Integer.parseInt(super.getAmount().getValue()))
                .status((Status)super.getStatus().getData())
                .build();

        System.out.println(travel);

        super.setInsurance(travel);

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Travel> c)
    {
        callBackEvent.setOnAction(e -> c.accept(super.getInsurance()));
    }
}
