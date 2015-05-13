package main.view.concrete;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import main.config.Config;
import main.localization.Loc;
import main.view.Resources;
import main.view.StandardGridPane;


/**
 * AboutView.java
 */
public class AboutView extends StandardGridPane
{
    private int rowNum = 0;
    private final int spaceCount = 5;
    private static final int columnCount = 1;

    public AboutView()
    {
        super(columnCount);
        initAboutFields();
    }

    private void initAboutFields()
    {
        Label title = new Label(String.format("%s - %s %s",
                Config.APP_NAME, Loc.l("version"), Config.VERSION));
        title.setFont(new Font(26));

        add(title, 0, rowNum++);

        rowNum += spaceCount;

        add(new Label(Loc.c("made_by")), 0, rowNum++);
        add(new Label("Alexander Skjolden"), 0, rowNum++);
        add(new Label("Hans Christian Nenseth"), 0, rowNum++);
        add(new Label("Hans Petter Osvold"), 0, rowNum++);

        rowNum += spaceCount;

        Hyperlink githubLink = new Hyperlink(Loc.l("repository_url"));
        githubLink.setOnAction(e ->
                Resources.inst.getHostServices().showDocument(Loc.l("repository_url")));

        add(githubLink, 0, rowNum++);

    }

    public StandardGridPane getNode() { return this; }
}

