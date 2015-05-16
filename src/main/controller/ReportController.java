package main.controller;

import main.config.Config;
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
    /**
     * The enum Type.
     */
    public enum Type
    {
        /**
         * The PERSON.
         */
        PERSON, /**
     * The INSURANCE.
     */
    INSURANCE, /**
     * The CLAIM.
     */
    CLAIM
    }

    private ReportController()
    {
    }

    /**
     * View void.
     *
     * @param p the p
     */
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

    /**
     * Load person report.
     *
     * @param personsList the persons list
     * @param title the title
     */
    public static void loadPersonReport(List<Person> personsList, String title)
    {
        PersonReportView reportView = new PersonReportView(personsList);
        Resources.inst.getOtp().injectObservableTab(title,
                reportView.getNode(), null, Config.REPORT_TAB_IMAGE, true);
    }

    /**
     * Load insurance report.
     *
     * @param insuranceList the insurance list
     * @param title the title
     */
    public static void loadInsuranceReport(List<Insurance> insuranceList, String title)
    {
        InsuranceReportView reportView = new InsuranceReportView(insuranceList);
        Resources.inst.getOtp().injectObservableTab(title,
                reportView.getNode(), null, Config.REPORT_TAB_IMAGE, true);
    }

    /**
     * Load claim report.
     *
     * @param claimList the claim list
     * @param title the title
     */
    public static void loadClaimReport(List<Claim> claimList, String title)
    {
        ClaimReportView reportView = new ClaimReportView(claimList);
        Resources.inst.getOtp().injectObservableTab(title,
                reportView.getNode(), null, Config.REPORT_TAB_IMAGE, true);
    }
}
