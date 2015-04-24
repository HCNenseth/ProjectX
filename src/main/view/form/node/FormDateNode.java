package main.view.form.node;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormDateNode extends FormNode<LocalDate>
{
    private String key;
    private LocalDate value;
    private Label keyLabel;
    private DatePicker datePicker;

    private final String pattern = "yyyy-MM-dd";

    public static class Builder
    {
        private String key;
        private LocalDate value;

        public Builder(String key, LocalDate value)
        {
            this.key = key;
            this.value = value;
        }

        public FormDateNode build()
        {
            return new FormDateNode(this);
        }
    }

    private FormDateNode(Builder builder)
    {
        this.key = builder.key;
        this.value = builder.value;

        keyLabel = new Label(key);
        // TODO insert date here
        datePicker =  new DatePicker(value);
    }

    public Label getKey() { return keyLabel; }

    public DatePicker getNode() { return datePicker; }

    public Label getError() { return new Label(); }

    public String getValue() { return datePicker.getPromptText(); }

    public String getRegex() { return ""; }

    public LocalDate getData() { return getNode().getValue(); }

    @Override
    public Type getType() { return Type.DATEPICKER; }

}
