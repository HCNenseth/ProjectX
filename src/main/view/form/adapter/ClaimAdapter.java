package main.view.form.adapter;

import main.localization.Loc;
import main.model.Status;
import main.model.claim.Claim;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by HansChristian on 28.04.2015.
 */
public class ClaimAdapter implements Formable<Claim>
{

    private FormValueNode description;
    private FormChoiceNode status;
    private FormValueNode contacts;


    private Person customer;

    public ClaimAdapter(Person customer, Claim claim)
    {
        this.customer = customer;
        initNodes();
    }


    private void initNodes()
    {
        description = new FormValueNode.Builder(Loc.get("desc"))
                .build();

        contacts = new FormValueNode.Builder(Loc.get("contacts"))
                .build();

        List<Enum> statusList = new ArrayList();
        for(Status s : Status.values()) { statusList.add(s); }

        status = new FormChoiceNode.Builder<>(Loc.get("getStatus"), statusList)
            .active(Status.ACTIVE )
            .build();
    }

    @Override
    public void callback()
    {

    }

    @Override
    public void setOnDoneAction(Consumer<Claim> c)
    {
        callBackEvent.setOnAction(e -> c.accept(person));
    }

}
