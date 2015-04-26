package main.view.form;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.App;
import main.localization.Loc;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Form class for wrapping labels and textFields in a
 * gridpane and validating them upon submit. Depends heavy
 * on the FormNode class.
 */
public class Form extends GridPane
{
    private List<FormNode> nodes = new ArrayList<>();
    private Formable caller;
    private Button submit;
    private int rowNum = 0;
    private boolean valid = true;

    public Form()
    {
        setMinWidth(App.WIDTH - (App.WIDTH / 20));

        ColumnConstraints keyColumn = new ColumnConstraints();
        //keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(20);

        ColumnConstraints valueColumn = new ColumnConstraints();
        //valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(80);

        getColumnConstraints().add(0, keyColumn);
        getColumnConstraints().add(1, valueColumn);

        setHgap(5);
        setVgap(5);

        submit = new Button(Loc.get("submit"));
        submit.setOnAction(e -> this.validate());
    }

    /**
     * Inject a Formable object into form. This method should
     * implement a system for rejecting object > 1
     * @param caller
     */
    public void injectAdapter(Formable caller)
    {
        this.caller = caller;
        nodes = this.caller.getNodes();
        nodes.stream().forEach(this::addPart);
    }

    /**
     * Return gridpane and add the button bottom.
     * @return
     */
    public GridPane getForm() {
        addRow(rowNum++);

        add(submit, 1, rowNum);
        return this;
    }

    /**
     * Expand gridpane and add the formnode.
     * @param fn
     */
    private void addPart(FormNode fn)
    {
        addRow(rowNum++);
        add(fn.getKey(), 0, rowNum);
        add(fn.getNode(), 1, rowNum);
        add(fn.getError(), 1, ++rowNum);

        setFillWidth(fn.getNode(), true);
    }

    /**
     * Validate form method.
     * This is the class most important method.
     * Its responsibilities is to loop through all
     * the FormNodes and validate them. If none of
     * these tests failed call upon the callers
     * callback method.
     */
    public void validate()
    {
        // reset valid getStatus
        valid = true;

        // loop through all nodes
        for (FormNode fn : nodes) {
            // reset all error fields (hide them)
            fn.getError().setVisible(false);

            // switch on because each node might have different behaviours
            switch (fn.getType()) {
                case VALUE:
                    // check if required or value is not blank
                    if (fn.getRequired() || !fn.getValue().equals("")) {
                        FormValueNode fvn = (FormValueNode)fn;
                        if (!fvn.getNode().getText().matches(fvn.getRegex())) {
                            // Show error message and set class valid to false
                            fn.getError().setVisible(true);
                            valid = false;
                        }
                    }
                    break;
            }
        }

        if (valid) { caller.callback(); }
    }
}
