package main.controller;

import main.config.Config;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.model.search.Search;
import main.view.Resources;
import main.view.concrete.SearchResultView;
import main.view.form.Form;
import main.view.form.adapter.SearchAdapter;

import java.util.List;

/**
 * Created by alex on 4/23/15.
 */
class SearchController
{
    Form f;

    public SearchController(Payload p)
    {
        f = new Form();
        create();
    }

    private void create()
    {
        SearchAdapter searchAdapter = new SearchAdapter();
        searchAdapter.setOnDoneAction(this::search);
        f.injectAdapter(searchAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c("search"),
                f.getForm(), true);
    }

    private void search(Search search)
    {
        String keyword = search.getKeyword();

        List<Person> persons = (List<Person>)Storage.getInstance().get(Config.PERSONS);
        List<Insurance> insurances = (List<Insurance>)Storage.getInstance().get(Config.INSURANCES);
        List<Claim> claims = (List<Claim>)Storage.getInstance().get(Config.CLAIMS);

        /* put the tables into a search result view */
        SearchResultView searchResult = new SearchResultView();

        searchResult.addTable(TableController.getPersonTable(persons.stream()
                .filter(i -> i.query(keyword))).getTable(), Loc.c("persons"));
        searchResult.addTable(TableController.getInsuranceTable(insurances.stream()
                .filter(i -> i.query(keyword))).getTable(), Loc.c("insurances"));
        searchResult.addTable(TableController.getClaimsTable(claims.stream()
                .filter(i -> i.query(keyword))).getTable(), Loc.c("claims"));

        f.setCallbackData(searchResult.getNode());
    }
}
