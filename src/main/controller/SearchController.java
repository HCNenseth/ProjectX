package main.controller;

import main.localization.Loc;
import main.model.Model;
import main.model.Status;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.model.search.Search;
import main.view.Resources;
import main.view.concrete.SearchResultView;
import main.view.form.Form;
import main.view.form.adapter.SearchAdapter;

import java.time.LocalDate;
import java.util.Set;

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
        Set<Status> statuses = search.getStatus();
        Set<Model.ModelType> models = search.getModels();

        /* put the tables into a search result view */
        SearchResultView searchResult = new SearchResultView();

        // Persons
        if (models.contains(Model.ModelType.PERSON)) {
            searchResult.addTable(TableController.getPersonTable(Person.getPersons().stream()
                                    .filter(i ->
                                            (i.query(keyword)
                                                    && i.between(from, to)
                                                    && statuses.contains(i.getStatus())))
                    ).getTable(),
                    Model.ModelType.PERSON,
                    Loc.c("persons"));
        }

        // Insurances
        if (models.contains(Model.ModelType.INSURANCE)) {
            searchResult.addTable(TableController.getInsuranceTable(Insurance.getInsurances().stream()
                                    .filter(i ->
                                            (i.query(keyword)
                                                    && i.between(from, to)
                                                    && statuses.contains(i.getStatus())))
                    ).getTable(),
                    Model.ModelType.INSURANCE,
                    Loc.c("insurances"));
        }

        // Claims
        if (models.contains(Model.ModelType.CLAIM)) {
            searchResult.addTable(TableController.getClaimsTable(Claim.getClaims().stream()
                                    .filter(i ->
                                            (i.query(keyword)
                                                    && i.between(from, to)
                                                    && statuses.contains(i.getStatus())))
                    ).getTable(),
                    Model.ModelType.CLAIM,
                    Loc.c("claims"));
        }

        f.setCallbackData(searchResult.getNode());
    }
}
