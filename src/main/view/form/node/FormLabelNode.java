package main.view.form.node;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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

    public static class Builder
    {
        private String key;
        private String value;

        public Builder(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

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

        keyLabel = new Label(key);
        valueLabel =  new Label(value);
    }

    public Label getKey() { return keyLabel; }

    public Label getNode() { return valueLabel; }

    public Label getError() { return new Label(); }

    public String getValue() { return valueLabel.getText(); }

    public String getRegex() { return ""; }

    public Label getData() { return getNode(); }

    @Override
    public Type getType() { return Type.LABEL; }

}
