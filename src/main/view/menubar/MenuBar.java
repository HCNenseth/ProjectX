package main.view.menubar;

import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import main.controller.Mediator;
import main.controller.Payload;
import main.controller.Signal;
import main.controller.StatisticsController;
import main.localization.Loc;
import main.preference.Pref;
import main.view.Resources;

/**
 * Created by alex on 4/22/15.
 */
public class MenuBar extends javafx.scene.control.MenuBar
{
    private Menu fileMenu, statisticsMenu, newMenu, aboutMenu;

    private MenuItem fileSave, closeProject, fileExit, fileRestart,
            customerStatistics, insuranceStatistics, claimStatistics,
            customer, search, report, aboutUs;

    public MenuBar()
    {
        fileMenu = new Menu(Loc.c("menu_group_file"));
        fileMenu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + F"));

        statisticsMenu = new Menu(Loc.c("menu_group_statistics"));

        aboutMenu = new Menu(Loc.c("menu_group_about"));
        aboutMenu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + B"));

        newMenu = new Menu(Loc.c("new"));

        aboutUs = new MenuItem(Loc.c("menu_about"));
        aboutUs.setAccelerator(KeyCombination.keyCombination("SHORTCUT + A"));
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
        fileExit.setAccelerator(KeyCombination.keyCombination("SHORTCUT + X"));
        fileExit.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.CLOSE)));

        customerStatistics = new MenuItem(Loc.c("menu_customer_statistics"));
        customerStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.PERSON)));

        insuranceStatistics = new MenuItem(Loc.c("menu_insurance_statistics"));
        insuranceStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.INSURANCE)));

        claimStatistics = new MenuItem(Loc.c("menu_claim_statistics"));
        claimStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.CLAIM)));

        customer = new MenuItem(Loc.c("customer"));
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));

        search = new MenuItem(Loc.c("search"));
        search.setOnAction(e -> Mediator.inst.router(Signal.SEARCH, null));

        fileMenu.getItems().addAll(
                closeProject,
                new SeparatorMenuItem(),
                fileSave,
                new SeparatorMenuItem(),
                fileRestart,
                fileExit);

        newMenu.getItems().addAll(search, customer);
        statisticsMenu.getItems().addAll(customerStatistics,
                insuranceStatistics, claimStatistics);

        aboutMenu.getItems().addAll(report, aboutUs);

        getMenus().addAll(fileMenu, newMenu, statisticsMenu, aboutMenu);
    }
}
