package main.controller;

import main.config.Config;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.ReportView;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class ReportController
{
    private ReportController() {}

    public static void view()
    {
        // references to the storage lists
        List<Person> persons = (List<Person>) Storage.getInstance().get(Config.PERSONS);
        List<Insurance> insurances = (List<Insurance>)Storage.getInstance().get(Config.INSURANCES);
        List<Claim> claims = (List<Claim>)Storage.getInstance().get(Config.CLAIMS);

        ReportView reportView = new ReportView();
        reportView.injectCustomerData(persons);
        reportView.injectInsuranceData(insurances);
        reportView.injectClaimsData(claims);

        Resources.inst.getOtp().injectObservableTab(Loc.c("report"),
                reportView.getNode(), null, true);
    }
}
