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

/**
 * Created by HansPetter on 23.04.2015.
 */
public class HouseAdapter implements Formable {

    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode squareMeters;
    private FormDateNode yearBuilt;
    private FormValueNode amount;
    private FormValueNode premium;
    private FormChoiceNode type;
    private FormChoiceNode material;
    private FormChoiceNode status;

    private House house;
    private Person customer;

    private boolean editMode = false;

    private int standardYear = 1970;
    private int standardMonth = 1;
    private int standardDay = 1;

    public HouseAdapter(Person customer, House house)
    {
        this.customer = customer;
        this.house = house;
        editMode = true;
        initNodes();
    }

    public HouseAdapter(Person customer)
    {
        this.customer = customer;
        initNodes();
    }

    private void initNodes()
    {

        street = new FormValueNode.Builder(Loc.get("house_street"))
                .error(Loc.get("error_house_street"))
                .regex(StringMatcher.getStreetAddress())
                .required(true)
                .build();

        postalCode = new FormValueNode.Builder(Loc.get("house_postalCode"))
                .error(Loc.get("error_house_code"))
                .regex(StringMatcher.getPostalCode())
                .required(true)
                .build();

        city = new FormValueNode.Builder(Loc.get("house_city"))
                .error(Loc.get("error_house_city"))
                .regex(StringMatcher.getBaseString())
                .build();

        yearBuilt = new FormDateNode.Builder(Loc.get("house_year_built"),
                editMode ? customer.getDateOfBirth() : LocalDate.of(standardYear, standardMonth, standardDay))
                .build();

        squareMeters = new FormValueNode.Builder(Loc.get("house_squaremeteres"))
                .error(Loc.get("error_house_squaremeters"))
                .regex(StringMatcher.getDigit())
                .build();

        amount = new FormValueNode.Builder(Loc.get("amount"))
                .error(Loc.get("error_amount"))
                .regex(StringMatcher.getDigit())
                .build();

        premium = new FormValueNode.Builder(Loc.get("premium"))
                .error(Loc.get("error_premium"))
                .regex(StringMatcher.getDigit())
                .build();

        List<Enum> typeList = new ArrayList();
        for(House.Type t : House.Type.values())
        {
            typeList.add(t);
        }

        type = new FormChoiceNode.Builder(Loc.get("type"), typeList)
                .build();

        List<Enum> statusList = new ArrayList();
        for(Status s : Status.values())
        {
            statusList.add(s);
        }

        status = new FormChoiceNode.Builder(Loc.get("status"), statusList)
                .build();

        List<Enum> materialList = new ArrayList();
        for(House.Material m : House.Material.values())
        {
            materialList.add(m);
        }

        material = new FormChoiceNode.Builder(Loc.get("material"), materialList)
                .build();

    }

    private HouseAdapter() { return; }


    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = new ArrayList();
        tmp.add(street);
        tmp.add(postalCode);
        tmp.add(city);
        tmp.add(type);
        tmp.add(material);
        tmp.add(squareMeters);
        tmp.add(yearBuilt);
        tmp.add(premium);
        tmp.add(amount);
        tmp.add(status);
        return tmp;
    }

    @Override
    public void callback() {

        if(editMode)
        {
            house.setCity(city.getValue());
            house.setType((House.Type) type.getData());
            house.setMaterial((House.Material) material.getData());
            house.setSquareMeter(Integer.parseInt(squareMeters.getValue()));
            house.setYear(Integer.parseInt(yearBuilt.getValue()));
            house.setPremium(Integer.parseInt(premium.getValue()));
            house.setAmount(Integer.parseInt(amount.getValue()));
            house.setStatus((Status) status.getData());

            System.out.println(house);
            return;
        }

        house = new House.Builder(customer, street.getValue(), postalCode.getValue())
                .city(city.getValue())
                .type((House.Type) type.getData())
                .material((House.Material) material.getData())
                .squareMeter(Integer.parseInt(squareMeters.getValue()))
                .year(Integer.parseInt(yearBuilt.getValue()))
                .premium(Integer.parseInt(premium.getValue()))
                .amount(Integer.parseInt(amount.getValue()))
                .status((Status) status.getData())
                .build();

        System.out.println(house);
        return;
    }
}
