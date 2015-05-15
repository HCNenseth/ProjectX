package main.view.form.adapter.insurance;

import main.config.Config;
import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.vehicle.Boat;
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
 * BoatAdapter.java
 */
public class BoatAdapter extends InsuranceAdapter<Boat> implements Formable<Boat>
{
    private FormValueNode licensePlate;
    private FormValueNode owner;
    private FormDateNode registration;
    private FormValueNode length;
    private FormValueNode horsePower;
    private FormChoiceNode<Boat.Propulsion> propulsion;
    private FormChoiceNode<Boat.Type> type;

    public BoatAdapter(Person customer, Boat boat)
    {
        super(customer, boat);
        initNodes();
    }

    public BoatAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private void initNodes()
    {
        licensePlate = new FormValueNode.Builder(Loc.c("license_plate"))
                .regex(StringMatcher.getRegnr())
                .value(getEditMode() ? getInsurance().getLicensePlate() : "")
                .error(Loc.c("license_plate_error"))
                .build();

        owner = new FormValueNode.Builder(Loc.c("owner"))
                .value(getEditMode() ? getInsurance().getOwner() : "")
                .regex(StringMatcher.getBaseString())
                .build();

        registration = new FormDateNode.Builder(Loc.c("vehicle_registration"),
                getEditMode() ? getInsurance().getRegistration() : LocalDate.of(Config.STANDARD_YEAR,
                                                                                Config.STANDARD_MONTH,
                                                                                Config.STANDARD_DAY))
                .required(false)
                .build();

        length = new FormValueNode.Builder(Loc.c("length"))
                .regex(StringMatcher.getDigit())
                .value(getEditMode() ? Integer.toString(getInsurance().getLength()) : "")
                .error(Loc.c("length_error"))
                .build();

        horsePower = new FormValueNode.Builder(Loc.c("vehicle_horse_power"))
                .regex(StringMatcher.getDigit())
                .value(getEditMode() ? Integer.toString(getInsurance().getHorsePower()) : "")
                .error(Loc.c("vehicle_horse_power_error"))
                .build();

        List<Boat.Propulsion> propulsionList = new ArrayList<>(
                Arrays.asList(Boat.Propulsion.values()));
        propulsion = new FormChoiceNode.Builder<>(Loc.c("boat_propulsion"), propulsionList )
                .active(getEditMode() ? getInsurance().getPropulsion() : Boat.Propulsion.A)
                .required(false)
                .build();

        List<Boat.Type> typeList = new ArrayList<>(Arrays.asList(Boat.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("boat_type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : Boat.Type.A)
                .required(false)
                .build();

    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getNodes();

        tmp.add(owner);
        tmp.add(licensePlate);
        tmp.add(registration);
        tmp.add(length);
        tmp.add(horsePower);
        tmp.add(propulsion);
        tmp.add(type);

        return tmp;
    }

    @Override
    public void callback()
    {
        if (getEditMode()) {
            Boat i = getInsurance();
            // shared values for all insurances
            i.setPremium(getPremium());
            i.setAmount(getAmount());
            i.setDeductible(getDeductible());
            i.setDesc(getDescription());
            i.setStatus(getStatus());

            i.setType(type.getData());
            i.setPropulsion(propulsion.getData());

            i.setOwner(owner.getValue());
            i.setHorsePower(Integer.parseInt(horsePower.getValue()));
            i.setLength(Integer.parseInt(length.getData()));
            i.setLicensePlate(licensePlate.getValue());
            i.setRegistration(registration.getData());
        } else {
            Boat insurance = new Boat.Builder(getCustomer(), licensePlate.getValue())
                    // shared values for all insurances
                    .premium(getPremium())
                    .amount(getAmount())
                    .deductible(getDeductible())
                    .desc(getDescription())
                    .status(getStatus())

                    .type(type.getData())
                    .propulsion(propulsion.getData())

                    .owner(owner.getValue())
                    .horsePower(Integer.parseInt(horsePower.getValue()))
                    .length(Integer.parseInt(length.getValue()))
                    .registration(registration.getData())
                    .premium(getPremium())
                    .amount(getAmount())
                    .status(getStatus())
                    .build();
            setInsurance(insurance);
            Insurance.saveNew(insurance);
        }
        callBackEvent.fire();
    }
}
