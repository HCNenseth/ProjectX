package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.insurance.vehicle.Car;
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

/**
 * Created by HansPetter on 22.04.2015.
 */
public class CarAdapter implements Formable {

    private FormValueNode premium;
    private FormValueNode amount;
    private FormValueNode desc;
    private FormValueNode regNr;
    private FormDateNode registrationYear;
    private FormValueNode mileage;
    private FormChoiceNode type;
    private FormChoiceNode status;
    private FormChoiceNode propulsion;

    private boolean editMode = false;

    private Car car;
    private Person customer;

    /**
     * For use in the FormDateNode
     */
    private int standardYear = 1970;
    private int standardMonth = 1;
    private int standardDay = 1;

    public CarAdapter(Person customer, Car car)
    {
        this.customer = customer;
        this.car = car;
        editMode = true;
        initNodes();
    }

    public CarAdapter(Person customer)
    {
        this.customer = customer;
        initNodes();
    }

    private CarAdapter() { return; }

    private void initNodes()
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

        regNr = new FormValueNode.Builder(Loc.get("regNr"))
                .error(Loc.get("regNr_error"))
                .regex(StringMatcher.getRegnr())
                .build();

        registrationYear = new FormDateNode.Builder(Loc.get("car_reg_year"),
                editMode ? car.getRegistrationYear() : LocalDate.of(standardYear, standardMonth, standardDay))
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

        List<Enum> statusList = new ArrayList<>();
        for(Status s : Status.values())
        {
            statusList.add(s);
        }

        status = new FormChoiceNode.Builder(Loc.get("status"), statusList)
                .active(Status.ACTIVE)
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
        tmp.add(premium);
        tmp.add(status);
        tmp.add(amount);
        tmp.add(regNr);
        tmp.add(registrationYear);
        tmp.add(mileage);
        tmp.add(type);
        tmp.add(propulsion);

        return tmp;
    }

    @Override
    public void callback() {

        if(editMode)
        {
            car.setMileage(Integer.parseInt(mileage.getValue()));
            car.setRegNr(regNr.getValue());
            car.setPremium(Integer.parseInt(premium.getValue()));
            car.setAmount(Integer.parseInt(amount.getValue()));
            car.setStatus((Status) status.getData());

            System.out.println(car);
            return;
        }

        car = new Car.Builder(customer, regNr.getValue())
                .registrationYear(registrationYear.getData())
                .mileage(Integer.parseInt(mileage.getValue()))
                .amount(Integer.parseInt(amount.getValue()))
                .premium(Integer.parseInt(premium.getValue()))
                .status((Status) status.getData())
                .type((Car.Type) type.getData())
                .propulsion((Car.Propulsion) propulsion.getData())
                .build();

        System.out.println(car);
        return;
    }
}
