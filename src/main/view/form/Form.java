package main.view.form;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.localization.Loc;

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
     * @param formable
     */
    public void injectObject(Formable formable)
    {
        nodes = formable.getNodes();
        nodes.stream().forEach(this::addPart);
    }

    /**
     * Return gridpane and add the button bottom.
     * @return
     */
    public GridPane getForm() {
        gp.addRow(rowNum++);

        gp.add(submit, 2, rowNum);
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
        gp.add(fn.getValue(), 1, rowNum);
        gp.add(fn.getError(), 2, rowNum);
    }

    /**
     * Validate form method.
     */
    public void validate()
    {
        // loop through all nodes
        for (FormNode fn : nodes) {
            // reset all error fields (hide them)
            fn.getError().setVisible(false);
            if (fn.getRequired()) {
                if (!fn.getValue().getText().matches(fn.getRegex())) {
                    // Show error message and set class valid to false
                    fn.getError().setVisible(true);
                    valid = false;
                }
            }
        }
    }
}
