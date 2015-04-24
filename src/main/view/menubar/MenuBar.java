package main.view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;


/**
 * Created by alex on 4/22/15.
 */
public class MenuBar extends javafx.scene.control.MenuBar
{
    private Menu fileMenu, statisticsMenu, editMenu,
            themeSubMenu, languageSubMenu, newMenu;
    private MenuItem fileSave, fileSaveAs, fileOpen, fileExit,
            statistics, customer;
    private ToggleGroup themeSubMenuGroup, langaugeSubMenuGroup;
    private RadioMenuItem themeSubMenuItem1, themeSubMenuItem2,
            langaugeSubMenuItem1, langaugeSubMenuItem2, langaugeSubMenuItem3,
            langaugeSubMenuItem4, langaugeSubMenuItem5;

    public MenuBar()
    {

        fileMenu = new Menu(Loc.get("menu_groupFile"));
        editMenu = new Menu(Loc.get("menu_groupEdit"));
        statisticsMenu = new Menu(Loc.get("menu_groupStatistics"));

        newMenu = new Menu(Loc.get("New"));

        fileSave = new MenuItem(Loc.get("menu_fileSave"));
        fileSave.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.SAVE)));

        fileSaveAs = new MenuItem(Loc.get("menu_fileSaveAs"));
        fileSaveAs.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.SAVE_AS)));

        fileOpen = new MenuItem(Loc.get("menu_fileOpen"));
        fileOpen.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.OPEN)));

        fileExit = new MenuItem(Loc.get("menu_fileClose"));
        fileExit.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.CLOSE)));

        statistics = new MenuItem(Loc.get("menu_statistics"));

        customer = new MenuItem(Loc.get("customer"));
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));



        /**
         * Sub menus with Radio menu items.
         * Probably better for scaling to use ENUMS with a loop.
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

        editMenu.getItems().addAll(themeSubMenu, languageSubMenu);

        newMenu.getItems().add(customer);

        statisticsMenu.getItems().addAll(statistics);

        getMenus().addAll(fileMenu, newMenu, editMenu, statisticsMenu);
    }
}
