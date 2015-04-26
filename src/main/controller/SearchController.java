package main.controller;

import main.App;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.SearchResult;
import main.view.table.ClaimTable;
import main.view.table.InsuranceTable;
import main.view.table.PersonTable;

import java.util.List;

/**
 * Created by alex on 4/23/15.
 */
class SearchController
{
    private String keyword;

    public SearchController(Payload p)
    {
        this.keyword = p.getString();
        search();
    }

    public void search()
    {
        List<Person> persons = (List<Person>)Storage.getInstance().get(App.PERSONS);
        List<Insurance> insurances = (List<Insurance>)Storage.getInstance().get(App.INSURANCES);
        List<Claim> claims = (List<Claim>)Storage.getInstance().get(App.CLAIMS);

        /* person table and actions */
        PersonTable personTable = new PersonTable();
        personTable.setOnEditAction(PersonController::edit);
        personTable.setOnViewAction(PersonController::view);
        personTable.setOnDoubleClickAction(PersonController::view);

        /* insurance table and actions */
        InsuranceTable insuranceTable = new InsuranceTable();
        insuranceTable.setOnEditAction(InsuranceController::edit);
        insuranceTable.setOnViewAction(InsuranceController::view);
        insuranceTable.setOnDoubleClickAction(InsuranceController::view);

        /* claim table and actions */
        ClaimTable claimTable = new ClaimTable();
        claimTable.setOnEditAction(ClaimController::edit);
        claimTable.setOnViewAction(ClaimController::view);
        claimTable.setOnDoubleClickAction(ClaimController::view);

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
}
