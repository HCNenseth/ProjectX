package main.view;

import main.view.form.FormNode;
import main.view.form.Formable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/21/15.
 */
public class TestPerson implements Formable
{
    FormNode firstname;
    FormNode lastname;

    public TestPerson()
    {
        firstname = new FormNode.Builder("Firstname")
                .error("Firstname must be set")
                .build();

        lastname = new FormNode.Builder("Lastname")
                .error("Lastname must be set")
                .build();
    }
    @Override
    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(firstname);
        tmp.add(lastname);

        return tmp;
    }
}
