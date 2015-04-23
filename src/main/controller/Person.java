package main.controller;

import main.localization.Loc;
import main.view.Resources;
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
        Form f = new Form();
        f.injectAdapter(new PersonAdapter());
        Resources.inst.getOtp().injectObservableTab(Loc.get("new_customer"),
                f.getForm(), true);
    }
}
