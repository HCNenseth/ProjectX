package main.view.form;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import main.model.Model;
import main.view.form.node.FormNode;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alex on 4/21/15.
 */
public interface Formable<T extends Model>
{
    // TODO replace this bad event object with a bit more abstract.
    ButtonBase callBackEvent = new ButtonBase()
    {
        @Override
        public void fire()
        {
            fireEvent(new ActionEvent());
        }
    };

    List<FormNode> getNodes();
    void callback();
    void setOnDoneAction(Consumer<T> c);
    default void submitActuator(Button b) {}
}
