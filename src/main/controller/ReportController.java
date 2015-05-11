package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.ReportView;

/**
 * ReportController.java
 */
public class ReportController
{
    private ReportController() {}

    public static void view()
    {
        // references to the storage lists
        ReportView reportView = new ReportView();

        // Persons
        reportView.injectCustomerData(Person.getPersons());

        // Insurances
        reportView.injectInsuranceData(Insurance.getInsurances());

        // Claims
        reportView.injectClaimsData(Claim.getClaims());

        Resources.inst.getOtp().injectObservableTab(Loc.c("report"),
                reportView.getNode(), null, true);
    }
}
