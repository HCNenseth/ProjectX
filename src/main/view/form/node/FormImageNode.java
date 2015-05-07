package main.view.form.node;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import main.localization.Loc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FormImageNode extends FormNode<Image> {

    private Image image;
    private Button loadButton;
    private FileChooser fileChooser;
    private String key;
    private String filename;
    private String error;
    private Label keyLabel;
    private Label errorLabel;

    public static class Builder
    {
        private String error = "";
        private boolean required = false;
        private String key = "";
        private String loadText = Loc.c("button_load");
        private Image image = null;

        public Builder required(boolean required)
        {
            this.required = required;
            return this;
        }

        public Builder key(String key)
        {
            this.key = key;
            return this;
        }

        public Builder error(String error)
        {
            this.error = error;
            return this;
        }

        public Builder loadText(String loadText)
        {
            this.loadText = loadText;
            return this;
        }

        public Builder image(Image image)
        {
            this.image = image;
            return this;
        }

        public FormImageNode build()
        {
            return new FormImageNode(this);
        }
    }

    private FormImageNode(Builder builder)
    {
        error = builder.error;
        key = builder.key;
        image = builder.image;
        setRequired(builder.required);
        keyLabel = new Label(builder.key + ":");
        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        loadButton = new Button(builder.loadText);
        loadButton.setOnAction(e -> initFileChooser());

    }

    // TODO should this be moved ?
    private void initFileChooser()
    {
        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(Loc.u("JPG") + " " + Loc.l("files") + " (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(Loc.u("PNG") + " " + Loc.l("files") + " (*.png)", "*.PNG");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            // TODO filename must be unique.
            filename = fileChooser.getInitialFileName();
        } catch(IOException ex)
        {
            error = Loc.c("image_io_exception");
        }
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
        return loadButton;
    }

    @Override
    public Type getType() {
        return Type.IMAGE;
    }

    @Override
    public String getValue() {
        return filename;
    }

    @Override
    public Image getData() {
        return image;
    }
}
