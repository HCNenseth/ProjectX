package main.view.form.adapter.insurance;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.adapter.insurance.InsuranceAdapter;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by HansChristian on 24.04.2015.
 */
public class TravelAdapter extends InsuranceAdapter<Travel> implements Formable<Travel>
{

    private FormChoiceNode continent;

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

        List<Enum> typeList = new ArrayList<>();
        for(Travel.Continent t : Travel.Continent.values())
        {
            typeList.add(t);
        }

        continent = new FormChoiceNode.Builder(Loc.c("continent"), typeList)
                .required(false)
                .active(Travel.Continent.A)
                .build();
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
            Travel i = getInsurance();
            i.setPremium(getPremium());
            i.setAmount(getAmount());
            i.setDeductible(getDeductible());
            i.setDesc(getDescription());
            i.setStatus(getStatus());

            i.setType((Travel.Continent) continent.getData());
        } else {
            Travel insurance =  new Travel.Builder(getCustomer())
                    .premium(getPremium())
                    .amount(getAmount())
                    .deductible(getDeductible())
                    .status(getStatus())
                    .desc(getDescription())
                    .continent((Travel.Continent)continent.getData())
                    .build();
            setInsurance(insurance);
            Insurance.saveNew(insurance);
        }
        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Travel> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }

}
