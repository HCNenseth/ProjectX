package main.view.form.node;

import javafx.scene.control.Label;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormLabelNode extends FormNode<Label>
{
    private String key;
    private String value;
    private Label keyLabel;
    private Label valueLabel;

    /**
     * The type Builder.
     */
    public static class Builder
    {
        private String key;
        private String value;

        /**
         * Instantiates a new Builder.
         *
         * @param key the key
         * @param value the value
         */
        public Builder(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        /**
         * Build form label node.
         *
         * @return the form label node
         */
        public FormLabelNode build()
        {
            return new FormLabelNode(this);
        }
    }

    private FormLabelNode(Builder builder)
    {
        this.key = builder.key;
        this.value = builder.value;
        super.setRequired(false);

        keyLabel = new Label(key + ":");
        valueLabel = new Label(value);
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
    public Label getNode()
    {
        return valueLabel;
    }

    @Override
    public Label getError()
    {
        return new Label();
    }

    @Override
    public String getValue()
    {
        return valueLabel.getText();
    }

    @Override
    public Label getData()
    {
        return getNode();
    }

    @Override
    public Type getType()
    {
        return Type.LABEL;
    }

}
