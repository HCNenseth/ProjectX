package main.view.form.node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FormValueNode.java
 *
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormValueNode extends FormNode<String>
{
    private String key;
    private String value;
    private String error;
    private String regex;
    private Label keyLabel;
    private TextField valueField;
    private Label errorLabel;

    /**
     * The type Builder.
     */
    public static class Builder
    {
        private String key;
        private String value = "";
        private String error = "";
        private String regex = "";
        private boolean required = true;

        /**
         * Instantiates a new Builder.
         *
         * @param key the key
         */
        public Builder(String key)
        {
            this.key = key;
        }

        /**
         * Value builder.
         *
         * @param value the value
         * @return the builder
         */
        public Builder value(String value)
        {
            this.value = value;
            return this;
        }

        /**
         * Error builder.
         *
         * @param error the error
         * @return the builder
         */
        public Builder error(String error)
        {
            this.error = error;
            return this;
        }

        /**
         * Regex builder.
         *
         * @param regex the regex
         * @return the builder
         */
        public Builder regex(String regex)
        {
            this.regex = regex;
            return this;
        }

        /**
         * Required builder.
         *
         * @param value the value
         * @return the builder
         */
        public Builder required(boolean value)
        {
            this.required = value;
            return this;
        }

        /**
         * Build form value node.
         *
         * @return the form value node
         */
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

        keyLabel = new Label(key + ":");
        valueField = new TextField(value);
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    /**
     * Gets regex.
     *
     * @return the regex
     */
    public String getRegex()
    {
        return regex;
    }

    /**
     * Connect to button.
     *
     * @param button the button
     */
    public void connectToButton(Button button)
    {
        valueField.setOnAction(e -> button.fire());
    }

    @Override
    public Label getKey()
    {
        return keyLabel;
    }

    @Override
    public TextField getNode()
    {
        return valueField;
    }

    @Override
    public Label getError()
    {
        return errorLabel;
    }

    @Override
    public String getValue()
    {
        return valueField.getText();
    }

    @Override
    public String getData()
    {
        return getValue();
    }

    @Override
    public Type getType()
    {
        return Type.VALUE;
    }
}
