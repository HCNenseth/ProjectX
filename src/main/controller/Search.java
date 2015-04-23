package main.controller;

import main.localization.Loc;
import main.view.Resources;

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
        /*
        List<main.model.person.Person> tmp = (List<main.model.person.Person>)Storage
                .getInstance().get("persons");

        // TODO send data to a search result view.
        tmp.stream().filter(i -> i.query(keyword));
        */

        Resources.inst.getOtp().injectObservableTab(Loc.get("search_results"),
                true);
    }
}
