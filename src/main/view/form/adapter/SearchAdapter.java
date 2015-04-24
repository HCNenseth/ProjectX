package main.view.form.adapter;

import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;
import main.view.form.Formable;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/24/15.
 */
public class SearchAdapter implements Formable
{
    private FormValueNode input;

    public SearchAdapter()
    {
        input = new FormValueNode.Builder(Loc.get("search")).build();
    }

    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();

        tmp.add(input);

        return tmp;
    }

    public void callback()
    {
        Mediator.inst.router(Signal.SEARCH,
                new Payload(input.getValue()));
    }
}
