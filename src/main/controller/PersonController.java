package main.controller;

import main.localization.Loc;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.PersonView;
import main.view.form.Form;
import main.view.form.adapter.PersonAdapter;

/**
 * Created by alex on 4/23/15.
 */
public class PersonController
{
    private PersonController() {}

    public static void create()
    {
        Form f = new Form();
        PersonAdapter personAdapter = new PersonAdapter();
        personAdapter.setOnDoneAction(PersonController::view);
        f.injectAdapter(personAdapter);
        Resources.inst.getOtp().injectObservableTab(Loc.get("new_customer"),
                f.getForm(), true);
    }

    public static void view(Person person)
    {
        // Remove all tabs dealing with this person
        Resources.inst.getOtp().closeObservableTabs(person);

        // TODO insert something more meaningful.
        PersonView personView = new PersonView(person);

        Resources.inst.getOtp().injectObservableTab(person.getName(),
                personView.getNode(), person, true);
    }

    public static void edit(Person person)
    {
        // Remove all tabs dealing with this person
        Resources.inst.getOtp().closeObservableTabs(person);

        Form f = new Form();
        PersonAdapter personAdapter = new PersonAdapter(person);
        personAdapter.setOnDoneAction(PersonController::view);
        f.injectAdapter(personAdapter);

        Resources.inst.getOtp().injectObservableTab(person.getName(),
                f.getForm(), person, true);
    }
}
