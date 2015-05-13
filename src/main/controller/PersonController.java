package main.controller;

import main.config.Config;
import main.localization.Loc;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.PersonView;
import main.view.form.Form;
import main.view.form.adapter.PersonAdapter;

/**
 * PersonController.java
 */
public class PersonController
{
    private PersonController() {}
    private static Form f;

    public static void create()
    {
        f = new Form();

        PersonAdapter personAdapter = new PersonAdapter();

        personAdapter.setOnDoneAction(PersonController::view);
        f.injectAdapter(personAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c("new_customer"),
                f.getForm(), f, Config.PERSON_TAB_IMAGE, true);
    }

    public static void view(Person person)
    {
        // Remove all tabs dealing with this person
        Resources.inst.getOtp().closeObservableTabs(person);
        Resources.inst.getOtp().closeObservableTabs(f);

        PersonView personView = new PersonView(person);

        Resources.inst.getOtp().injectObservableTab(person.getName(),
                personView.getNode(), person, Config.PERSON_TAB_IMAGE, true);
    }

    public static void edit(Person person)
    {
        // Remove all tabs dealing with this person
        Resources.inst.getOtp().closeObservableTabs(person);

        f = new Form();
        PersonAdapter personAdapter = new PersonAdapter(person);
        personAdapter.setOnDoneAction(PersonController::view);
        f.injectAdapter(personAdapter);

        Resources.inst.getOtp().injectObservableTab(person.getName(),
                f.getForm(), person, Config.PERSON_TAB_IMAGE, true);
    }
}
