package it.osvold.Contacts.controller;

import it.osvold.Contacts.Main;
import it.osvold.Contacts.localization.Loc;
import it.osvold.Contacts.view.RootLayout;
import javafx.scene.Scene;

/**
 * Created by HansPetter on 21.04.2015.
 */
public class RootLayoutController {

    private RootLayout rootLayout;
    private Main main;

    public RootLayoutController()
    {


        // Reference to the RootLayout VIEW.
        rootLayout = new RootLayout(this);
    }

    /**
     * Saves data to the last active file.
     */
    public void handleFileSave()
    {
        System.out.println("handled file save");
    }

    /**
     * Opens a data file.
     */
    public void handleFileOpen()
    {
        System.out.println("handled file save");
    }

    /**
     * Saves data to a chosen file.
     */
    public void handleFileSaveAs()
    {
        System.out.println("handled file save as");
    }

    /**
     * Sets the programs css to a inverse css file.
     */
    public void handleInverseTheme()
    {
        System.out.println("Changing template to inverse theme.");
    }

    /**
     * Sets the programs css to a common css file.
     */
    public void handleDefaultTheme()
    {
        System.out.println("Changing template to default theme.");
    }

    /**
     * Handling of the statistics.
     */
    public void handleStatistics()
    {
        System.out.println("Handling statistics");
    }

    /**
     * Sets the apps language. 1 = english, 2 = norwegian, 3 = german, 4 = french, 5 = italian.
     * Probly better to use ENUMS here, but this is just a proof of concept.
     * @param language
     */
    public void handleLanguage(int language)
    {
        if(language > 0 && language < 6)
        {
            switch(language)
            {
                case 1:
                    Loc.setActiveLang("english");
                    System.out.println("Language set to English.");
                    break;
                case 2:
                    Loc.setActiveLang("norwegian");
                    System.out.println("Language set to Norwegian.");
                    break;
                case 3:
                    Loc.setActiveLang("german");
                    System.out.println("Language set to German.");
                    break;
                case 4:
                    Loc.setActiveLang("french");
                    System.out.println("Language set to French.");
                    break;
                case 5:
                    Loc.setActiveLang("italian");
                    System.out.println("Language set to Italian.");
                    break;
            }

            main.updateWindow();
        }
    }

    /**
     *
     * @return the RootLayout scene which
     * includes the top most navigational bar.
     */
    public Scene getRootScene()
    {
        return rootLayout.getRootScene();
    }

}
