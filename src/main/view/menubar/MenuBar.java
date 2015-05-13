package main.view.menubar;

import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import main.controller.*;
import main.localization.Loc;

/**
 * Created by alex on 4/22/15.
 */
public class MenuBar extends javafx.scene.control.MenuBar
{
    private Menu fileMenu, statisticsMenu, reportMenu, newMenu, aboutMenu;

    private MenuItem fileSave, closeProject, fileExit, fileRestart,
            customerStatistics, insuranceStatistics, claimStatistics,
            customerReport, insuranceReport, claimReport,
            customer, search, aboutUs;

    public MenuBar()
    {
        /* Menus */
        fileMenu = new Menu(Loc.c("menu_group_file"));
        fileMenu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + F"));

        statisticsMenu = new Menu(Loc.c("menu_group_statistics"));

        aboutMenu = new Menu(Loc.c("menu_group_about"));
        aboutMenu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + B"));

        newMenu = new Menu(Loc.c("new"));

        reportMenu = new Menu(Loc.c("report"));

        /* Menu items */

        // about
        aboutUs = new MenuItem(Loc.c("menu_about"));
        aboutUs.setAccelerator(KeyCombination.keyCombination("SHORTCUT + A"));
        aboutUs.setOnAction(e -> Mediator.inst.router(Signal.ABOUT, null));

        // file
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

        // statistics
        customerStatistics = new MenuItem(Loc.c("menu_customer_statistics"));
        customerStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.PERSON)));

        insuranceStatistics = new MenuItem(Loc.c("menu_insurance_statistics"));
        insuranceStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.INSURANCE)));

        claimStatistics = new MenuItem(Loc.c("menu_claim_statistics"));
        claimStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.CLAIM)));

        // reports
        customerReport = new MenuItem(Loc.c("menu_customer_report"));
        customerReport.setOnAction(e -> Mediator.inst.router(Signal.REPORT,
                new Payload(ReportController.Type.PERSON)));

        insuranceReport = new MenuItem(Loc.c("menu_insurance_report"));
        insuranceReport.setOnAction(e -> Mediator.inst.router(Signal.REPORT,
                new Payload(ReportController.Type.INSURANCE)));

        claimReport = new MenuItem(Loc.c("menu_claim_report"));
        claimReport.setOnAction(e -> Mediator.inst.router(Signal.REPORT,
                new Payload(ReportController.Type.CLAIM)));

        // new
        customer = new MenuItem(Loc.c("customer"));
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));

        search = new MenuItem(Loc.c("search"));
        search.setOnAction(e -> Mediator.inst.router(Signal.SEARCH, null));


        // Add to menus
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

        reportMenu.getItems().addAll(customerReport,
                insuranceReport, claimReport);

        aboutMenu.getItems().addAll(aboutUs);

        getMenus().addAll(fileMenu, newMenu, statisticsMenu,
                reportMenu, aboutMenu);
    }
}
