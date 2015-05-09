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
    private Button openButton;
    private FileChooser fileChooser;
    private String error;
    private Label errorLabel;

    public static class Builder
    {
        private String error = "";
        private String key = Loc.c("button_open");
        private Image image = null;
        private boolean required = false;

        public Builder(String key)
        {
            this.key = key;
        }

        public Builder required(boolean required)
        {
            this.required = required;
            return this;
        }

        public Builder error(String error)
        {
            this.error = error;
            return this;
        }

        public Builder key(String key)
        {
            this.key = key;
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
        image = builder.image;

        super.setRequired(builder.required);

        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        openButton = new Button(builder.key);
        openButton.setOnAction(e -> loadImage());
    }

    private void loadImage()
    {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(Loc.u("JPG") + " " + Loc.l("files") + " (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(Loc.u("PNG") + " " + Loc.l("files") + " (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        try {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                BufferedImage bufferedImage = ImageIO.read(file);
                image = SwingFXUtils.toFXImage(bufferedImage, null);
            }
        } catch(IOException ex) {
            error = Loc.c("error_image_format");
        }
    }

    @Override
    public Label getKey() {
        return null;
    }

    @Override
    public Label getError() {
        return errorLabel;
    }

    @Override
    public Node getNode() {
        return openButton;
    }

    @Override
    public Type getType() {
        return Type.IMAGE;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public Image getData() {
        return image;
    }
}
