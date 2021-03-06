package main.controller.adapter.insurance;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.property.House;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * HouseAdapter.java
 */
public class HouseAdapter extends InsuranceAdapter<House> implements Formable<House>
{

    private FormValueNode contents;
    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode squareMeters;
    private FormValueNode yearBuilt;
    private FormChoiceNode<House.Type> type;
    private FormChoiceNode<House.Material> material;
    private FormChoiceNode<House.Standard> standard;

    /**
     * Instantiates a new House adapter.
     *
     * @param customer the customer
     * @param house the house
     */
    public HouseAdapter(Person customer, House house)
    {
        super(customer, house);
        initNodes();
    }

    /**
     * Instantiates a new House adapter.
     *
     * @param customer the customer
     */
    public HouseAdapter(Person customer)
    {
        super(customer);
        initNodes();
    }

    private void initNodes()
    {
        contents = new FormValueNode.Builder(Loc.c("contents"))
                .error(Loc.c("error_house_contents"))
                .value(getEditMode() ? getInsurance().getContents() + "" : "")
                .regex(StringMatcher.getFloat())
                .required(false)
                .build();

        street = new FormValueNode.Builder(Loc.c("street_address"))
                .error(Loc.c("error_house_street"))
                .value(getEditMode() ? getInsurance().getStreetAddress() : "")
                .regex(StringMatcher.getLiberation())
                .required(true)
                .build();

        postalCode = new FormValueNode.Builder(Loc.c("postal_code"))
                .error(Loc.c("error_house_code"))
                .value(getEditMode() ? getInsurance().getPostalCode() : "")
                .regex(StringMatcher.getPostalCode())
                .required(true)
                .build();

        city = new FormValueNode.Builder(Loc.c("city"))
                .error(Loc.c("error_house_city"))
                .value(getEditMode() ? getInsurance().getCity() : "")
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

        List<House.Type> typeList = new ArrayList<>(Arrays.asList(House.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : House.Type.A)
                .build();

        List<House.Material> materialList = new ArrayList<>(
                Arrays.asList(House.Material.values()));
        material = new FormChoiceNode.Builder<>(Loc.c("material"), materialList)
                .active(getEditMode() ? getInsurance().getMaterial() : House.Material.A)
                .build();

        List<House.Standard> standardList = new ArrayList<>(Arrays.asList(House.Standard.values()));
        standard = new FormChoiceNode.Builder<>(Loc.c("standard"), standardList)
                .active(getEditMode() ? getInsurance().getStandard() : House.Standard.A)
                .build();
    }

    private void update()
    {
        House i = getInsurance();
        i.setCity(city.getValue());
        i.setType(type.getData());
        i.setStreetAddress(street.getValue());
        i.setPostalCode(postalCode.getValue());
        i.setMaterial(material.getData());
        i.setStandard(standard.getData());
        i.setDesc(getDescription());
        i.setDeductible(getDeductible());
        i.setSquareMeter(Integer.parseInt(squareMeters.getValue()));
        i.setYear(Integer.parseInt(yearBuilt.getValue()));
        i.setPremium(getPremium());
        i.setAmount(getAmount());
        i.setStatus(getStatus());
        i.setContents(Double.parseDouble(contents.getValue()));
    }

    private void create()
    {
        House insurance = new House.Builder(getCustomer(),
                street.getValue(), postalCode.getValue())
                .city(city.getValue())
                .type(type.getData())
                .material(material.getData())
                .standard(standard.getData())
                .desc(getDescription())
                .deductible(getDeductible())
                .squareMeter(Integer.parseInt(squareMeters.getValue()))
                .year(Integer.parseInt(yearBuilt.getValue()))
                .premium(getPremium())
                .amount(getAmount())
                .status(getStatus())
                .contents(Double.parseDouble(contents.getValue()))
                .build();
        setInsurance(insurance);
        Insurance.saveNew(insurance);
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getNodes();

        tmp.add(contents);
        tmp.add(street);
        tmp.add(postalCode);
        tmp.add(city);
        tmp.add(type);
        tmp.add(material);
        tmp.add(standard);
        tmp.add(squareMeters);
        tmp.add(yearBuilt);

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

    @Override
    public void setOnDoneAction(Consumer<House> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
