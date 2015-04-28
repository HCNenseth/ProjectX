package main.view.form.adapter;

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

public class BoatAdapter extends InsuranceAdapter<Boat> implements Formable<Boat>
{

    private FormValueNode regNr;
    private FormValueNode registrationYear;
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
        regNr = new FormValueNode.Builder(Loc.get("reg_number"))
                .regex(StringMatcher.getRegnr())
                .value(getEditMode() ? getInsurance().getLicencePlate() : "")
                .error(Loc.get("error_reg_number"))
                .build();

        registrationYear = new FormValueNode.Builder(Loc.get("car_reg_year"))
                .regex(StringMatcher.getYear())
                .value(getEditMode() ? Integer.toString(getInsurance().getRegistrationYear()) : "")
                .error(Loc.get("error_reg_year"))
                .build();

        length = new FormValueNode.Builder(Loc.get("boat_length"))
                .regex(StringMatcher.getDigit())
                .value(getEditMode() ? Integer.toString(getInsurance().getLength()) : "")
                .error(Loc.get("error_boat_length"))
                .build();

        horsePower = new FormValueNode.Builder(Loc.get("boat_horse_power"))
                .regex(StringMatcher.getDigit())
                .value(getEditMode() ? Integer.toString(getInsurance().getHorsePower()) : "")
                .error(Loc.get("error_boat_horse_power"))
                .build();

        List<Enum> propulsionList = new ArrayList<>();
        for(Boat.Propulsion p : Boat.Propulsion.values())
        {
            propulsionList.add(p);
        }

        propulsion = new FormChoiceNode.Builder(Loc.get("boat_propulsion"), propulsionList )
                .required(false)
                .active(Boat.Propulsion.A)
                .build();

        List<Enum> typeList = new ArrayList<>();
        for(Boat.Type t : Boat.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder(Loc.get("boat_type"), typeList)
                .required(false)
                .active(Boat.Type.A)
                .build();

    }

    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = super.getNodes();
        tmp.add(registrationYear);
        tmp.add(length);
        tmp.add(horsePower);
        tmp.add(propulsion);
        tmp.add(type);
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
            getInsurance().setLicencePlate(regNr.getValue());
            getInsurance().setPropulsion((Boat.Propulsion) propulsion.getData());
            System.out.println(getInsurance());
            return;
        }

        Boat boat = new Boat.Builder(super.getCustomer(), regNr.getValue())
                .horsePower(Integer.parseInt(horsePower.getValue()))
                .length(Integer.parseInt(length.getValue()))
                .registrationYear(Integer.parseInt(registrationYear.getValue()))
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
