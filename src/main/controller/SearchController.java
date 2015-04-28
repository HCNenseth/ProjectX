package main.controller;

import main.App;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.SearchResultView;

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

        /* put the tables into a search result view */
        SearchResultView searchResult = new SearchResultView();

        searchResult.addTable(TableController.getPersonTable(persons.stream()
                .filter(i -> i.query(keyword))).getTable(), Loc.get("persons"));
        searchResult.addTable(TableController.getInsuranceTable(insurances.stream()
                .filter(i -> i.query(keyword))).getTable(), Loc.get("insurances"));
        searchResult.addTable(TableController.getClaimsTable(claims.stream()
                .filter(i -> i.query(keyword))).getTable(), Loc.get("claims"));

        /* insert search result view into tab */
        Resources.inst.getOtp().injectObservableTab(Loc.get("search_results"),
                searchResult.getNode(), true);
    }
}
