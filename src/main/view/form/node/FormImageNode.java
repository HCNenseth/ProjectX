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
    private String key;
    private String error;
    private Label keyLabel;
    private Label errorLabel;

    public static class Builder
    {
        private String error = "";
        private String key = "";
        private String buttonText = Loc.c("button_load");
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

        public Builder button(String text)
        {
            this.buttonText = text;
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

        key = builder.key;
        error = builder.error;
        image = builder.image;

        super.setRequired(builder.required);

        keyLabel = new Label(key + ":");

        errorLabel = new Label(error);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        openButton = new Button(builder.buttonText);
        openButton.setOnAction(e -> initFileChooser());

    }

    private void initFileChooser()
    {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(Loc.u("JPG") + " " + Loc.l("files") + " (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(Loc.u("PNG") + " " + Loc.l("files") + " (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        try {
            openImage();
        } catch(IOException ex) {
            error = Loc.c("error_image_format");
        }
    }

    private void openImage() throws IOException {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
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
