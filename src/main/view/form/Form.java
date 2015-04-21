package main.view.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/20/15.
 */
public class Form
{
    private GridPane gp;
    private List<FormNode> nodes = new ArrayList<>();
    private int rowNum;

    public Form()
    {
        gp = new GridPane();
        gp.addColumn(2);
        rowNum = 0;
    }

    public void injectObject(Formable formable)
    {
        formable.getNodes().stream().forEach(this::addPart);
    }

    public GridPane getForm() { return gp; }

    private void addPart(FormNode fn)
    {
        nodes.add(fn);
        gp.addRow(rowNum++);
        gp.add(new Label(fn.getKey()), 0, rowNum);
        gp.add(new TextField(fn.getValue()), 1, rowNum);
    }
}
