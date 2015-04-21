package main.view.form.node;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by alex on 4/21/15.
 */
public abstract class FormNode
{
    private boolean required;

    public abstract Label getKey();
    public abstract Label getError();
    public abstract Node getValue();
    public abstract Type getType();

    public void setRequired(boolean required) { this.required = required; }

    public boolean getRequired() { return required; }
}
