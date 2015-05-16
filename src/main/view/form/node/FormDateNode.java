package main.view.form.node;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

/**
 * FormDateNode.java
 *
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormDateNode extends FormNode<LocalDate>
{
    private String key;
    private LocalDate value;
    private Label keyLabel;
    private DatePicker datePicker;

    /**
     * The type Builder.
     */
    public static class Builder
    {
        private String key;
        private LocalDate value;
        private boolean required;

        /**
         * Instantiates a new Builder.
         *
         * @param key the key
         * @param value the value
         */
        public Builder(String key, LocalDate value)
        {
            this.key = key;
            this.value = value;
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
         * Build form date node.
         *
         * @return the form date node
         */
        public FormDateNode build()
        {
            return new FormDateNode(this);
        }
    }

    private FormDateNode(Builder builder)
    {
        this.key = builder.key;
        this.value = builder.value;
        super.setRequired(builder.required);

        keyLabel = new Label(key + ":");
        datePicker = new DatePicker(value);
    }

    /**
     * Gets regex.
     *
     * @return the regex
     */
    public String getRegex()
    {
        return "";
    }

    @Override
    public Label getKey()
    {
        return keyLabel;
    }

    @Override
    public DatePicker getNode()
    {
        return datePicker;
    }

    @Override
    public Label getError()
    {
        return new Label();
    }

    @Override
    public String getValue()
    {
        return datePicker.getPromptText();
    }

    @Override
    public LocalDate getData()
    {
        return getNode().getValue();
    }

    @Override
    public Type getType()
    {
        return Type.DATEPICKER;
    }

}
