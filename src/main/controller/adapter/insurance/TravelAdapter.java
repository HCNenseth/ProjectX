package main.controller.adapter.insurance;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * TravelAdapter.java
 */
public class TravelAdapter extends InsuranceAdapter<Travel> implements Formable<Travel>
{
    private FormChoiceNode<Travel.Continent> continent;

    /**
     * Instantiates a new Travel adapter.
     *
     * @param customer the customer
     * @param travel the travel
     */
    public TravelAdapter(Person customer, Travel travel)
    {
        super(customer, travel);
        initNodes();
    }

    /**
     * Instantiates a new Travel adapter.
     *
     * @param customer the customer
     */
    public TravelAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private void initNodes()
    {

        List<Travel.Continent> typeList = new ArrayList<>(
                Arrays.asList(Travel.Continent.values()));
        continent = new FormChoiceNode.Builder(Loc.c("continent"), typeList)
                .required(false)
                .active(Travel.Continent.A)
                .build();
    }

    private void update()
    {
        Travel i = getInsurance();
        i.setPremium(getPremium());
        i.setAmount(getAmount());
        i.setDeductible(getDeductible());
        i.setDesc(getDescription());
        i.setStatus(getStatus());
        i.setType(continent.getData());
    }

    private void create()
    {
        Travel insurance = new Travel.Builder(getCustomer())
                .premium(getPremium())
                .amount(getAmount())
                .deductible(getDeductible())
                .status(getStatus())
                .desc(getDescription())
                .continent(continent.getData())
                .build();
        setInsurance(insurance);
        Insurance.saveNew(insurance);
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getNodes();
        tmp.add(continent);

        return tmp;
    }

    @Override
    public void callback()
    {
        if (getEditMode()) {
            update();
        } else {
            create();
        }

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Travel> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }

}
