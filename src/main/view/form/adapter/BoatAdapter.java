package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.insurance.vehicle.Boat;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HansPetter on 23.04.2015.
 */
public class BoatAdapter implements Formable {

    private FormValueNode regNr;
    private FormValueNode registrationYear;
    private FormValueNode length;
    private FormValueNode horsePower;
    private FormValueNode premium;
    private FormValueNode amount;
    private FormValueNode desc;
    private FormChoiceNode propulsion;
    private FormChoiceNode type;
    private FormChoiceNode status;

    private Person customer;
    private Boat boat;

    public BoatAdapter(Person customer, Boat boat)
    {
        this();
        this.customer = customer;
        this.boat = boat;
    }

    public BoatAdapter(Person customer)
    {
        this();
        this.customer = customer;
    }

    public BoatAdapter()
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

        regNr = new FormValueNode.Builder(Loc.get("reg_number"))
                .regex(StringMatcher.getRegnr())
                .error(Loc.get("error_reg_number"))
                .build();

        registrationYear = new FormValueNode.Builder(Loc.get("reg_year"))
                .regex(StringMatcher.getYear())
                .error(Loc.get("error_reg_year"))
                .build();

        length = new FormValueNode.Builder(Loc.get("boat_length"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_boat_length"))
                .build();

        horsePower = new FormValueNode.Builder(Loc.get("boat_horse_power"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_boat_horse_power"))
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
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(registrationYear);
        tmp.add(length);
        tmp.add(horsePower);
        tmp.add(premium);
        tmp.add(amount);
        tmp.add(propulsion);
        tmp.add(status);
        tmp.add(type);
        return tmp;
    }

    @Override
    public void callback() {
        if(boat == null)
        {
            boat = new Boat.Builder(customer, regNr.getValue())
                    .horsePower(Integer.parseInt(horsePower.getValue()))
                    .length(Integer.parseInt(length.getValue()))
                    .registrationYear(Integer.parseInt(registrationYear.getValue()))
                    .premium(Integer.parseInt(premium.getValue()))
                    .amount(Integer.parseInt(amount.getValue()))
                    .propulsion((Boat.Propulsion) propulsion.getData())
                    .status((Status)status.getData())
                    .type((Boat.Type) type.getData())
                    .build();
            System.out.println(boat);
            return;
        }

        boat.setHorsePower(Integer.parseInt(horsePower.getValue()));
        boat.setPremium(Integer.parseInt(premium.getValue()));
        boat.setAmount(Integer.parseInt(amount.getValue()));
        boat.setStatus((Status)status.getData());
        boat.setRegNr(regNr.getValue());
        boat.setPropulsion((Boat.Propulsion) propulsion.getData());
        System.out.println(boat);
        return;
    }
}