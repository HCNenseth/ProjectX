package main.view.form;

import javafx.scene.control.Button;
import main.model.Model;
import main.view.form.node.FormNode;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alex on 4/21/15.
 * @param <T>  the type parameter
 */
public interface Formable<T extends Model>
{
    /**
     * Gets visible nodes.
     *
     * @return the visible nodes
     */
    List<FormNode> getVisibleNodes();

    /**
     * Callback void.
     */
    void callback();

    /**
     * Sets on done action.
     *
     * @param c the c
     */
    void setOnDoneAction(Consumer<T> c);

    /**
     * Submit actuator.
     *
     * @param b the b
     */
    default void submitActuator(Button b)
    {
    }

    /**
     * Gets hidden nodes.
     *
     * @return the hidden nodes
     */
    default List<FormNode> getHiddenNodes()
    {
        return new LinkedList<>();
    }
}
