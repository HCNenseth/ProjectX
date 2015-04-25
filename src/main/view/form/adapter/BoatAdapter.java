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

/**
 * Created by HansPetter on 23.04.2015.
 */
public class BoatAdapter implements Formable<Boat>
{

    private FormValueNode regNr;
    private FormDateNode registrationYear;
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


    /**
     * For use in the FormDateNode
     */
    private int standardYear = 1970;
    private int standardMonth = 1;
    private int standardDay = 1;

    private boolean editMode = false;

    private BoatAdapter() { return; }

    public BoatAdapter(Person customer, Boat boat)
    {
        this.customer = customer;
        this.boat = boat;
        editMode = true;
        initNodes();
    }

    public BoatAdapter(Person customer)
    {
        this.customer = customer;
        initNodes();
    }

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

        regNr = new FormValueNode.Builder(Loc.get("reg_number"))
                .regex(StringMatcher.getRegnr())
                .error(Loc.get("error_reg_number"))
                .build();

        registrationYear = new FormDateNode.Builder(Loc.get("car_reg_year"),
                editMode ? boat.getRegistrationYear() : LocalDate.of(standardYear, standardMonth, standardDay))
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

        if(editMode)
        {
            boat.setHorsePower(Integer.parseInt(horsePower.getValue()));
            boat.setPremium(Integer.parseInt(premium.getValue()));
            boat.setAmount(Integer.parseInt(amount.getValue()));
            boat.setStatus((Status)status.getData());
            boat.setRegNr(regNr.getValue());
            boat.setPropulsion((Boat.Propulsion) propulsion.getData());
            System.out.println(boat);
            return;
        }

        boat = new Boat.Builder(customer, regNr.getValue())
                .horsePower(Integer.parseInt(horsePower.getValue()))
                .length(Integer.parseInt(length.getValue()))
                .registrationYear(registrationYear.getData())
                .premium(Integer.parseInt(premium.getValue()))
                .amount(Integer.parseInt(amount.getValue()))
                .propulsion((Boat.Propulsion) propulsion.getData())
                .status((Status) status.getData())
                .type((Boat.Type) type.getData())
                .build();

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Boat> c)
    {
        callBackEvent.setOnAction(e -> c.accept(boat));
    }
}
