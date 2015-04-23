package main.controller;

import main.view.form.Form;
import main.view.form.adapter.PersonAdapter;

/**
 * Created by alex on 4/23/15.
 */
public class Person
{

    public Person()
    {

    }

    public void newPerson()
    {
        // TODO inject new tab with customer form
        Form f = new Form();
        f.injectAdapter(new PersonAdapter());
    }
}
