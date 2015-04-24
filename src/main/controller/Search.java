package main.controller;

import main.localization.Loc;
import main.model.Storage;
import main.model.person.Person;
import main.view.Resources;
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
        List<Person> persons = (List<Person>)Storage.getInstance().get("persons");

        PersonTable personTable = new PersonTable();

        persons.stream().filter(i -> i.query(keyword)).forEach(personTable::insertData);

        Resources.inst.getOtp().injectObservableTab(Loc.get("search_results"),
                personTable.getTable(), true);
    }
}
