package main.view.concrete;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import main.config.Config;
import main.localization.Loc;
import main.view.StandardGridPane;

/**
 * Created by HansPetter on 30.04.2015.
 */
public class AboutView {

    private StandardGridPane gp;
    private int rowNum = 0;

    public AboutView()
    {
        gp = new StandardGridPane();

        initAboutFields();
    }

    private void initAboutFields()
    {

        gp.add(new Label(Config.APP_NAME + " \t " + Loc.c("version")), 0, rowNum++);

        addSpace(5);

        gp.add(new Label(Loc.c("made_by")), 0, rowNum++);
        gp.add(new Label("Alexander Skjolden"), 0, rowNum++);
        gp.add(new Label("Hans Christian Nenseth"), 0, rowNum++);
        gp.add(new Label("Hans Petter Osvold"), 0, rowNum++);

        addSpace(5);

        gp.add(new Label(Loc.c("repository") + " \t " + Loc.c("repository_url")), 0, rowNum++);

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

