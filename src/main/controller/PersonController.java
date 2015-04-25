package main.controller;

import main.localization.Loc;
import main.model.person.Person;
import main.view.Resources;
import main.view.form.Form;
import main.view.form.adapter.PersonAdapter;

/**
 * Created by alex on 4/23/15.
 */
class PersonController
{

    private PersonController() {}

    public static void create()
    {
        Form f = new Form();
        f.injectAdapter(new PersonAdapter());
        Resources.inst.getOtp().injectObservableTab(Loc.get("new_customer"),
                f.getForm(), true);
    }

    public static void view(Person person)
    {
        Resources.inst.getOtp().injectOfflineTab(person.getName(), true);
    }
}
