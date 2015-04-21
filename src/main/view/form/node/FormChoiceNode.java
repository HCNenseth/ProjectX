package main.view.form.node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormChoiceNode<T> extends FormNode
{
    private String key;
    private String error;
    private String regex;
    private boolean required;
    private Label keyLabel;
    private ChoiceBox<T> values;
    private Label errorLabel;

    public static class Builder<T>
    {
        private String key;
        private ChoiceBox<T> values;
        private String error = "";
        private String regex = "";
        private boolean required = true;

        public Builder(String key, List<T> data)
        {
            this.key = key;
            values = new ChoiceBox<>();
            values.getItems().setAll(data);
        }

        public Builder active(T value)
        {
            values.setValue(value); return this;
        }

        public Builder error(String error)
        {
            this.error = error; return this;
        }

        public Builder regex(String regex)
        {
            this.regex = regex; return this;
        }

        public Builder required(boolean value)
        {
            this.required = value; return this;
        }

        public FormChoiceNode build()
        {
            return new FormChoiceNode(this);
        }
    }

    private FormChoiceNode(Builder builder)
    {
        this.key = builder.key;
        this.values = builder.values;
        this.error = builder.error;
        this.regex = builder.regex;
        this.required = builder.required;

        keyLabel = new Label(key);
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    public Label getKey() { return keyLabel; }

    public ChoiceBox<T> getValue() { return values; }

    public Label getError() { return errorLabel; }

    public String getRegex() { return regex; }

    public boolean getRequired() { return required; }

    @Override
    public Type getType() { return Type.CHOICE; }
}
