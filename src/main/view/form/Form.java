package main.view.form;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import main.localization.Loc;
import main.view.StandardGridPane;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;

import java.util.List;

/**
 * Form class for wrapping labels and textFields in a
 * gridpane and validating them upon submit. Depends heavy
 * on the FormNode class.
 */
public class Form extends StandardGridPane
{
    private List<FormNode> nodes;
    private List<FormNode> hiddenNodes;
    private Accordion optionalNodes;
    private TitledPane advanced;
    private Formable caller;
    private Button submit;

    private Node callBackData;
    private int rowNum = 0;
    private boolean valid = true;
    private boolean hasCallbackData = false;

    /**
     * Instantiates a new Form.
     */
    public Form()
    {
        super(ColumnCount.THREE);

        setPadding(new Insets(5));

        setHgap(5);
        setVgap(5);

        submit = new Button(Loc.c("submit"));
        submit.setOnAction(e -> this.validate());
    }

    /**
     * Inject a Formable object into form. This method should
     * implement a system for rejecting object > 1
     *
     * @param caller the caller
     */
    public void injectAdapter(Formable caller)
    {
        this.caller = caller;

        nodes = this.caller.getVisibleNodes();

        this.caller.submitActuator(submit);
        nodes.stream().forEach(this::addPart);

        hiddenNodes = this.caller.getHiddenNodes();
        if (hiddenNodes.size() > 0) {
            setupAdvancedSection();
        }
    }

    /**
     * Return gridpane and add the button bottom.
     *
     * @return form
     */
    public GridPane getForm()
    {
        if (optionalNodes != null) {
            add(optionalNodes, 1, rowNum++);
        }

        add(submit, 1, rowNum++);
        return this;
    }

    /**
     * Make possible to return data to form, eg after validate callback.
     *
     * @param node - any JavaFX node.
     */
    public void setCallbackData(Node node)
    {
        if (hasCallbackData) {
            getChildren().remove(callBackData);
        }

        add(node, 0, rowNum, 3, 1);
        callBackData = node;
        hasCallbackData = true;
    }

    /**
     * Expand gridpane and add the formnode.
     *
     * @param fn
     */
    private void addPart(FormNode fn)
    {
        add(fn.getKey(), 0, rowNum);
        add(fn.getNode(), 1, rowNum);
        add(fn.getError(), 2, rowNum++);
    }

    private void setupAdvancedSection()
    {
        StandardGridPane sgp = new StandardGridPane(ColumnCount.THREE)
        {
            @Override
            public StandardGridPane getNode()
            {
                return this;
            }
        };

        int innerRowNum = 0;

        optionalNodes = new Accordion();
        for (FormNode n : hiddenNodes) {
            sgp.add(n.getKey(), 0, innerRowNum);
            sgp.add(n.getNode(), 1, innerRowNum);
            sgp.add(n.getError(), 2, innerRowNum++);
        }
        advanced = new TitledPane(Loc.c("advanced"), sgp);
        optionalNodes.getPanes().addAll(advanced);
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
                        FormValueNode fvn = (FormValueNode) fn;
                        if (!fvn.getNode().getText().matches(fvn.getRegex())) {
                            // Show error message and set class valid to false
                            fn.getError().setVisible(true);
                            valid = false;
                        }
                    }
                    break;
            }
        }

        if (valid) {
            caller.callback();
        }
    }

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }
}
