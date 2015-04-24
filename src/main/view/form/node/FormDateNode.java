package main.view.form.node;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormDateNode extends FormNode<DatePicker>
{
    private String key;
    private String value;
    private Label keyLabel;
    private DatePicker datePicker;

    private int standardYear = 1970;
    private int standardMonth = 1;
    private int standardDay = 1;

    private final String pattern = "yyyy-MM-dd";

    public static class Builder
    {
        private String key;
        private String value;

        public Builder(String key, String value)
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
        datePicker =  new DatePicker(LocalDate.of(standardYear,
                standardMonth, standardDay));
    }

    public Label getKey() { return keyLabel; }

    public DatePicker getNode() { return datePicker; }

    public Label getError() { return new Label(); }

    public String getValue() { return datePicker.getPromptText(); }

    public String getRegex() { return ""; }

    public DatePicker getData() { return getNode(); }

    @Override
    public Type getType() { return Type.LABEL; }

}
