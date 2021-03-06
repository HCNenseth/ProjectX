package main.controller.adapter.insurance;

import main.config.Config;
import main.localization.Loc;
import main.model.insurance.Insurance;
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
import java.util.Arrays;
import java.util.List;

/**
 * CarAdapter.java
 */
public class CarAdapter extends InsuranceAdapter<Car> implements Formable<Car>
{

    private FormValueNode licensePlate;
    private FormValueNode owner;
    private FormDateNode registration;
    private FormValueNode mileage;
    private FormValueNode horsePower;
    private FormChoiceNode<Car.Type> type;
    private FormChoiceNode<Car.Propulsion> propulsion;

    /**
     * Instantiates a new Car adapter.
     *
     * @param customer the customer
     * @param car the car
     */
    public CarAdapter(Person customer, Car car)
    {
        super(customer, car);
        initNodes();
    }

    /**
     * Instantiates a new Car adapter.
     *
     * @param customer the customer
     */
    public CarAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private void initNodes()
    {
        licensePlate = new FormValueNode.Builder(Loc.c("license_plate"))
                .error(Loc.c("license_plate_error"))
                .value(getEditMode() ? getInsurance().getLicensePlate() : "")
                .regex(StringMatcher.getRegnr())
                .build();

        owner = new FormValueNode.Builder(Loc.c("owner"))
                .value(getEditMode() ? getInsurance().getOwner() : "")
                .regex(StringMatcher.getBaseString())
                .error(Loc.c("owner_error"))
                .required(false)
                .build();

        registration = new FormDateNode.Builder(Loc.c("vehicle_registration"),
                getEditMode() ? getInsurance().getRegistration() : LocalDate.of(Config.STANDARD_YEAR,
                        Config.STANDARD_MONTH,
                        Config.STANDARD_DAY))
                .build();

        mileage = new FormValueNode.Builder(Loc.c("mileage"))
                .error(Loc.c("mileage_error"))
                .value(getEditMode() ? Integer.toString(getInsurance().getMileage()) : "")
                .regex(StringMatcher.getFloat())
                .build();

        horsePower = new FormValueNode.Builder(Loc.c("vehicle_horse_power"))
                .regex(StringMatcher.getDigit())
                .value(getEditMode() ? Integer.toString(getInsurance().getHorsePower()) : "")
                .error(Loc.c("vehicle_horse_power_error"))
                .build();

        List<Car.Type> typeList = new ArrayList<>(Arrays.asList(Car.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("car_type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : Car.Type.A)
                .required(false)
                .build();

        List<Car.Propulsion> propulsionList = new ArrayList<>(
                Arrays.asList(Car.Propulsion.values()));
        propulsion = new FormChoiceNode.Builder<>(Loc.c("car_propulsion"), propulsionList)
                .active(getEditMode() ? getInsurance().getPropulsion() : Car.Propulsion.A)
                .required(false)
                .build();
    }

    private void update()
    {
        Car i = getInsurance();
        i.setPremium(getPremium());
        i.setAmount(getAmount());
        i.setDeductible(getDeductible());
        i.setDesc(getDescription());
        i.setStatus(getStatus());

        i.setType(type.getData());
        i.setPropulsion(propulsion.getData());

        i.setOwner(owner.getValue());
        i.setMileage(Integer.parseInt(mileage.getValue()));
        i.setHorsePower(Integer.parseInt(horsePower.getValue()));
        i.setLicensePlate(licensePlate.getValue());
        i.setRegistration(registration.getData());
    }

    private void create()
    {
        Car insurance = new Car.Builder(getCustomer(), licensePlate.getValue())
                // shared values for all insurances
                .premium(getPremium())
                .amount(getAmount())
                .deductible(getDeductible())
                .desc(getDescription())
                .status(getStatus())

                .type(type.getData())
                .propulsion(propulsion.getData())

                .owner(owner.getValue())
                .mileage(Integer.parseInt(mileage.getValue()))
                .horsePower(Integer.parseInt(horsePower.getValue()))
                .registration(registration.getData())
                .build();
        setInsurance(insurance);
        Insurance.saveNew(insurance);
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getNodes();

        tmp.add(owner);
        tmp.add(licensePlate);
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
            update();
        } else {
            create();
        }

        callBackEvent.fire();
    }
}
