package main.view.form.adapter;

import main.localization.Loc;
import main.validator.StringMatcher;
import main.view.form.FormNode;
import main.view.form.Formable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/21/15.
 */
public class PersonAdapter implements Formable
{
    FormNode firstname;
    FormNode lastname;
    FormNode city;
    FormNode streetAddress;
    FormNode postNum;

    public PersonAdapter()
    {
        firstname = new FormNode.Builder(Loc.get("firstname"))
                .error(Loc.get("firstname_error"))
                .regex(StringMatcher.getFirstname())
                .build();

        lastname = new FormNode.Builder(Loc.get("lastname"))
                .error(Loc.get("lastname_error"))
                .regex(StringMatcher.getLastname())
                .build();

        city = new FormNode.Builder(Loc.get("city"))
                .required(false).build();

        streetAddress = new FormNode.Builder(Loc.get("street_address"))
                .required(false).build();

        postNum = new FormNode.Builder(Loc.get("postal_code"))
                .required(false).build();
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

        return tmp;
    }
}
