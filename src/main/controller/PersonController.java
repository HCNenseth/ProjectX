package main.controller;

import main.config.Config;
import main.controller.adapter.PersonAdapter;
import main.localization.Loc;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.PersonView;
import main.view.form.Form;

/**
 * PersonController.java
 */
public class PersonController
{
    private static Form f;

    private PersonController()
    {
    }

    /**
     * Create void.
     */
    public static void create()
    {
        f = new Form();

        PersonAdapter personAdapter = new PersonAdapter();

        personAdapter.setOnDoneAction(PersonController::view);
        f.injectAdapter(personAdapter);

        Resources.inst.getOtp().injectObservableTab(Loc.c("new_customer"),
                f.getForm(), f, Config.PERSON_TAB_IMAGE, true);
    }

    /**
     * View void.
     *
     * @param person the person
     */
    public static void view(Person person)
    {
        // Remove all tabs dealing with this person
        Resources.inst.getOtp().closeObservableTabs(person);
        Resources.inst.getOtp().closeObservableTabs(f);

        PersonView personView = new PersonView(person);

        Resources.inst.getOtp().injectObservableTab(person.getName(),
                personView.getNode(), person, Config.PERSON_TAB_IMAGE, true);
    }

    /**
     * Edit void.
     *
     * @param person the person
     */
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
