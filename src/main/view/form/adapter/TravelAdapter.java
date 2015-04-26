package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
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
public class TravelAdapter implements Formable<Travel>
{
    private FormValueNode premium;
    private FormValueNode amount;
    private FormChoiceNode status;

    private boolean editMode = false;

    private Travel travel;
    private Person customer;

    public TravelAdapter(Person customer, Travel travel)
    {
        this();
        this.customer = customer;
        this.travel = travel;
    }

    public TravelAdapter(Person customer)
    {
        this();
        this.customer = customer;
    }

    public TravelAdapter()
    {
        premium = new FormValueNode.Builder(Loc.get("getPremium"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_premium"))
                .build();

        amount = new FormValueNode.Builder(Loc.get("getAmount"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_amount"))
                .build();

        List<Enum> statusList = new ArrayList<>();
        for(Status s : Status.values()) {
            statusList.add(s);
        }

        status = new FormChoiceNode.Builder(Loc.get("getStatus"), statusList)
                .active(Status.ACTIVE)
                .build();
    }

    @Override
    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(premium);
        tmp.add(amount);
        tmp.add(status);
        return tmp;
    }

    @Override
    public void callback()
    {
        if (editMode) {
            travel.setPremium(Integer.parseInt(premium.getValue()));
            travel.setAmount(Integer.parseInt(amount.getValue()));
            travel.setStatus((Status) status.getData());

            System.out.println(travel);
            return;
        }

        travel = new Travel.Builder(customer)
                .premium(Integer.parseInt(premium.getValue()))
                .amount(Integer.parseInt(amount.getValue()))
                .status((Status) status.getData())
                .build();

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Travel> c)
    {
        callBackEvent.setOnAction(e -> c.accept(travel));
    }
}
