package main.view.concrete;

import javafx.scene.control.Label;
import main.config.Config;
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

        gp.add(new Label(Config.APP_NAME), 0, rowNum++);

    }

    public StandardGridPane getNode()
    {
        return gp;
    }
}

