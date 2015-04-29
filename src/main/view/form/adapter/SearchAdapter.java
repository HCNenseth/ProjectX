package main.view.form.adapter;

import javafx.scene.control.Button;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;
import main.model.Model;
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
public class SearchAdapter implements Formable<Model>
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

    @Override
    public void setOnDoneAction(Consumer<Model> c)
    {
        callBackEvent.setOnAction(e -> c.accept(null));
    }

    @Override
    public void submitActuator(Button button)
    {
        input.connectToButton(button);
    }
}
