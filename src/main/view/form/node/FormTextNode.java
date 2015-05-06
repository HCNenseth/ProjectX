package main.view.form.node;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class FormTextNode extends FormNode<TextArea> {

    private String key;
    private String value;
    private String error;
    private String regex;
    private Label keyLabel;
    private TextArea textArea;
    private Label errorLabel;

    public static class Builder
    {
        private String key;
        private String value = "";
        private String error = "";
        private String regex = "";
        private boolean required = false;

        public Builder(String key)
        {
            this.key = key;
        }

        public Builder value(String value)
        {
            this.value = value;
            return this;
        }

        public Builder error(String error)
        {
            this.error = error;
            return this;
        }

        public Builder regex(String regex)
        {
            this.regex = regex;
            return this;
        }

        public Builder required(boolean required)
        {
            this.required = required;
            return this;
        }

        public FormTextNode build()
        {
            return new FormTextNode(this);
        }

    }

    public FormTextNode(Builder builder)
    {
        key = builder.key;
        value = builder.value;
        error = builder.error;
        regex = builder.regex;

        super.setRequired(builder.required);

        keyLabel = new Label(key + ":");
        textArea = new TextArea(value);
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    @Override
    public Label getKey() {
        return keyLabel;
    }

    @Override
    public Label getError() {
        return errorLabel;
    }

    @Override
    public Node getNode() {
        return textArea;
    }

    @Override
    public Type getType() {
        return Type.TEXTAREA;
    }

    @Override
    public String getValue() {
        return textArea.getText();
    }

    @Override
    public TextArea getData() {
        return textArea;
    }



}
