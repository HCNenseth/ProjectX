package main.view.form.adapter;

import main.localization.Loc;
import main.model.insurance.Type;
import main.model.insurance.vehicle.Car;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
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
     * For editing of said Car insurance we will need a reference to the object.
     */
    private Car car;
    private Person customer;

    // TODO not sure about how to manage all the datafields as ex: premium, deductible, etc.

    public CarAdapter(Person customer, Car car)
    {
        this();
        this.customer = customer;
        this.car = car;
    }

    public CarAdapter(Person customer)
    {
        this();
        this.customer = customer;
    }

    public CarAdapter()
    {
        owner = new FormValueNode.Builder(Loc.get("owner"))
                .error(Loc.get("owner_error"))
                .regex(StringMatcher.getFirstname())
                .build();

        regNr = new FormValueNode.Builder(Loc.get("regNr"))
                .error(Loc.get("regNr_error"))
                .regex(StringMatcher.getRegnr())
                .build();

        registrationYear = new FormValueNode.Builder(Loc.get("registrationYear"))
                .error(Loc.get("registrationYear_error"))
                .regex(StringMatcher.getYear())
                .required(false)
                .build();

        mileage = new FormValueNode.Builder(Loc.get("mileage"))
                .error(Loc.get("mileage"))
                .regex(StringMatcher.getDigit())
                .required(false)
                .build();

        List<Enum> typeList = new ArrayList<>();
        for(Car.Type t : Car.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder(Loc.get("car_type"), typeList)
                .required(false)
                .active(Car.Type.A)
                .build();

        List<Enum> propulsionList = new ArrayList<>();
        for(Car.Propulsion p : Car.Propulsion.values())
        {
            propulsionList.add(p);
        }

        propulsion = new FormChoiceNode.Builder(Loc.get("car_propulsion"), propulsionList)
                .required(false)
                .active(Car.Propulsion.A)
                .build();
    }

    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(owner);
        tmp.add(regNr);
        tmp.add(registrationYear);
        tmp.add(mileage);
        tmp.add(type);
        tmp.add(propulsion);

        return tmp;
    }

    @Override
    public void callback() {
        if(car == null)
        {
            car = new Car.Builder(customer, regNr.getValue())
                    .registrationYear( Integer.parseInt(registrationYear.getValue()) )
                    .milage(Integer.parseInt(mileage.getValue()))
                    .type((Car.Type)type.getData())
                    .propulsion((Car.Propulsion)propulsion.getData())
                    .build();

            System.out.println(car);
            return;
        }

        car.setMileage(Integer.parseInt(mileage.getValue()));
        car.setRegNr(regNr.getValue());

        System.out.println(car);
    }
}
