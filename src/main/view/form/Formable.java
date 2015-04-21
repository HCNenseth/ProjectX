package main.view.form;

import main.view.form.node.FormNode;

import java.util.List;

/**
 * Created by alex on 4/21/15.
 */
public interface Formable
{
    List<FormNode> getNodes();
    void callback();
}
