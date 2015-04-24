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

        PersonTable personTable = new PersonTable();
        InsuranceTable insuranceTable = new InsuranceTable();
        ClaimTable claimTable = new ClaimTable();

        persons.stream().filter(i -> i.query(keyword)).forEach(personTable::insertData);
        insurances.stream().filter(i -> i.query(keyword)).forEach(insuranceTable::insertData);
        claims.stream().filter(i -> i.query(keyword)).forEach(claimTable::insertData);

        SearchResult searchResult = new SearchResult();

        searchResult.addTable(personTable.getTable(), Loc.get("persons"));
        searchResult.addTable(insuranceTable.getTable(), Loc.get("insurances"));
        searchResult.addTable(claimTable.getTable(), Loc.get("insurances"));

        Resources.inst.getOtp().injectObservableTab(Loc.get("search_results"),
                searchResult.getNode(), true);
    }
}
