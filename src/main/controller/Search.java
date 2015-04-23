package main.controller;

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
        // TODO change this tmp list with actual data.
        List<main.model.person.Person> tmp = new ArrayList<>();

        // TODO send data to a search result view.
        tmp.stream().filter(i -> i.query(keyword));
    }
}
