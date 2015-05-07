package main.view.concrete;

import javafx.scene.control.Label;
import main.config.Config;
import main.localization.Loc;
import main.view.StandardGridPane;

/**
 * Created by HansPetter on 30.04.2015.
 */
public class AboutView extends StandardGridPane
{
    private int rowNum = 0;

    public AboutView()
    {
        initAboutFields();
    }

    private void initAboutFields()
    {

        add(new Label(Config.APP_NAME + " \t " + Loc.c("version")), 0, rowNum++);

        addSpace(5);

        add(new Label(Loc.c("made_by")), 0, rowNum++);
        add(new Label("Alexander Skjolden"), 0, rowNum++);
        add(new Label("Hans Christian Nenseth"), 0, rowNum++);
        add(new Label("Hans Petter Osvold"), 0, rowNum++);

        addSpace(5);

        add(new Label(Loc.c("repository") + " \t " + Loc.c("repository_url")), 0, rowNum++);

    }

    private void addSpace(int space)
    {
        for(int i = 0; i < space; i++)
        {
            rowNum++;
        }
    }

    public StandardGridPane getNode() { return this; }
}

