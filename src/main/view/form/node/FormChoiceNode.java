package main.view.form.node;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 * @param <T>  the type parameter
 */
public class FormChoiceNode<T> extends FormNode<T>
{
    private String key;
    private String error;
    private String regex;
    private boolean required;
    private Label keyLabel;
    private ChoiceBox<T> values;
    private Label errorLabel;

    /**
     * The type Builder.
     * @param <T>  the type parameter
     */
    public static class Builder<T>
    {
        private String key;
        private ChoiceBox<T> values;
        private String error = "";
        private String regex = "";
        private boolean required = true;

        /**
         * Instantiates a new Builder.
         *
         * @param key the key
         * @param data the data
         */
        public Builder(String key, List<T> data)
        {
            this.key = key;
            values = new ChoiceBox<>();
            values.getItems().setAll(data);
        }

        /**
         * Active builder.
         *
         * @param value the value
         * @return the builder
         */
        public Builder active(T value)
        {
            values.setValue(value);
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
         * Build form choice node.
         *
         * @return the form choice node
         */
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

        keyLabel = new Label(key + ":");
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    public Label getKey()
    {
        return keyLabel;
    }

    public ChoiceBox<T> getNode()
    {
        return values;
    }

    public Label getError()
    {
        return errorLabel;
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

    public String getValue()
    {
        return values.getValue().toString();
    }

    public boolean getRequired()
    {
        return required;
    }

    public T getData()
    {
        return values.getValue();
    }

    @Override
    public Type getType()
    {
        return Type.CHOICE;
    }
}
