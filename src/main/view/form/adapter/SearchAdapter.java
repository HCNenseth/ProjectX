package main.view.form.adapter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import main.localization.Loc;
import main.model.Model;
import main.model.Status;
import main.model.search.Search;
import main.validator.StringMatcher;
import main.view.form.Formable;
import main.view.form.node.FormCheckBoxesNode;
import main.view.form.node.FormDateNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * SearchAdapter.java
 */
public class SearchAdapter implements Formable<Search>
{
    private FormValueNode input;
    private FormCheckBoxesNode<Status> status;
    private FormCheckBoxesNode<Model.ModelType> models;
    private FormDateNode from;
    private FormDateNode to;
    private Search search;

    private int fromYear = 1930, fromMonth = 01, fromDay = 01;

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

        from = new FormDateNode.Builder(Loc.c("from"), LocalDate.of(fromYear,
                fromMonth, fromDay))
                .build();

        to = new FormDateNode.Builder(Loc.c("to"), LocalDate.now())
                .build();

        List<Status> statusList = new ArrayList<>(Arrays.asList(Status.values()));

        status = new FormCheckBoxesNode.Builder<>(Loc.c("status"), statusList)
                .active(Status.ACTIVE, Status.INACTIVE)
                .build();

        List<Model.ModelType> modelList = new ArrayList<>(
                Arrays.asList(Model.ModelType.values()));

        models = new FormCheckBoxesNode.Builder<>(Loc.c("types"), modelList)
                .remove(Model.ModelType.SEARCH)
                .active(Model.ModelType.INSURANCE,
                        Model.ModelType.PERSON, Model.ModelType.CLAIM)
                .build();
    }

    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = new ArrayList<>();

        tmp.add(input);

        return tmp;
    }

    public List<FormNode> getHiddenNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(from);
        tmp.add(to);
        tmp.add(models);
        tmp.add(status);

        return tmp;
    }


    public void callback()
    {
        search = new Search.Builder(input.getValue())
                .from(from.getData())
                .to(to.getData())
                .status(status.getData())
                .models(models.getData())
                .build();

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
