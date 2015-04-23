package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;
import main.view.form.Formable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/21/15.
 */
public class PersonAdapter implements Formable
{
    private FormValueNode firstname;
    private FormValueNode lastname;
    private FormValueNode city;
    private FormValueNode streetAddress;
    private FormValueNode postalCode;
    private FormChoiceNode status;

    private Person person;

    // TODO rethink this implementation decision.
    private boolean editMode = false;

    public PersonAdapter(Person person)
    {
        this();
        this.person = person;
        editMode = true;
    }

    public PersonAdapter()
    {
        firstname = new FormValueNode.Builder(Loc.get("firstname"))
                .value(editMode ? person.getFirstname() : "")
                .error(Loc.get("firstname_error"))
                .regex(StringMatcher.getFirstname())
                .build();

        lastname = new FormValueNode.Builder(Loc.get("lastname"))
                .value(editMode ? person.getLastname() : "")
                .error(Loc.get("lastname_error"))
                .regex(StringMatcher.getLastname())
                .build();

        city = new FormValueNode.Builder(Loc.get("city"))
                .value(editMode ? person.getCity() : "")
                .required(false)
                .build();

        streetAddress = new FormValueNode.Builder(Loc.get("street_address"))
                .value(editMode ? person.getStreetAddress() : "")
                .required(false)
                .build();

        postalCode = new FormValueNode.Builder(Loc.get("postal_code"))
                .value(editMode ? person.getPostalCode() : "")
                .required(false)
                .build();

        List<Enum> statusList = new ArrayList<>();
        for (Status s : Status.values()) { statusList.add(s); }

        status = new FormChoiceNode.Builder<>(Loc.get("status"), statusList)
                .active(editMode ? person.getStatus() : Status.ACTIVE)
                .build();

    }

    @Override
    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(firstname);
        tmp.add(lastname);
        tmp.add(city);
        tmp.add(streetAddress);
        tmp.add(postalCode);
        tmp.add(status);

        return tmp;
    }

    public void callback()
    {
        if (editMode) {
            person.setFirstname(firstname.getValue());
            person.setLastname(lastname.getValue());
            person.setPostalCode(postalCode.getValue());
            person.setStreetAddress(streetAddress.getValue());
            person.setCity(city.getValue());
            person.setStatus((Status) status.getData()); // tsk tsk
        } else {
            person = new Person.Builder(firstname.getValue(), lastname.getValue())
                    .streetAddress(streetAddress.getValue())
                    .postalCode(postalCode.getValue())
                    .status((Status)status.getData()) // tsk tsk
                    .city(city.getValue())
                    .build();
        }
    }
}
