package main.view.form.node;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * FormSlideNode.java
 *
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormSlideNode extends FormNode<Integer>
{
    private String key;
    private int min, max, value;
    private Label keyLabel;
    private Slider valueSlider;

    /**
     * The type Builder.
     */
    public static class Builder
    {
        private String key;
        private int min, max, value;

        /**
         * Instantiates a new Builder.
         *
         * @param key the key
         * @param min the min
         * @param max the max
         */
        public Builder(String key, int min, int max)
        {
            this.key = key;
            this.min = min;
            this.max = max;
            this.value = (max - min) / 2;
        }

        /**
         * Value builder.
         *
         * @param value the value
         * @return the builder
         */
        public Builder value(int value)
        {
            this.value = value;
            return this;
        }

        /**
         * Build form slide node.
         *
         * @return the form slide node
         */
        public FormSlideNode build()
        {
            return new FormSlideNode(this);
        }
    }

    private FormSlideNode(Builder builder)
    {
        this.key = builder.key;
        this.min = builder.min;
        this.max = builder.max;
        this.value = builder.value;

        keyLabel = new Label(key + ":");

        valueSlider = new Slider();
        valueSlider.setMin(min);
        valueSlider.setMax(max);
        valueSlider.setValue(value);
        valueSlider.setShowTickLabels(true);
        valueSlider.setShowTickMarks(true);
        valueSlider.setSnapToTicks(true);
    }

    @Override
    public Label getKey()
    {
        return keyLabel;
    }

    @Override
    public Slider getNode()
    {
        return valueSlider;
    }

    @Override
    public Label getError()
    {
        return new Label("");
    }

    @Override
    public String getValue()
    {
        return Double.toString(valueSlider.getValue());
    }

    @Override
    public Integer getData()
    {
        return (int) valueSlider.getValue();
    }

    @Override
    public Type getType()
    {
        return Type.SLIDER;
    }

}
