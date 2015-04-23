package main.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;

/**
 * Created by alex on 4/23/15.
 */
public class Search
{
    private GridPane gp;
    private Button submit;
    private TextField input;

    public Search()
    {
        gp = new GridPane();

        input = new TextField();
        submit = new Button(Loc.get("submit"));
        submit.setOnAction(e -> searchSubmit());

        gp.add(input, 0, 0);
        gp.add(submit, 1, 0);
    }

    public GridPane getNode()
    {
        return gp;
    }

    private void searchSubmit()
    {
        Mediator.inst.router(Signal.SEARCH,
                new Payload(input.getText()));
    }
}
