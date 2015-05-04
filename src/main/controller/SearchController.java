package main.controller;

import main.config.Config;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
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
    private String keyword;

    public SearchController(Payload p)
    {
        if (p != null) {
            this.keyword = p.getString();
            search();
        } else {
            create();
        }
    }

    private void create()
    {
        Form f = new Form();
        f.injectAdapter(new SearchAdapter());

        Resources.inst.getOtp().injectObservableTab(Loc.c("search"),
                f.getForm(), true);
    }

    private void search()
    {
        List<Person> persons = (List<Person>)Storage.getInstance().get(Config.PERSONS);
        List<Insurance> insurances = (List<Insurance>)Storage.getInstance().get(Config.INSURANCES);
        List<Claim> claims = (List<Claim>)Storage.getInstance().get(Config.CLAIMS);

        /* put the tables into a search result view */
        SearchResultView searchResult = new SearchResultView();

        if (persons.size() > 0)
            searchResult.addTable(TableController.getPersonTable(persons.stream()
                    .filter(i -> i.query(keyword))).getTable(), Loc.c("persons"));
        if (insurances.size() > 0)
            searchResult.addTable(TableController.getInsuranceTable(insurances.stream()
                    .filter(i -> i.query(keyword))).getTable(), Loc.c("insurances"));
        if (claims.size() > 0)
            searchResult.addTable(TableController.getClaimsTable(claims.stream()
                    .filter(i -> i.query(keyword))).getTable(), Loc.c("claims"));

        /* insert search result view into tab */
        Resources.inst.getOtp().injectObservableTab(Loc.c("search_results"),
                searchResult.getNode(), true);
    }
}
