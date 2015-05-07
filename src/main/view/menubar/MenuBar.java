package main.view.menubar;

import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
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

    private MenuItem fileSave, closeProject, fileExit, fileRestart,
            customerStatistics, insuranceStatistics, claimStatistics,
            customer, search, report, aboutUs;

    private ToggleGroup themeSubMenuGroup, languageSubMenuGroup;

    private RadioMenuItem themeSubMenuItem1, themeSubMenuItem2;

    public MenuBar()
    {
        fileMenu = new Menu(Loc.c("menu_group_file"));
        fileMenu.setAccelerator(KeyCombination.valueOf("SHORTCUT + F"));
        editMenu = new Menu(Loc.c("menu_group_edit"));
        statisticsMenu = new Menu(Loc.c("menu_group_statistics"));
        aboutMenu = new Menu(Loc.c("menu_group_about"));
        aboutMenu.setAccelerator(KeyCombination.valueOf("SHORTCUT + A"));
        newMenu = new Menu(Loc.c("new"));

        aboutUs = new MenuItem(Loc.c("menu_about"));
        aboutUs.setOnAction(e -> Mediator.inst.router(Signal.ABOUT, null));

        report = new MenuItem(Loc.c("menu_report"));
        report.setOnAction(e -> Mediator.inst.router(Signal.REPORT, null));

        fileSave = new MenuItem(Loc.c("menu_file_save"));
        fileSave.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.SAVE)));

        closeProject = new MenuItem(Loc.c("menu_close_project"));
        closeProject.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_PROJECT)));

        fileRestart = new MenuItem(Loc.c("restart"));
        fileRestart.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.RESTART)));

        fileExit = new MenuItem(Loc.c("menu_file_exit"));
        fileExit.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.CLOSE)));

        customerStatistics = new MenuItem(Loc.c("menu_customer_statistics"));
        insuranceStatistics = new MenuItem(Loc.c("menu_insurance_statistics"));
        claimStatistics = new MenuItem(Loc.c("menu_claim_statistics"));

        customer = new MenuItem(Loc.c("customer"));
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));

        search = new MenuItem(Loc.c("search"));
        search.setOnAction(e -> Mediator.inst.router(Signal.SEARCH, null));


        /**
         * Sub menus with Radio menu items.
         * Probably better for scaling to use ENUMS with a loop.
         * But this is just a proof of concept.
         */



        themeSubMenu = new Menu(Loc.c("theme"));
        themeSubMenuGroup = new ToggleGroup();
        themeSubMenuItem1 = new RadioMenuItem(Loc.c("default"));
        themeSubMenuItem1.setSelected(true);
        themeSubMenuItem1.setToggleGroup(themeSubMenuGroup);
        themeSubMenuItem2 = new RadioMenuItem(Loc.c("inverse"));
        themeSubMenuItem2.setToggleGroup(themeSubMenuGroup);
        themeSubMenu.getItems().addAll(themeSubMenuItem1, themeSubMenuItem2);

        languageSubMenu = new Menu(Loc.c("language"));
        languageSubMenuGroup = new ToggleGroup();

        for (String s : Loc.getLanguages()) {
            RadioMenuItem tmp = new RadioMenuItem(Loc.c(s));
            tmp.setToggleGroup(languageSubMenuGroup);
            tmp.setOnAction(e -> {
                Loc.setActiveLang(s);
                /*
                Resources.inst.getInfoBar().setAndShow(
                        Loc.c("please_restart_for_changes_to_take_effect"));
                */
                Pref.inst.put("language", s);
                // TODO reevaluate this decision.
                Resources.inst.getSceneSwitch().setMainWindow();
                Resources.inst.getOtp().closeAllTabs();
            });
            languageSubMenu.getItems().add(tmp);
        }

       /**
         * Putting the parts together.
         */
        fileMenu.getItems().addAll(
                closeProject,
                new SeparatorMenuItem(),
                fileSave,
                new SeparatorMenuItem(),
                fileRestart,
                fileExit);
        //editMenu.getItems().addAll(themeSubMenu, languageSubMenu);
        newMenu.getItems().addAll(search, customer);
        statisticsMenu.getItems().addAll(customerStatistics,
                insuranceStatistics, claimStatistics);

        aboutMenu.getItems().addAll(report, aboutUs);

        getMenus().addAll(fileMenu, newMenu /*, editMenu */, statisticsMenu, aboutMenu);
    }
}
