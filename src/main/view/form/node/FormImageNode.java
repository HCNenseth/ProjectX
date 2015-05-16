package main.view.form.node;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import main.localization.Loc;

import java.io.File;

/**
 * FormImageNode.java
 *
 * The type Form image node.
 */
public class FormImageNode extends FormNode<File>
{

    private File image;
    private Button openButton;
    private FileChooser fileChooser;
    private String error;
    private Label key;
    private Label placeHolder;
    private Label errorLabel;
    private HBox wrapper;

    /**
     * The type Builder.
     */
    public static class Builder
    {
        private String key;
        private String error = "";
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
         * Build form image node.
         *
         * @return the form image node
         */
        public FormImageNode build()
        {
            return new FormImageNode(this);
        }
    }

    private FormImageNode(Builder builder)
    {
        super.setRequired(builder.required);

        error = builder.error;

        wrapper = new HBox();
        wrapper.setSpacing(5);

        key = new Label(builder.key);
        placeHolder = new Label("");

        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        openButton = new Button(Loc.c("add_image"));
        openButton.setOnAction(e -> loadImage());
    }

    private void loadImage()
    {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(
                Loc.u("jpg_files"), "*.jpg", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(
                Loc.u("png_files"), "*.png", "*.PNG");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            placeHolder.setText(file.getName());
            image = file;
        }
    }

    @Override
    public Label getKey()
    {
        return key;
    }

    @Override
    public Label getError()
    {
        return errorLabel;
    }

    @Override
    public Node getNode()
    {
        wrapper.getChildren().addAll(openButton, placeHolder);
        return wrapper;
    }

    @Override
    public Type getType()
    {
        return Type.IMAGE;
    }

    @Override
    public String getValue()
    {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    public File getData()
    {
        return image;
    }
}
