package main.view.form;

import javafx.scene.control.Button;
import main.model.Model;
import main.view.form.node.FormNode;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alex on 4/21/15.
 */
public interface Formable<T extends Model>
{
    List<FormNode> getNodes();
    void callback();
    void setOnDoneAction(Consumer<T> c);
    default void submitActuator(Button b) {}
}
