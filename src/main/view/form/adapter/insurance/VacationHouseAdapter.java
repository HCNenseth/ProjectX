package main.view.form.adapter.insurance;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.property.VacationHouse;
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
 * VacationHouseAdapter.java
 */
public class VacationHouseAdapter extends InsuranceAdapter<VacationHouse>
        implements Formable<VacationHouse>
{
    private FormValueNode contents;
    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode squareMeters;
    private FormValueNode yearBuilt;
    private FormChoiceNode<VacationHouse.Type> type;
    private FormChoiceNode<VacationHouse.Material> material;
    private FormChoiceNode<VacationHouse.Standard> standard;

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
        contents = new FormValueNode.Builder(Loc.c("contents"))
                .error(Loc.c("error_vacation_house_contents"))
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

        List<VacationHouse.Type> typeList = new ArrayList<>(
                Arrays.asList(VacationHouse.Type.values()));
        type = new FormChoiceNode.Builder<>(Loc.c("type"), typeList)
                .active(getEditMode() ? getInsurance().getType() : VacationHouse.Type.A)
                .build();

        List<VacationHouse.Material> materialList = new ArrayList<>(
                Arrays.asList(VacationHouse.Material.values()));
        material = new FormChoiceNode.Builder<>(Loc.c("material"), materialList)
                .active(getEditMode() ? getInsurance().getMaterial() : VacationHouse.Material.A)
                .build();

        List<VacationHouse.Standard> standardList = new ArrayList<>(
                Arrays.asList(VacationHouse.Standard.values()));
        standard = new FormChoiceNode.Builder(Loc.c("standard"), standardList)
                .active(getEditMode() ? getInsurance().getStandard() : VacationHouse.Standard.A)
                .build();
    }

    private void update()
    {
        VacationHouse i = getInsurance();
        i.setCity(city.getValue());
        i.setStreetAddress(street.getValue());
        i.setPostalCode(postalCode.getValue());
        i.setType(type.getData());
        i.setMaterial(material.getData());
        i.setDesc(getDescription());
        i.setDeductible(getDeductible());
        i.setStandard(standard.getData());
        i.setSquareMeter(Integer.parseInt(squareMeters.getValue()));
        i.setYear(Integer.parseInt(yearBuilt.getValue()));
        i.setPremium(getPremium());
        i.setAmount(getAmount());
        i.setStatus(getStatus());
        i.setContents(Double.parseDouble(contents.getValue()));
    }

    private void create()
    {
        VacationHouse insurance = new VacationHouse.Builder(getCustomer(),
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
    public void setOnDoneAction(Consumer<VacationHouse> c)
    {
        callBackEvent.setOnAction(e -> c.accept(getInsurance()));
    }
}
