package main.view;

import javafx.scene.layout.Pane;
import main.view.form.Form;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by alex on 4/10/15.
 */
public class ObservablePane extends Observable
{
    private BasePane pane;

    public ObservablePane(Observer observer, String title)
    {
        this.pane = new BasePane(title);
        addObserver(observer);

        /**
         * Some stupid test code only!
         */

        Form f = new Form();
        f.injectObject(new TestPerson());
        pane.setContent(f.getForm());
    }

    public Pane getPane()
    {
        return pane;
    }

}
