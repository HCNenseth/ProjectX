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
import java.util.function.Consumer;

/**
 * Created by HansPetter on 22.04.2015.
 */
public class CarAdapter extends InsuranceAdapter implements Formable<Car>
{

    private FormValueNode regNr;
    private FormDateNode registrationYear;
    private FormValueNode mileage;
    private FormChoiceNode type;
    private FormChoiceNode propulsion;

    private Car car;

    public CarAdapter(Person customer, Car car)
    {
        super(customer, true);
        this.car = car;
        initNodes();
    }

    public CarAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private CarAdapter()
    {
        super(null);
    }

    private void initNodes()
    {
        regNr = new FormValueNode.Builder(Loc.get("regNr"))
                .error(Loc.get("regNr_error"))
                .regex(StringMatcher.getRegnr())
                .build();

        registrationYear = new FormDateNode.Builder(Loc.get("car_reg_year"),
                super.editMode() ? car.getRegistrationYear() : LocalDate.of(super.standardYear, super.standardMonth, super.standardDay))
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
        List<FormNode> tmp = super.getNodes();
        tmp.add(regNr);
        tmp.add(registrationYear);
        tmp.add(mileage);
        tmp.add(type);
        tmp.add(propulsion);

        return tmp;
    }

    @Override
    public void callback() {

        if(super.editMode())
        {
            car.setMileage(Integer.parseInt(mileage.getValue()));
            car.setRegNr(regNr.getValue());
            car.setPremium(Integer.parseInt(super.premium().getValue()));
            car.setAmount(Integer.parseInt(super.amount().getValue()));
            car.setStatus((Status) super.status().getData());

            System.out.println(car);
            return;
        }

        car = new Car.Builder(super.customer(), regNr.getValue())
                .registrationYear(registrationYear.getData())
                .mileage(Integer.parseInt(mileage.getValue()))
                .amount(Integer.parseInt(super.amount().getValue()))
                .premium(Integer.parseInt(super.premium().getValue()))
                .status((Status) super.status().getData())
                .type((Car.Type) type.getData())
                .propulsion((Car.Propulsion) propulsion.getData())
                .build();

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Car> c)
    {
        callBackEvent.setOnAction(e -> c.accept(car));
    }
}
