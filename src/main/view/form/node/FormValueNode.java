package main.view.form.node;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormValueNode extends FormNode
{
    private String key;
    private String value;
    private String error;
    private String regex;
    private Label keyLabel;
    private TextField valueField;
    private Label errorLabel;

    public static class Builder
    {
        private String key;
        private String value = "";
        private String error = "";
        private String regex = "";
        private boolean required = true;

        public Builder(String key)
        {
            this.key = key;
        }

        public Builder value(String value)
        {
            this.value = value; return this;
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

        public FormValueNode build()
        {
            return new FormValueNode(this);
        }
    }

    private FormValueNode(Builder builder)
    {
        this.key = builder.key;
        this.value = builder.value;
        this.error = builder.error;
        this.regex = builder.regex;

        super.setRequired(builder.required);

        keyLabel = new Label(key);
        valueField = new TextField(value);
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    public Label getKey() { return keyLabel; }

    public TextField getValue() { return valueField; }

    public Label getError() { return errorLabel; }

    public String getRegex() { return regex; }

    @Override
    public Type getType() { return Type.VALUE; }

}
