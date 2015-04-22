package main.view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import main.localization.Loc;


/**
 * Created by alex on 4/22/15.
 */
public class MenuBar extends javafx.scene.control.MenuBar
{
    private Menu fileMenu, statisticsMenu, editMenu,
            themeSubMenu, languageSubMenu;
    private MenuItem fileSave, fileSaveAs, fileOpen, fileExit,
            statistics;
    private ToggleGroup themeSubMenuGroup, langaugeSubMenuGroup;
    private RadioMenuItem themeSubMenuItem1, themeSubMenuItem2,
            langaugeSubMenuItem1, langaugeSubMenuItem2, langaugeSubMenuItem3,
            langaugeSubMenuItem4, langaugeSubMenuItem5;

    public MenuBar()
    {
        fileMenu = new Menu(Loc.get("menu_groupFile"));
        editMenu = new Menu(Loc.get("menu_groupEdit"));
        statisticsMenu = new Menu(Loc.get("menu_groupStatistics"));

        fileSave = new MenuItem(Loc.get("menu_fileSave"));
        fileSaveAs = new MenuItem(Loc.get("menu_fileSaveAs"));
        fileOpen = new MenuItem(Loc.get("menu_fileOpen"));
        fileExit = new MenuItem(Loc.get("menu_fileClose"));
        statistics = new MenuItem(Loc.get("menu_statistics"));

        /**
         * Sub menus with Radio menu items.
         * Probly better for scaling to use ENUMS with a loop.
         * But this is just a proof of concept.
         */

        themeSubMenu = new Menu(Loc.get("theme"));
        themeSubMenuGroup = new ToggleGroup();
        themeSubMenuItem1 = new RadioMenuItem(Loc.get("default"));
        themeSubMenuItem1.setSelected(true);
        themeSubMenuItem1.setToggleGroup(themeSubMenuGroup);
        themeSubMenuItem2 = new RadioMenuItem(Loc.get("inverse"));
        themeSubMenuItem2.setToggleGroup(themeSubMenuGroup);
        themeSubMenu.getItems().addAll(themeSubMenuItem1, themeSubMenuItem2);


        languageSubMenu = new Menu(Loc.get("language"));
        langaugeSubMenuGroup = new ToggleGroup();

        langaugeSubMenuItem1 = new RadioMenuItem(Loc.get("langEn"));
        langaugeSubMenuItem1.setSelected(true);
        langaugeSubMenuItem1.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem2 = new RadioMenuItem(Loc.get("langNo"));
        langaugeSubMenuItem2.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem3 = new RadioMenuItem(Loc.get("langGe"));
        langaugeSubMenuItem3.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem4 = new RadioMenuItem(Loc.get("langFr"));
        langaugeSubMenuItem4.setToggleGroup(langaugeSubMenuGroup);

        langaugeSubMenuItem5 = new RadioMenuItem(Loc.get("langIt"));
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
        fileMenu.getItems().addAll(fileOpen, fileSave, fileSaveAs, fileExit);

        statisticsMenu.getItems().addAll(statistics);

        editMenu.getItems().addAll(themeSubMenu, languageSubMenu);

        getMenus().addAll(fileMenu, editMenu, statisticsMenu);
    }
}
