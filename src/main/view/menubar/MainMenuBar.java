package main.view.menubar;

import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCombination;
import main.controller.*;
import main.localization.Loc;

/**
 * MenuBar.java
 */
public class MainMenuBar extends MenuBar
{
    /**
     * Instantiates a new Menu bar.
     */
    public MainMenuBar()
    {
        setupFile();
        setupNew();
        setupStatistics();
        setupReport();
        setupAbout();
    }

    private void setupFile()
    {
        Menu fileMenu = new Menu(Loc.c("menu_group_file"));

        MenuItem fileSave = new MenuItem(Loc.c("menu_file_save"));
        fileSave.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.SAVE)));

        MenuItem closeProject = new MenuItem(Loc.c("menu_close_project"));
        closeProject.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_PROJECT)));

        MenuItem fileRestart = new MenuItem(Loc.c("restart"));
        fileRestart.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.RESTART)));

        MenuItem fileExit = new MenuItem(Loc.c("menu_file_exit"));
        fileExit.setAccelerator(KeyCombination.keyCombination("SHORTCUT + X"));
        fileExit.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.CLOSE)));

        fileMenu.getItems().addAll(closeProject,
                new SeparatorMenuItem(),
                fileSave,
                new SeparatorMenuItem(),
                fileRestart, fileExit);

        getMenus().add(fileMenu);
    }

    private void setupStatistics()
    {
        Menu statisticsMenu = new Menu(Loc.c("menu_group_statistics"));

        MenuItem customerStatistics = new MenuItem(Loc.c("menu_customer_statistics"));
        customerStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.PERSON)));

        MenuItem insuranceStatistics = new MenuItem(Loc.c("menu_insurance_statistics"));
        insuranceStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.INSURANCE)));

        MenuItem claimStatistics = new MenuItem(Loc.c("menu_claim_statistics"));
        claimStatistics.setOnAction(e -> Mediator.inst.router(Signal.STATISTICS,
                new Payload(StatisticsController.Type.CLAIM)));

        statisticsMenu.getItems().addAll(customerStatistics,
                insuranceStatistics, claimStatistics);

        getMenus().add(statisticsMenu);
    }

    private void setupAbout()
    {
        Menu aboutMenu = new Menu(Loc.c("menu_group_about"));

        MenuItem aboutUs = new MenuItem(Loc.c("menu_about"));
        aboutUs.setAccelerator(KeyCombination.keyCombination("SHORTCUT + A"));
        aboutUs.setOnAction(e -> Mediator.inst.router(Signal.ABOUT, null));

        aboutMenu.getItems().addAll(aboutUs);

        getMenus().add(aboutMenu);
    }

    private void setupReport()
    {
        Menu reportMenu = new Menu(Loc.c("report"));

        MenuItem customerReport = new MenuItem(Loc.c("menu_customer_report"));
        customerReport.setOnAction(e -> Mediator.inst.router(Signal.REPORT,
                new Payload(ReportController.Type.PERSON)));

        MenuItem insuranceReport = new MenuItem(Loc.c("menu_insurance_report"));
        insuranceReport.setOnAction(e -> Mediator.inst.router(Signal.REPORT,
                new Payload(ReportController.Type.INSURANCE)));

        MenuItem claimReport = new MenuItem(Loc.c("menu_claim_report"));
        claimReport.setOnAction(e -> Mediator.inst.router(Signal.REPORT,
                new Payload(ReportController.Type.CLAIM)));

        reportMenu.getItems().addAll(customerReport,
                insuranceReport, claimReport);

        getMenus().add(reportMenu);
    }

    private void setupNew()
    {
        Menu newMenu = new Menu(Loc.c("new"));

        MenuItem customer = new MenuItem(Loc.c("customer"));
        customer.setAccelerator(KeyCombination.keyCombination("SHORTCUT + C"));
        customer.setOnAction(e -> Mediator.inst.router(Signal.FILE,
                new Payload(main.controller.MenuBar.Type.NEW_CUSTOMER)));

        MenuItem search = new MenuItem(Loc.c("search"));
        search.setAccelerator(KeyCombination.keyCombination("SHORTCUT + S"));
        search.setOnAction(e -> Mediator.inst.router(Signal.SEARCH, null));

        newMenu.getItems().addAll(search, customer);

        getMenus().add(newMenu);
    }
}
