package main.view.form.adapter;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import main.localization.Loc;
import main.model.Status;
import main.model.insurance.Insurance;
import main.model.insurance.property.House;
import main.model.insurance.property.VacationHouse;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by HansChristian on 30.04.2015.
 */
public class VacationHouseAdapter extends InsuranceAdapter<VacationHouse> implements Formable<VacationHouse>
{

    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode squareMeters;
    private FormValueNode yearBuilt;
    private FormChoiceNode type;
    private FormChoiceNode material;

    public VacationHouseAdapter(Person customer, VacationHouse vacationHouse)
    {
        super(customer, vacationHouse);
        initNodes();
    }

    public VacationHouseAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private void initNodes()
    {
        street = new FormValueNode.Builder(Loc.c("street_address"))
                .error(Loc.c("error_house_street"))
                .value(getEditMode() ? getCustomer().getStreetAddress() : "")
                .regex(StringMatcher.getLiberation())
                .required(true)
                .build();

        postalCode = new FormValueNode.Builder(Loc.c("postal_code"))
                .error(Loc.c("error_house_code"))
                .value(getEditMode() ? getCustomer().getPostalCode() : "")
                .regex(StringMatcher.getPostalCode())
                .required(true)
                .build();

        city = new FormValueNode.Builder(Loc.c("city"))
                .error(Loc.c("error_house_city"))
                .value(getEditMode() ? getCustomer().getCity() : "")
                .regex(StringMatcher.getBaseString())
                .build();

        yearBuilt = new FormValueNode.Builder(Loc.c("year_built"))
                .error(Loc.c("error_house_year"))
                .value(getEditMode() ? Integer.toString(getInsurance().getYear()) : "")
                .regex(StringMatcher.getYear())
                .build();

        squareMeters = new FormValueNode.Builder(Loc.c("square_meters"))
                .error(Loc.c("error_house_squaremeters"))
                .value(getEditMode() ? Integer.toString(getInsurance().getSquareMeter()) : "")
                .regex(StringMatcher.getDigit())
                .build();

        List<Enum> typeList = new ArrayList();
        for(VacationHouse.Type t : VacationHouse.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder<>(Loc.c("type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : VacationHouse.Type.A)
                .build();

        List<Enum> materialList = new ArrayList();
        for(VacationHouse.Material m : VacationHouse.Material.values())
        {
            materialList.add(m);
        }

        material = new FormChoiceNode.Builder<>(Loc.c("material"), materialList)
                .active(getEditMode() ? getInsurance().getMaterial() : House.Material.A)
                .build();
    }

    @Override
    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = super.getNodes();
        tmp.add(street);
        tmp.add(postalCode);
        tmp.add(city);
        tmp.add(type);
        tmp.add(material);
        tmp.add(squareMeters);
        tmp.add(yearBuilt);

        return tmp;
    }

    @Override
    public void callback()
    {
        if (getEditMode()) {
            VacationHouse i = getInsurance();
            i.setCity(city.getValue());
            i.setType((VacationHouse.Type) type.getData());
            i.setMaterial((VacationHouse.Material) material.getData());
            i.setSquareMeter(Integer.parseInt(squareMeters.getValue()));
            i.setYear(Integer.parseInt(yearBuilt.getValue()));
            i.setPremium(getPremium());
            i.setAmount(getAmount());
            i.setStatus(getStatus());
        } else {
            VacationHouse insurance = new VacationHouse.Builder(getCustomer(),
                    street.getValue(), postalCode.getValue())
                    .city(city.getValue())
                    .type((VacationHouse.Type) type.getData())
                    .material((VacationHouse.Material) material.getData())
                    .squareMeter(Integer.parseInt(squareMeters.getValue()))
                    .year(Integer.parseInt(yearBuilt.getValue()))
                    .premium(getPremium())
                    .amount(getAmount())
                    .status(getStatus())
                    .build();
            setInsurance(insurance);
            Insurance.saveNew(insurance);
        }
        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<VacationHouse> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
