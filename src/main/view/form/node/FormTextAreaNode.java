package main.view.form.node;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

/**
 * FormTextAreaNode.java
 *
 * The type Form text area node.
 */
public class FormTextAreaNode extends FormNode<TextArea>
{
    private String key;
    private String value;
    private String error;
    private String regex;
    private Label keyLabel;
    private TextArea textArea;
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
        private boolean required = false;

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
         * @param required the required
         * @return the builder
         */
        public Builder required(boolean required)
        {
            this.required = required;
            return this;
        }

        /**
         * Build form text area node.
         *
         * @return the form text area node
         */
        public FormTextAreaNode build()
        {
            return new FormTextAreaNode(this);
        }

    }

    /**
     * Instantiates a new Form text area node.
     *
     * @param builder the builder
     */
    private FormTextAreaNode(Builder builder)
    {
        key = builder.key;
        value = builder.value;
        error = builder.error;
        regex = builder.regex;

        super.setRequired(builder.required);

        keyLabel = new Label(key + ":");
        textArea = new TextArea(value);
        textArea.setPrefRowCount(3);

        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    @Override
    public Label getKey()
    {
        return keyLabel;
    }

    @Override
    public Label getError()
    {
        return errorLabel;
    }

    @Override
    public Node getNode()
    {
        return textArea;
    }

    @Override
    public Type getType()
    {
        return Type.TEXTAREA;
    }

    @Override
    public String getValue()
    {
        return textArea.getText();
    }

    @Override
    public TextArea getData()
    {
        return textArea;
    }


}
