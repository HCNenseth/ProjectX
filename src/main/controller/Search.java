package main.controller;

import main.model.Storage;
import main.model.person.*;

import java.util.ArrayList;
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
        List<main.model.person.Person> tmp = (List< main.model.person.Person>)Storage
                .getInstance().get("persons");

        // TODO send data to a search result view.
        tmp.stream().filter(i -> i.query(keyword));
    }
}
