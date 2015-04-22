package main.view.form.adapter;

import main.localization.Loc;
import main.model.insurance.vehicle.Car;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.List;

/**
 * Created by HansPetter on 22.04.2015.
 */
public class CarAdapter implements Formable {

    // TODO Did not implement the bonus field as it would be part of business logic. Good / bad ?

    /**
     * From Car
     */

    private FormValueNode owner;
    private FormValueNode regNr;
    private FormValueNode registrationYear;
    private FormValueNode mileage;
    private FormChoiceNode type;
    private FormChoiceNode propulsion;

    /**
     * From Insurance
     */
    private FormValueNode date;
    private FormValueNode customer;

    /**
     * For editing of said Car insurance we will need a reference to the object.
     */
    private Car car;

    // TODO not sure about how to manage all the datafields as ex: premium, deductible, etc.

    public CarAdapter(Car car)
    {
        this();
        this.car = car;
    }

    public CarAdapter()
    {
        owner = new FormValueNode.Builder(Loc.get("owner"))
                .error(Loc.get("owner_error"))
                .regex(StringMatcher.getFirstname())
                .build();

        regNr = new FormValueNode.Builder(Loc.get(regNr))
                .error(Loc.get("regNr_error"))
                .regex(StringMatcher.getRegNr())
                .build();
    }

    @Override
    public List<FormNode> getNodes() {
        return null;
    }

    @Override
    public void callback() {

    }
}
