package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
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
    FormValueNode firstname;
    FormValueNode lastname;
    FormValueNode city;
    FormValueNode streetAddress;
    FormValueNode postNum;
    FormChoiceNode status;

    public PersonAdapter()
    {
        firstname = new FormValueNode.Builder(Loc.get("firstname"))
                .error(Loc.get("firstname_error"))
                .regex(StringMatcher.getFirstname())
                .build();

        lastname = new FormValueNode.Builder(Loc.get("lastname"))
                .error(Loc.get("lastname_error"))
                .regex(StringMatcher.getLastname())
                .build();

        city = new FormValueNode.Builder(Loc.get("city"))
                .required(false).build();

        streetAddress = new FormValueNode.Builder(Loc.get("street_address"))
                .required(false).build();

        postNum = new FormValueNode.Builder(Loc.get("postal_code"))
                .required(false).build();

        // TODO: This is slightly retarded, improve it somehow.
        List<Enum> statusList = new ArrayList<>();
        for (Status s : Status.values()) { statusList.add(s); }

        status = new FormChoiceNode.Builder<>(Loc.get("status"), statusList)
                .active(Status.ACTIVE)
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
        tmp.add(postNum);
        tmp.add(status);

        return tmp;
    }
}
