package main.controller;

import main.localization.Loc;
import main.view.Resources;
import main.view.concrete.AboutView;

/**
 * AboutController.php
 */
public class AboutController
{
    private AboutController(){ }

    public static void view()
    {
        AboutView view = new AboutView();
        Resources.inst.getOtp().injectObservableTab(Loc.c("about"),
                view.getNode(), null, true);
    }

}
