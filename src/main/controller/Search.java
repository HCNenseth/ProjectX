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
        PersonController.view(p);
    }

    private void editPerson(Person p)
    {
        Form f = new Form();
        PersonAdapter personAdapter = new PersonAdapter(p);
        personAdapter.setOnDoneAction(PersonController::view);
        f.injectAdapter(personAdapter);

        Resources.inst.getOtp().injectObservableTab(p.getName(),
                f.getForm(), true);
    }

    private void viewInsurance(Insurance i)
    {
        // TODO Create Insurance Controller
        //InsuranceController.view(i);
    }

    private void editInsurance(Insurance i)
    {
        // TODO Create Insurance Controller
        Form f = new Form();
        switch (i.identify()) {
            case CAR:
                CarAdapter carAdapter = new CarAdapter(i.getCustomer(), (Car)i);
                //carAdapter.setOnDoneAction(CarController::view);
                f.injectAdapter(carAdapter);
                break;
            case BOAT:
                BoatAdapter boatAdapter = new BoatAdapter(i.getCustomer(), (Boat)i);
                //boatAdapter.setOnDoneAction(BoatController::view);
                f.injectAdapter(boatAdapter);
                break;
            case HOUSE:
                HouseAdapter houseAdapter = new HouseAdapter(i.getCustomer(), (House)i);
                //houseAdapter.setOnDoneAction(HouseController::view);
                f.injectAdapter(houseAdapter);
                break;
            case TRAVEL:
                TravelAdapter travelAdapter = new TravelAdapter(i.getCustomer(), (Travel)i);
                //travelAdapter.setOnDoneAction(TravelController::view);
                f.injectAdapter(travelAdapter);
                break;
            default: return;
        }
        Resources.inst.getOtp().injectObservableTab(i.getCustomer().getName(),
                f.getForm(), true);
    }

    private void viewClaim(Claim c)
    {
        // TODO Create ClaimController
        //ClaimController.view(c);
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
