package main.view.form;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.localization.Loc;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;
import main.view.form.node.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Form class for wrapping labels and textFields in a
 * gridpane and validating them upon submit. Depends heavy
 * on the FormNode class.
 */
public class Form
{
    private GridPane gp;
    private List<FormNode> nodes = new ArrayList<>();
    private Formable caller;
    private Button submit;
    private int rowNum = 0;
    private boolean valid = true;

    public Form()
    {
        gp = new GridPane();
        gp.getColumnConstraints().add(new ColumnConstraints(200));

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
        gp.addRow(rowNum++);

        gp.add(submit, 1, rowNum);
        return gp;
    }

    /**
     * Expand gridpane and add the formnode.
     * @param fn
     */
    private void addPart(FormNode fn)
    {
        gp.addRow(rowNum++);
        gp.add(fn.getKey(), 0, rowNum);
        gp.add(fn.getNode(), 1, rowNum);
        gp.add(fn.getError(), 1, ++rowNum);
    }

    /**
     * Validate form method.
     */
    public void validate()
    {
        // reset valid status
        valid = true;

        // loop through all nodes
        for (FormNode fn : nodes) {
            // reset all error fields (hide them)
            fn.getError().setVisible(false);

            // Type.VALUE
            if (fn.getRequired() && fn.getType().equals(Type.VALUE)) {
                FormValueNode fvn = (FormValueNode)fn;
                if (!fvn.getNode().getText().matches(fvn.getRegex())) {
                    // Show error message and set class valid to false
                    fn.getError().setVisible(true);
                    valid = false;
                }
            }
        }

        if (valid) { caller.callback(); }
    }
}
