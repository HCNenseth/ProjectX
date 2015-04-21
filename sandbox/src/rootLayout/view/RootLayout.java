package it.osvold.Contacts.view;

import it.osvold.Contacts.controller.RootLayoutController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import it.osvold.Contacts.localization.*;


/**
 * Created by HansPetter on 21.04.2015.
 * This class will represent the view of the top most navigational bar.
 * Will be implemented with the localization class.
 */
public class RootLayout {

    /**
     * Warning: here lives bad code. It is possible that it bites.
     */

    private BorderPane borderPane;
    private double PREF_HEIGHT = 500;
    private double PREF_WIDTH = 500;
    private MenuBar menuBar;
    private Menu fileMenu, statisticsMenu, editMenu, themeSubMenu, languageSubMenu;
    private MenuItem fileSave, fileSaveAs, fileOpen, fileExit, statistics;
    private ToggleGroup themeSubMenuGroup, langaugeSubMenuGroup;
    private RadioMenuItem themeSubMenuItem1, themeSubMenuItem2, langaugeSubMenuItem1, langaugeSubMenuItem2, langaugeSubMenuItem3, langaugeSubMenuItem4, langaugeSubMenuItem5;
    private Scene scene;
    private RootLayoutController controller;

    /**
     * Initializes the top most navigational bar.
     */
    public RootLayout(RootLayoutController controller)
    {

        this.controller = controller;
        borderPane = new BorderPane();
        borderPane.prefHeight(PREF_HEIGHT);
        borderPane.prefWidth(PREF_WIDTH);

        menuBar = new MenuBar();

        fileMenu = new Menu(Loc.get("menu_groupFile"));
        editMenu = new Menu(Loc.get("menu_groupEdit"));
        statisticsMenu = new Menu(Loc.get("menu_groupStatistics"));

        fileSave = new MenuItem(Loc.get("menu_fileSave"));
        fileSaveAs = new MenuItem(Loc.get("menu_fileSaveAs"));
        fileOpen = new MenuItem(Loc.get("menu_fileOpen"));
        fileExit = new MenuItem(Loc.get("menu_fileClose"));

        fileOpen.setOnAction(i -> controller.handleFileOpen());
        fileSave.setOnAction(i -> controller.handleFileSave());
        fileSaveAs.setOnAction(i -> controller.handleFileSaveAs());
        fileExit.setOnAction(i -> System.exit(0));

        statistics = new MenuItem("Statistics");

        statistics.setOnAction(i -> controller.handleStatistics());

        /**
         * Sub menus with Radio menu items.
         * Probly better for scaling to use ENUMS with a loop.
         * But this is just a proof of concept.
         */

        themeSubMenu = new Menu("Theme");
        themeSubMenuGroup = new ToggleGroup();
        themeSubMenuItem1 = new RadioMenuItem("Default");
        themeSubMenuItem1.setSelected(true);
        themeSubMenuItem1.setToggleGroup(themeSubMenuGroup);
        themeSubMenuItem2 = new RadioMenuItem("Inverse");
        themeSubMenuItem2.setToggleGroup(themeSubMenuGroup);
        themeSubMenu.getItems().addAll(themeSubMenuItem1, themeSubMenuItem2);

        themeSubMenuItem1.setOnAction
                (i -> controller.handleDefaultTheme());

        themeSubMenuItem2.setOnAction
                (i -> controller.handleInverseTheme());

        languageSubMenu = new Menu("Language");
        langaugeSubMenuGroup = new ToggleGroup();

        langaugeSubMenuItem1 = new RadioMenuItem(Loc.get("langEn"));
        langaugeSubMenuItem1.setSelected(true);
        langaugeSubMenuItem1.setOnAction
                (i -> controller.handleLanguage(1));
        langaugeSubMenuItem1.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem2 = new RadioMenuItem(Loc.get("langNo"));
        langaugeSubMenuItem2.setOnAction
                (i -> controller.handleLanguage(2));
        langaugeSubMenuItem2.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem3 = new RadioMenuItem(Loc.get("langGe"));
        langaugeSubMenuItem3.setOnAction
                (i -> controller.handleLanguage(3));
        langaugeSubMenuItem3.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem4 = new RadioMenuItem(Loc.get("langFr"));
        langaugeSubMenuItem4.setOnAction
                (i -> controller.handleLanguage(4));
        langaugeSubMenuItem4.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem5 = new RadioMenuItem(Loc.get("langIt"));
        langaugeSubMenuItem5.setOnAction
                (i -> controller.handleLanguage(5));
        langaugeSubMenuItem5.setToggleGroup(langaugeSubMenuGroup);

        languageSubMenu.getItems()
                .addAll(
                        langaugeSubMenuItem1,
                        langaugeSubMenuItem2,
                        langaugeSubMenuItem3,
                        langaugeSubMenuItem4,
                        langaugeSubMenuItem5
                );

        /**
         * Putting the parts together.
         */

        fileMenu.getItems()
                .addAll(
                        fileOpen,
                        fileSave,
                        fileSaveAs,
                        fileExit
                );

        statisticsMenu.getItems()
                .addAll(statistics);

        editMenu.getItems()
                .addAll(
                        themeSubMenu,
                        languageSubMenu
                );

        menuBar.getMenus()
                .addAll(
                        fileMenu,
                        editMenu,
                        statisticsMenu
                );


        /**
         * Setting the scene.
         */

        scene = new Scene(new VBox(), PREF_HEIGHT, PREF_HEIGHT);
        scene.setFill(Color.OLDLACE);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);

    }

    /**
     * Not sure if the root layout should return a full scene or just an VBOX.
     * @return
     */

    public Scene getRootScene()
    {
        return scene;
    }

}
