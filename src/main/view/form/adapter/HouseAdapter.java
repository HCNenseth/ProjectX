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

/**
 * Created by HansPetter on 23.04.2015.
 */
public class HouseAdapter implements Formable {

    private FormValueNode street;
    private FormValueNode postalCode;
    private FormValueNode city;
    private FormValueNode built;
    private FormValueNode squareMeters;
    private FormValueNode amount;
    private FormValueNode premium;
    private FormChoiceNode type;
    private FormChoiceNode material;
    private FormChoiceNode status;

    private House house;
    private Person customer;

    public HouseAdapter(Person customer, House house)
    {
        this();
        this.customer = customer;
        this.house = house;
    }

    public HouseAdapter(Person customer)
    {
        this();
        this.customer = customer;
    }

    public HouseAdapter()
    {

        /**
         * FormValueNodes
         */

        // TODO add new regexes in StringMatcher.

        street = new FormValueNode.Builder(Loc.get("street"))
                .error(Loc.get("error_street"))
                .regex(StringMatcher.getStreetAddress())
                .required(true)
                .build();

        postalCode = new FormValueNode.Builder(Loc.get("postalCode"))
                .error(Loc.get("error_postal_code"))
                .regex(StringMatcher.getPostalCode())
                .required(true)
                .build();

        city = new FormValueNode.Builder(Loc.get("city"))
                .error(Loc.get("error_city"))
                .regex(StringMatcher.getBaseString())
                .build();

        built = new FormValueNode.Builder(Loc.get("house_built"))
                .error(Loc.get("error_house_built"))
                .regex(StringMatcher.getYear())
                .build();

        squareMeters = new FormValueNode.Builder(Loc.get("squaremeteres"))
                .error(Loc.get("error_squaremeters"))
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

        /**
         * FormChoiceNodes
         */

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


    @Override
    public List<FormNode> getNodes() {
        List<FormNode> tmp = new ArrayList();
        tmp.add(street);
        tmp.add(postalCode);
        tmp.add(city);
        tmp.add(type);
        tmp.add(material);
        tmp.add(squareMeters);
        tmp.add(built);
        tmp.add(premium);
        tmp.add(amount);
        tmp.add(status);
        return tmp;
    }

    @Override
    public void callback() {
        if(house == null)
        {
            house = new House.Builder(customer, street.getValue(), postalCode.getValue())
                    .city(city.getValue())
                    .type((House.Type)type.getData())
                    .material((House.Material)material.getData())
                    .squareMeter(Integer.parseInt(squareMeters.getValue()))
                    .year(Integer.parseInt(built.getValue()))
                    .premium(Integer.parseInt(premium.getValue()))
                    .amount(Integer.parseInt(amount.getValue()))
                    .status((Status)status.getData())
                    .build();

            System.out.println(house);
            return;
        }

        // TODO refactor house model

        /**house.setCity(city.getValue());
        house.setType((House.Type)type.getData());
        house.setMaterial((House.Material)material.getData());
        house.setSquareMeter(Integer.parseInt(squareMeters.getValue()));
        house.setYear(Integer.parseInt(built.getValue()));
        house.setPremium(Integer.parseInt(premium.getValue()));
        house.setAmount(Integer.parseInt(amount.getValue()));
        house.setStatus((Status)status.getData());

        System.out.println(house);
        return;
*/
    }
}
