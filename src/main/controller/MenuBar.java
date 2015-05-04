package main.controller;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import main.App;
import main.localization.Loc;
import main.model.Storage;
import main.preference.Pref;

import java.io.File;
import java.io.IOException;

/**
 * Created by alex on 4/23/15.
 */
public class MenuBar
{
    public enum Type {
        OPEN, SAVE_AS, SAVE, CLOSE, RESTART,
        NEW_CUSTOMER, ABOUT,
    }

    public MenuBar(Payload p)
    {
        switch ((Type)p.getEnumValue()) {
            case OPEN: openFile(); break;
            case SAVE_AS: safeFileAs(); break;
            case SAVE: saveFile(); break;
            case CLOSE: exitApp(); break;
            case RESTART: restartApp(); break;
            case NEW_CUSTOMER: newCustomer(); break;
            case ABOUT: aboutUs(); break;
            default: return;
        }
    }

    private void aboutUs()
    {
        AboutController.view();
    }

    private void exitApp()
    {
        saveFile();
        Platform.exit();
    }

    private void restartApp()
    {
        saveFile();
        App.restart = true;
        App.kill();
    }

    private void openFile()
    {
        FileChooser fc = new FileChooser();
        fc.setTitle(Loc.c("choose_data_file"));

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Loc.c("data_files"), "*.dat")
        );
        File file = fc.showOpenDialog(null);

        if (file != null) {
            try {
                Storage.injectFilename(file.getPath());
                Storage.getInstance().read();
                Pref.inst.put("last_used_file", file.getPath());
            } catch (IOException | ClassNotFoundException e) {
                // TODO do something meaningful with this error.
                System.out.println("error reading from file");
            }
        }
    }

    private void saveFile()
    {
        if (!Storage.hasFilename()) { return; }

        try {
            Storage.getInstance().save();
        } catch (IOException ioe) {
            // TODO do something meaningful with this error.
            System.out.println("error writing to file");
        }
    }

    private void safeFileAs()
    {
        FileChooser fc = new FileChooser();
        fc.setTitle(Loc.c("save_data_file_as"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Loc.c("data_files"), "*.dat")
        );
        File file = fc.showSaveDialog(null);

        // TODO implement this correctly
        /*
        if (file != null) {
            // if extension is omitted from filename
            if (! file.getName().contains(".")) {
                file = new File(file.getAbsolutePath() + ".dat");
            }

            // try to save to file
            try {
            } catch (IOException ioe) {

            }
        }
        */
    }

    public void newCustomer() { PersonController.create(); }
}
