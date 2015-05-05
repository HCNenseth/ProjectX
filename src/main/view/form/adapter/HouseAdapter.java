package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.insurance.property.House;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HouseAdapter extends InsuranceAdapter<House> implements Formable<House>
{

    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode squareMeters;
    private FormValueNode yearBuilt;
    private FormChoiceNode type;
    private FormChoiceNode material;

    public HouseAdapter(Person customer, House house)
    {
        super(customer, house);
        initNodes();
    }

    public HouseAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private HouseAdapter() { super(null); return; }

    private void initNodes()
    {

        street = new FormValueNode.Builder(Loc.c("street_address"))
                .error(Loc.c("error_house_street"))
                .value(getEditMode() ? getCustomer().getStreetAddress() : "")
                .regex(StringMatcher.getStreetAddress())
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
        for(House.Type t : House.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder<>(Loc.c("type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : House.Type.A)
                .build();

        List<Enum> materialList = new ArrayList();
        for(House.Material m : House.Material.values())
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
        tmp.add(getStatus());
        return tmp;
    }

    @Override
    public void callback()
    {
        if(super.getEditMode())
        {
            super.getInsurance().setCity(city.getValue());
            super.getInsurance().setType((House.Type) type.getData());
            super.getInsurance().setMaterial((House.Material) material.getData());
            super.getInsurance().setSquareMeter(Integer.parseInt(squareMeters.getValue()));
            super.getInsurance().setYear(Integer.parseInt(yearBuilt.getValue()));
            super.getInsurance().setPremium(Integer.parseInt(super.getPremium().getValue()));
            super.getInsurance().setAmount(Integer.parseInt(super.getAmount().getValue()));
            super.getInsurance().setStatus((Status) super.getStatus().getData());

            System.out.println(super.getInsurance());
            return;
        }

        House house = new House.Builder(super.getCustomer(), street.getValue(), postalCode.getValue())
                .city(city.getValue())
                .type((House.Type) type.getData())
                .material((House.Material) material.getData())
                .squareMeter(Integer.parseInt(squareMeters.getValue()))
                .year(Integer.parseInt(yearBuilt.getValue()))
                .premium(Integer.parseInt(super.getPremium().getValue()))
                .amount(Integer.parseInt(super.getAmount().getValue()))
                .status((Status)super.getStatus().getData())
                .build();

        super.setInsurance(house);

        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<House> c)
    {
        callBackEvent.setOnAction(e -> c.accept(super.getInsurance()));
    }
}
