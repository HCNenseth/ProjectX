package main.controller;

import main.App;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.insurance.property.House;
import main.model.insurance.travel.Travel;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.SearchResult;
import main.view.form.Form;
import main.view.form.adapter.*;
import main.view.table.ClaimTable;
import main.view.table.InsuranceTable;
import main.view.table.PersonTable;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by alex on 4/23/15.
 */
class Search
{
    private String keyword;

    public Search(Payload p)
    {
        this.keyword = p.getString();
        generateData();
    }

    private void generateData()
    {
        List<Person> persons = (List<Person>)Storage.getInstance().get(App.PERSONS);
        List<Insurance> insurances = (List<Insurance>)Storage.getInstance().get(App.INSURANCES);
        List<Claim> claims = (List<Claim>)Storage.getInstance().get(App.CLAIMS);

        /* person table and actions */
        PersonTable personTable = new PersonTable();
        personTable.setOnEditAction(this::editPerson);
        personTable.setOnViewAction(this::viewPerson);
        personTable.setOnDoubleClickAction(this::viewPerson);

        /* insurance table and actions */
        InsuranceTable insuranceTable = new InsuranceTable();
        insuranceTable.setOnEditAction(this::editInsurance);
        insuranceTable.setOnViewAction(this::viewInsurance);
        insuranceTable.setOnDoubleClickAction(this::viewInsurance);

        /* claim table and actions */
        ClaimTable claimTable = new ClaimTable();
        claimTable.setOnEditAction(this::editClaim);
        claimTable.setOnViewAction(this::viewClaim);
        claimTable.setOnDoubleClickAction(this::viewClaim);

        /* generate data for the tables */
        persons.stream().filter(i -> i.query(keyword)).forEach(personTable::insertData);
        insurances.stream().filter(i -> i.query(keyword)).forEach(insuranceTable::insertData);
        claims.stream().filter(i -> i.query(keyword)).forEach(claimTable::insertData);

        /* put the tables into a search result view */
        SearchResult searchResult = new SearchResult();
        searchResult.addTable(personTable.getTable(), Loc.get("persons"));
        searchResult.addTable(insuranceTable.getTable(), Loc.get("insurances"));
        searchResult.addTable(claimTable.getTable(), Loc.get("insurances"));

        /* insert search result view into tab */
        Resources.inst.getOtp().injectObservableTab(Loc.get("search_results"),
                searchResult.getNode(), true);
    }

    private void viewPerson(Person p)
    {
        System.out.println(p.toString());
    }

    private void editPerson(Person p)
    {
        Form f = new Form();
        f.injectAdapter(new PersonAdapter(p));
        Resources.inst.getOtp().injectObservableTab(p.getName(),
                f.getForm(), true);
    }

    private void viewInsurance(Insurance i)
    {
        System.out.println(i.toString());
    }

    private void editInsurance(Insurance i)
    {
        Form f = new Form();
        switch (i.identify()) {
            case CAR:
                f.injectAdapter(new CarAdapter(i.getCustomer(), (Car)i));
                break;
            case BOAT:
                f.injectAdapter(new BoatAdapter(i.getCustomer(), (Boat)i));
                break;
            case HOUSE:
                f.injectAdapter(new HouseAdapter(i.getCustomer(), (House)i));
                break;
            case TRAVEL:
                f.injectAdapter(new TravelAdapter(i.getCustomer(), (Travel)i));
                break;
            default: return;
        }

        Resources.inst.getOtp().injectObservableTab(i.getCustomer().getName(),
                f.getForm(), true);
    }

    private void viewClaim(Claim c)
    {
        System.out.println(c.toString());
    }

    private void editClaim(Claim c)
    {
        // TODO ClaimAdapter not implemented yet.

        /*
        Form f = new Form();
        f.injectAdapter(new ClaimAdapter(c.getCustomer(), c.getInsurance(), c));
        Resources.inst.getOtp().injectObservableTab(p.getName(),
                f.getForm(), true);
        */
    }
}
