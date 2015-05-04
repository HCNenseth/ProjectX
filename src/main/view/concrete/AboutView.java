package main.view.concrete;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import main.config.Config;
import main.localization.Loc;
import main.view.StandardGridPane;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by HansPetter on 30.04.2015.
 */
public class AboutView {

    private StandardGridPane gp;
    private int rowNum = 0;
    private Hyperlink repository;

    public AboutView()
    {
        gp = new StandardGridPane();

        initAboutFields();
    }

    private void initAboutFields()
    {

        gp.add(new Label(Config.APP_NAME + " \t " + Loc.get("version")), 0, rowNum++);

        addSpace(5);

        gp.add(new Label(Loc.get("made_by")), 0, rowNum++);
        gp.add(new Label("Alexander Skjolden"), 0, rowNum++);
        gp.add(new Label("Hans Christian Nenseth"), 0, rowNum++);
        gp.add(new Label("Hans Petter Osvold"), 0, rowNum++);

        addSpace(5);

        repository = new Hyperlink();
        repository.setText(Loc.get("repository_url"));
        repository.setOnAction(e -> urlOpener(Loc.get("repository_url")));

        gp.add(repository, 0, rowNum++);

    }

    private void urlOpener(String url)
    {
        try {
            java.awt.Desktop.getDesktop().browse(new URI(url));
        }
            catch(URISyntaxException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void addSpace(int space)
    {
        for(int i = 0; i < space; i++)
        {
            rowNum++;
        }
    }

    public StandardGridPane getNode()
    {
        return gp;
    }
}

