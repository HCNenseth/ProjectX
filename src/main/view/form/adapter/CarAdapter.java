package main.view.form.adapter;

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
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by HansPetter on 22.04.2015.
 */
public class CarAdapter extends InsuranceAdapter<Car> implements Formable<Car>
{

    private FormValueNode licensePlate;
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
        licensePlate = new FormValueNode.Builder(Loc.c("license_plate"))
                .error(Loc.c("license_plate_error"))
                .value(getEditMode() ? getInsurance().getLicensePlate() : "")
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

        List<Enum> typeList = new ArrayList<>();
        for (Car.Type t : Car.Type.values()) { typeList.add(t); }

        type = new FormChoiceNode.Builder<>(Loc.c("car_type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : Car.Type.A)
                .required(false)
                .build();

        List<Enum> propulsionList = new ArrayList<>();
        for(Car.Propulsion p : Car.Propulsion.values()) { propulsionList.add(p); }

        propulsion = new FormChoiceNode.Builder<>(Loc.c("car_propulsion"), propulsionList)
                .active(getEditMode() ? getInsurance().getPropulsion() : Car.Propulsion.A)
                .required(false)
                .build();
    }

    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = super.getNodes();

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
            Car i = getInsurance();
            // shared values for all insurances
            i.setPremium(getPremium());
            i.setAmount(getAmount());
            i.setDeductible(getDeductible());
            i.setDesc(getDescription());
            i.setStatus(getStatus());

            i.setType((Car.Type) type.getData());
            i.setPropulsion((Car.Propulsion) propulsion.getData());

            i.setMileage(Integer.parseInt(mileage.getValue()));
            i.setHorsePower(Integer.parseInt(horsePower.getValue()));
            i.setLicensePlate(licensePlate.getValue());
            i.setRegistration(registration.getData());
        } else {
            Car insurance = new Car.Builder(getCustomer(), licensePlate.getValue())
                    // shared values for all insurances
                    .premium(getPremium())
                    .amount(getAmount())
                    .deductible(getDeductible())
                    .desc(getDescription())
                    .status(getStatus())

                    .type((Car.Type) type.getData())
                    .propulsion((Car.Propulsion) propulsion.getData())

                    .mileage(Integer.parseInt(mileage.getValue()))
                    .horsePower(Integer.parseInt(horsePower.getValue()))
                    .registration(registration.getData())
                    .build();
            setInsurance(insurance);
            Insurance.saveNew(insurance);
        }
        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Car> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
