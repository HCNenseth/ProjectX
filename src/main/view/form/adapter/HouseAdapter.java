package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.insurance.property.House;
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
public class HouseAdapter extends InsuranceAdapter<House> implements Formable<House>
{

    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode squareMeters;
    private FormDateNode yearBuilt;
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

    private void initNodes()
    {

        street = new FormValueNode.Builder(Loc.get("street_address"))
                .error(Loc.get("error_house_street"))
                .regex(StringMatcher.getStreetAddress())
                .required(true)
                .build();

        postalCode = new FormValueNode.Builder(Loc.get("postal_code"))
                .error(Loc.get("error_house_code"))
                .regex(StringMatcher.getPostalCode())
                .required(true)
                .build();

        city = new FormValueNode.Builder(Loc.get("city"))
                .error(Loc.get("error_house_city"))
                .regex(StringMatcher.getBaseString())
                .build();

        yearBuilt = new FormDateNode.Builder(Loc.get("year_built"),
                super.getEditMode() ? super.getCustomer().getDateOfBirth() : LocalDate.of(standardYear, standardMonth, standardDay))
                .build();

        squareMeters = new FormValueNode.Builder(Loc.get("square_meters"))
                .error(Loc.get("error_house_squaremeters"))
                .regex(StringMatcher.getDigit())
                .build();

        List<Enum> typeList = new ArrayList();
        for(House.Type t : House.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder<>(Loc.get("type"), typeList)
                .build();

        List<Enum> materialList = new ArrayList();
        for(House.Material m : House.Material.values())
        {
            materialList.add(m);
        }

        material = new FormChoiceNode.Builder<>(Loc.get("material"), materialList)
                .build();

    }

    private HouseAdapter() { super(null); return; }


    @Override
    public List<FormNode> getNodes() {
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
    public void callback() {

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
