package main.view.form.node;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormCheckBoxesNode<T extends Enum> extends FormNode<Set<T>>
{
    private String key;
    private String error;
    private boolean required;
    private boolean lastOneActive;

    private Label keyLabel;
    private Label errorLabel;

    private List<T> data = new LinkedList<>();
    private Set<T> active = new HashSet<>();
    private HashMap<T, CheckBox> map = new HashMap<>();

    private HBox wrapper = new HBox();
    private int cellGap = 5;

    public static class Builder<E extends Enum>
    {
        private String key;
        private List<E> values = new LinkedList<>();
        private List<E> active = new LinkedList<>();
        private String error = "";
        private String regex = "";
        private boolean required = true;
        private boolean lastOneActive = true;

        public Builder(String key, List<E> data)
        {
            this.key = key;
            values.addAll(data);
        }

        public Builder active(E ... active)
        {
            for (E e : active) { this.active.add(e); }
            return this;
        }

        public Builder remove(E ... removed)
        {
            for (E e : removed) { this.values.remove(e); }
            return this;
        }

        public Builder lastOneActive(boolean val)
        {
            this.lastOneActive = lastOneActive;
            return this;
        }

        public Builder error(String error)
        {
            this.error = error; return this;
        }

        public Builder required(boolean value)
        {
            this.required = value; return this;
        }

        public FormCheckBoxesNode build()
        {
            return new FormCheckBoxesNode(this);
        }
    }

    private FormCheckBoxesNode(Builder builder)
    {
        this.key = builder.key;
        this.error = builder.error;
        this.required = builder.required;
        this.lastOneActive = builder.lastOneActive;

        this.data.addAll(builder.values);
        this.active.addAll(builder.active);

        keyLabel = new Label(key + ":");
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        insertChoices();
    }

    private void insertChoices()
    {
        wrapper.setSpacing(cellGap);

        for (T t : data) {
            CheckBox c = new CheckBox(t.toString());
            if (active.contains(t)) { c.setSelected(true); }

            c.setOnAction(e -> {
                // protect the last checkbox if lastOneActive
                if ((map.entrySet().stream()
                        .filter(i -> i.getValue().isSelected())
                        .count() == 0) && (lastOneActive))
                { c.setSelected(true); }

                if (c.isSelected()) { active.add(t); }
                else { active.remove(t); }
            });

            map.put(t, c);
            wrapper.getChildren().add(c);
        }
    }

    public Label getKey() { return keyLabel; }

    public HBox getNode() { return wrapper; }

    public Label getError() { return errorLabel; }

    public String getRegex() { throw new UnsupportedOperationException("not supported!"); }

    public String getValue() { throw new UnsupportedOperationException("not supported!"); }

    public boolean getRequired() { return required; }

    public Set<T> getData() { return active; }

    @Override
    public Type getType() { return Type.CHOICE; }
}
