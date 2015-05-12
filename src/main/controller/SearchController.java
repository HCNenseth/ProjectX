package main.controller;

import main.localization.Loc;
import main.model.Model;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.model.search.Search;
import main.view.Resources;
import main.view.concrete.SearchResultView;
import main.view.form.Form;
import main.view.form.adapter.SearchAdapter;

import java.time.LocalDate;

/**
 * SearchController.java
 */
class SearchController
{
    private Form f;
    private Payload payload;


    public SearchController(Payload payload)
    {
        this.payload = payload;
        f = new Form();
        create();
    }

    private void create()
    {
        SearchAdapter searchAdapter = new SearchAdapter();
        searchAdapter.setOnDoneAction(this::search);
        f.injectAdapter(searchAdapter);

        Resources.inst.getOtp().injectOfflineTab(Loc.c("search"),
                f.getForm(), true);
    }

    private void search(Search search)
    {
        String keyword = search.getKeyword();
        LocalDate from = search.getFrom();
        LocalDate to = search.getTo();

        /* put the tables into a search result view */
        SearchResultView searchResult = new SearchResultView();

        // Persons
        searchResult.addTable(TableController.getPersonTable(Person.getPersons().stream()
                .filter(i -> (i.query(keyword) && i.between(from, to)))).getTable(),
                Model.ModelType.PERSON,
                Loc.c("persons"));

        // Insurances
        searchResult.addTable(TableController.getInsuranceTable(Insurance.getInsurances().stream()
                .filter(i -> (i.query(keyword) && i.between(from, to)))).getTable(),
                Model.ModelType.INSURANCE,
                Loc.c("insurances"));

        // Claims
        searchResult.addTable(TableController.getClaimsTable(Claim.getClaims().stream()
                .filter(i -> (i.query(keyword) && i.between(from, to)))).getTable(),
                Model.ModelType.CLAIM,
                Loc.c("claims"));

        f.setCallbackData(searchResult.getNode());
    }
}
