package main.view.menubar;

import javafx.scene.control.*;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.localization.Loc;
import main.preference.Pref;
import main.view.Resources;

/**
 * Created by alex on 4/22/15.
 */
public class MenuBar extends javafx.scene.control.MenuBar
{
    private Menu fileMenu, statisticsMenu, editMenu,
            themeSubMenu, languageSubMenu, newMenu, aboutMenu;
    private MenuItem fileSave, fileSaveAs, fileOpen, fileExit,
            statistics, customer, search, aboutUs;
    private ToggleGroup themeSubMenuGroup, languageSubMenuGroup;
    private RadioMenuItem themeSubMenuItem1, themeSubMenuItem2;

    public MenuBar()
    {

        fileMenu = new Menu(Loc.get("menu_group_file"));
        editMenu = new Menu(Loc.get("menu_group_edit"));
        statisticsMenu = new Menu(Loc.get("menu_group_statistics"));
        aboutMenu = new Menu(Loc.get("menu_group_about"));
        newMenu = new Menu(Loc.get("New"));

        aboutUs = new MenuItem(Loc.get("menu_about"));
        aboutUs.setOnAction(e -> Mediator.inst.router(Signal.ABOUT,
                new Payload()));

        fileSave = new MenuItem(Loc.get("menu_file_save"));
        fileSave.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.SAVE)));

        fileSaveAs = new MenuItem(Loc.get("menu_file_save_as"));
        fileSaveAs.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.SAVE_AS)));

        fileOpen = new MenuItem(Loc.get("menu_file_open"));
        fileOpen.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.OPEN)));

        fileExit = new MenuItem(Loc.get("menu_file_close"));
        fileExit.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.CLOSE)));

        statistics = new MenuItem(Loc.get("menu_statistics"));

        customer = new MenuItem(Loc.get("customer"));
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));

        search = new MenuItem(Loc.get("search"));
        search.setOnAction(e -> Mediator.inst.router(Signal.SEARCH, null));


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
        languageSubMenuGroup = new ToggleGroup();

        for (String s : Loc.getLanguages()) {
            RadioMenuItem tmp = new RadioMenuItem(Loc.get(s));
            tmp.setToggleGroup(languageSubMenuGroup);
            tmp.setOnAction(e -> {
                Loc.setActiveLang(s);
                Resources.inst.getInfoBar().setAndShow(
                        Loc.get("please_restart_for_changes_to_take_effect"));
                Pref.inst.put("language", s);
            });
            languageSubMenu.getItems().add(tmp);
        }

       /**
         * Putting the parts together.
         */
        fileMenu.getItems().addAll(fileOpen, fileSave, fileSaveAs, fileExit);
        editMenu.getItems().addAll(themeSubMenu, languageSubMenu);
        newMenu.getItems().addAll(search, customer);
        statisticsMenu.getItems().addAll(statistics);
        aboutMenu.getItems().add(aboutUs);

        getMenus().addAll(fileMenu, newMenu, editMenu, statisticsMenu, aboutMenu);
    }
}
