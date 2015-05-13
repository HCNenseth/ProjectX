package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.report.ClaimReportView;
import main.view.concrete.report.InsuranceReportView;
import main.view.concrete.report.PersonReportView;

import java.util.List;

/**
 * ReportController.java
 */
public class ReportController
{
    public enum Type
    {
        PERSON, INSURANCE, CLAIM
    }

    private ReportController() {}

    public static void view(Payload p)
    {
        switch ((Type) p.getEnumValue()) {
            case PERSON:
                loadPersonReport(Person.getPersons(), Loc.c("person_report"));
                break;
            case INSURANCE:
                loadInsuranceReport(Insurance.getInsurances(), Loc.c("insurance_report"));
                break;
            case CLAIM:
                loadClaimReport(Claim.getClaims(), Loc.c("claim_report"));
                break;
            default:
                throw new IllegalStateException("You have now reached the end of the universe!");
        }

    }

    public static void loadPersonReport(List<Person> personsList, String title)
    {
        PersonReportView reportView = new PersonReportView(personsList);
        Resources.inst.getOtp().injectObservableTab(title,
                reportView.getNode(), null, true);
    }

    public static void loadInsuranceReport(List<Insurance> insuranceList, String title)
    {
        InsuranceReportView reportView = new InsuranceReportView(insuranceList);
        Resources.inst.getOtp().injectObservableTab(title,
                reportView.getNode(), null, true);
    }

    public static void loadClaimReport(List<Claim> claimList, String title)
    {
        ClaimReportView reportView = new ClaimReportView(claimList);
        Resources.inst.getOtp().injectObservableTab(title,
                reportView.getNode(), null, true);
    }
}
