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
public class BoatAdapter extends InsuranceAdapter implements Formable<Boat>
{

    private FormValueNode regNr;
    private FormDateNode registrationYear;
    private FormValueNode length;
    private FormValueNode horsePower;
    private FormChoiceNode propulsion;
    private FormChoiceNode type;
    private Boat boat;

    private BoatAdapter() { super(null); return; }

    public BoatAdapter(Person customer, Boat boat)
    {
        super(customer, true);
        this.boat = boat;
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
                .error(Loc.get("error_reg_number"))
                .build();

        registrationYear = new FormDateNode.Builder(Loc.get("car_reg_year"),
                super.getEditMode() ? boat.getRegistrationYear() : LocalDate.of(super.standardYear, super.standardMonth, super.standardDay))
                .build();

        length = new FormValueNode.Builder(Loc.get("boat_length"))
                .regex(StringMatcher.getDigit())
                .error(Loc.get("error_boat_length"))
                .build();

        horsePower = new FormValueNode.Builder(Loc.get("boat_horse_power"))
                .regex(StringMatcher.getDigit())
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
            boat.setHorsePower(Integer.parseInt(horsePower.getValue()));
            boat.setPremium(Integer.parseInt(super.getPremium().getValue()));
            boat.setAmount(Integer.parseInt(super.getAmount().getValue()));
            boat.setStatus((Status)super.getStatus().getData());
            boat.setRegNr(regNr.getValue());
            boat.setPropulsion((Boat.Propulsion) propulsion.getData());
            System.out.println(boat);
            return;
        }

        boat = new Boat.Builder(super.getCustomer(), regNr.getValue())
                .horsePower(Integer.parseInt(horsePower.getValue()))
                .length(Integer.parseInt(length.getValue()))
                .registrationYear(registrationYear.getData())
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
        callBackEvent.setOnAction(e -> c.accept(boat));
    }
}
