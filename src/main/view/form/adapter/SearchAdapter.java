package main.view.form.adapter;

import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alex on 4/24/15.
 */
public class SearchAdapter implements Formable<Person> // TODO this is obviously wrong
{
    private FormValueNode input;

    public SearchAdapter()
    {
        input = new FormValueNode.Builder(Loc.get("search"))
                .regex(StringMatcher.getBaseString())
                .error(Loc.get("search_error"))
                .build();
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

    // TODO this is obviously wrong!
    @Override
    public void setOnDoneAction(Consumer<Person> c)
    {
        callBackEvent.setOnAction(e -> c.accept(null));
    }
}
