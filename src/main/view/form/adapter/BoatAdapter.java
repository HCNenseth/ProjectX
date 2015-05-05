package main.view.form.adapter;

import main.config.Config;
import main.localization.Loc;
import main.model.Status;
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
import java.util.List;
import java.util.function.Consumer;

public class                                                                                                                                    BoatAdapter extends InsuranceAdapter<Boat> implements Formable<Boat>
{
    private FormValueNode licencePlate;
    private FormValueNode owner;
    private FormDateNode registration;
    private FormValueNode length;
    private FormValueNode horsePower;
    private FormChoiceNode propulsion;
    private FormChoiceNode type;

    private BoatAdapter() { super(null); return; }

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
        licencePlate = new FormValueNode.Builder(Loc.c("licence_plate"))
                .regex(StringMatcher.getRegnr())
                .value(getEditMode() ? getInsurance().getLicencePlate() : "")
                .error(Loc.c("licence_plate_error"))
                .build();

        owner = new FormValueNode.Builder(Loc.c("owner"))
                .value(getEditMode() ? getInsurance().getOwner().getFullName() : "")
                .regex(StringMatcher.getBaseString())
                .build();


        registration = new FormDateNode.Builder(Loc.c("vehicle_registration"),
                getEditMode() ? getInsurance().getRegistration() : LocalDate.of(Config.STANDARD_YEAR, Config.STANDARD_MONTH, Config.STANDARD_DAY))
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

        List<Enum> propulsionList = new ArrayList<>();
        for(Boat.Propulsion p : Boat.Propulsion.values())
        {
            propulsionList.add(p);
        }

        propulsion = new FormChoiceNode.Builder(Loc.c("boat_propulsion"), propulsionList )
                .required(false)
                .active(getEditMode() ? getInsurance().getPropulsion() : Boat.Propulsion.A)
                .build();

        List<Enum> typeList = new ArrayList<>();
        for(Boat.Type t : Boat.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder(Loc.c("boat_type"), typeList)
                .required(false)
                .active(getEditMode() ? getInsurance().getType() : Boat.Type.A)
                .build();

    }

    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = super.getNodes();
        tmp.add(licencePlate);
        tmp.add(owner);
        tmp.add(registration);
        tmp.add(length);
        tmp.add(horsePower);
        tmp.add(propulsion);
        tmp.add(type);
        tmp.add(getStatus());
        return tmp;
    }

    @Override
    public void callback() {

        if(super.getEditMode())
        {
            getInsurance().setHorsePower(Integer.parseInt(horsePower.getValue()));
            getInsurance().setPremium(Integer.parseInt(super.getPremium().getValue()));
            getInsurance().setAmount(Integer.parseInt(super.getAmount().getValue()));
            getInsurance().setStatus((Status) super.getStatus().getData());
            getInsurance().setLicencePlate(licencePlate.getValue());
            getInsurance().setPropulsion((Boat.Propulsion) propulsion.getData());
            System.out.println(getInsurance());
            return;
        }

        Boat boat = new Boat.Builder(super.getCustomer(), licencePlate.getValue())
                .horsePower(Integer.parseInt(horsePower.getValue()))
                .length(Integer.parseInt(length.getValue()))
                .registration(registration.getData())
                .premium(Integer.parseInt(super.getPremium().getValue()))
                .amount(Integer.parseInt(super.getAmount().getValue()))
                .propulsion((Boat.Propulsion) propulsion.getData())
                .status((Status) super.getStatus().getData())
                .type((Boat.Type) type.getData())
                .build();

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Boat> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
