package main.view.concrete.statistics;

import javafx.scene.control.Label;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class PersonStatisticsView extends StandardGridPane
{
    private List<Person> persons;

    public PersonStatisticsView(List<Person> persons)
    {
        super(1);
        this.persons = persons;

        add(new Label("Fill me up baby!"), 0, 0);
    }

    @Override
    public StandardGridPane getNode() { return this; }
}
