package main.view.form.adapter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;
import main.model.Model;
import main.model.search.Search;
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
public class SearchAdapter implements Formable<Search>
{
    private FormValueNode input;
    private Search search;

    private ButtonBase callBackEvent = new ButtonBase()
    {
        @Override
        public void fire()
        {
            fireEvent(new ActionEvent());
        }
    };


    public SearchAdapter()
    {
        input = new FormValueNode.Builder(Loc.c("search"))
                .regex(StringMatcher.getBaseString())
                .error(Loc.c("search_error"))
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
        search = new Search(input.getValue());
        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Search> s)
    {
        callBackEvent.setOnAction(e -> s.accept(search));
    }

    @Override
    public void submitActuator(Button button)
    {
        input.connectToButton(button);
    }
}
