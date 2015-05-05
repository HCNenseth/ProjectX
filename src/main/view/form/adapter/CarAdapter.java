package main.view.form.adapter;

import main.config.Config;
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
public class CarAdapter extends InsuranceAdapter<Car> implements Formable<Car>
{

    private FormValueNode licencePlate;
    //private FormValueNode owner;
    private FormDateNode registration;
    private FormValueNode mileage;
    private FormChoiceNode type;
    private FormChoiceNode propulsion;
    private FormValueNode horsePower;

    public CarAdapter(Person customer, Car car)
    {
        super(customer, car);
        initNodes();
    }

    public CarAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private void initNodes()
    {
        licencePlate = new FormValueNode.Builder(Loc.c("licence_plate"))
                .error(Loc.c("licence_plate_error"))
                .value(getEditMode() ? getInsurance().getLicencePlate() : "")
                .regex(StringMatcher.getRegnr())
                .build();

        // This does not make any sense!
        /*
        owner = new FormValueNode.Builder(Loc.c("owner"))
                .value(getEditMode() ? getInsurance().getOwner().getName() : "")
                .regex(StringMatcher.getBaseString())
                .build();
        */

        registration = new FormDateNode.Builder(Loc.c("vehicle_registration"),
                 getEditMode() ? getInsurance().getRegistration() : LocalDate.of(Config.STANDARD_YEAR, Config.STANDARD_MONTH, Config.STANDARD_DAY))
                .build();

        mileage = new FormValueNode.Builder(Loc.c("mileage"))
                .error(Loc.c("mileage_error"))
                .value(getEditMode() ? Integer.toString(getInsurance().getMileage()) : "")
                .regex(StringMatcher.getDigit())
                .build();

        horsePower = new FormValueNode.Builder(Loc.c("vehicle_horse_power"))
                .regex(StringMatcher.getDigit())
                .value(getEditMode() ? Integer.toString(getInsurance().getHorsePower()) : "")
                .error(Loc.c("vehicle_horse_power_error"))
                .build();

        List<Enum> typeList = new ArrayList<>();
        for (Car.Type t : Car.Type.values()) { typeList.add(t); }

        type = new FormChoiceNode.Builder(Loc.c("car_type"), typeList)
                .required(false)
                .active(getEditMode() ? getInsurance().getType() : Car.Type.A)
                .build();


        List<Enum> propulsionList = new ArrayList<>();
        for(Car.Propulsion p : Car.Propulsion.values())
        {
            propulsionList.add(p);
        }

        propulsion = new FormChoiceNode.Builder(Loc.c("car_propulsion"), propulsionList)
                .required(false)
                .active(getEditMode() ? getInsurance().getPropulsion() : Car.Propulsion.A)
                .build();
    }

    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = super.getNodes();

        tmp.add(licencePlate);
        tmp.add(registration);
        tmp.add(mileage);
        tmp.add(type);
        tmp.add(horsePower);
        tmp.add(propulsion);

        return tmp;
    }

    @Override
    public void callback()
    {
        if (getEditMode()) {
            Car i = getInsurance();
            i.setMileage(Integer.parseInt(mileage.getValue()));
            i.setHorsePower(Integer.parseInt(horsePower.getValue()));
            i.setLicencePlate(licencePlate.getValue());
            i.setPremium(Integer.parseInt(getPremium()));
            i.setAmount(Integer.parseInt(getAmount()));
            i.setStatus(getStatus());
        } else {
            setInsurance(new Car.Builder(getCustomer(), licencePlate.getValue())
                    .registration(registration.getData())
                    .horsePower(Integer.parseInt(horsePower.getValue()))
                    .mileage(Integer.parseInt(mileage.getValue()))
                    .amount(Integer.parseInt(getAmount()))
                    .premium(Integer.parseInt(getPremium()))
                    .status(getStatus())
                    .type((Car.Type) type.getData())
                    .propulsion((Car.Propulsion) propulsion.getData())
                    .build());
        }
        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Car> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
