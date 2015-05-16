package main.view.form.node;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * FormNode.java
 * @param <T>  the type parameter
 */
public abstract class FormNode<T>
{
    private boolean required;

    /**
     * Gets key.
     *
     * @return the key
     */
    public abstract Label getKey();

    /**
     * Gets error.
     *
     * @return the error
     */
    public abstract Label getError();

    /**
     * Gets node.
     *
     * @return the node
     */
    public abstract Node getNode();

    /**
     * Gets type.
     *
     * @return the type
     */
    public abstract Type getType();

    /**
     * Gets value.
     *
     * @return the value
     */
    public abstract String getValue();

    /**
     * Gets data.
     *
     * @return the data
     */
    public abstract T getData();

    /**
     * Sets required.
     *
     * @param required the required
     */
    public void setRequired(boolean required)
    {
        this.required = required;
    }

    /**
     * Gets required.
     *
     * @return the required
     */
    public boolean getRequired()
    {
        return required;
    }
}
