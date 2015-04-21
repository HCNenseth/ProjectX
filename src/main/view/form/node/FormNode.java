package main.view.form.node;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by alex on 4/21/15.
 */
public abstract class FormNode<T>
{
    private boolean required;

    public abstract Label getKey();
    public abstract Label getError();
    public abstract Node getNode();
    public abstract Type getType();
    public abstract String getValue();
    public abstract T getData();

    public void setRequired(boolean required) { this.required = required; }

    public boolean getRequired() { return required; }

    @Override
    public String toString()
    {
        return "boop";
    }
}
