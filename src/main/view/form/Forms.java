package main.view.form;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/20/15.
 */
public class Forms
{
    private GridPane gp;
    private List<FormNode> nodes = new ArrayList<>();

    public Forms()
    {
        gp = new GridPane();
        gp.addColumn(2);
    }

    public void injectPart(FormNode fn)
    {
        nodes.add(fn);
        gp.addRow(1);
    }
}
