package main.controller;

import main.config.Config;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.statistics.ClaimStatisticsView;
import main.view.concrete.statistics.InsuranceStatisticsView;
import main.view.concrete.statistics.PersonStatisticsView;

import java.util.List;


/**
 * StatisticsController.java
 */
public class StatisticsController
{
    public enum Type
    {
        PERSON, INSURANCE, CLAIM
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
                loadPersonStatistics(Person.getPersons());
                break;
            case INSURANCE:
                loadInsuranceStatistics(Insurance.getInsurances());
                break;
            case CLAIM:
                loadClaimsStatistics(Claim.getClaims());
                break;
            default:
                throw new IllegalStateException("Unknown enum type");
        }
    }

    /**
     * Load person statistics.
     *
     * @param personList the person list
     */
    public static void loadPersonStatistics(List<Person> personList)
    {
        PersonStatisticsView personStatisticsView = new PersonStatisticsView(
                personList);

        Resources.inst.getOtp().injectObservableTab(Loc.c("statistics_persons"),
                personStatisticsView.getNode(), null, Config.STATS_TAB_IMAGE, true);
    }

    /**
     * Load insurance statistics.
     *
     * @param insuranceList the insurance list
     */
    public static void loadInsuranceStatistics(List<Insurance> insuranceList)
    {
        InsuranceStatisticsView insuranceStatisticsView = new InsuranceStatisticsView(
                insuranceList);

        Resources.inst.getOtp().injectObservableTab(Loc.c("statistics_insurances"),
                insuranceStatisticsView.getNode(), null, Config.STATS_TAB_IMAGE, true);
    }

    /**
     * Load claims statistics.
     *
     * @param claimList the claim list
     */
    public static void loadClaimsStatistics(List<Claim> claimList)
    {
        ClaimStatisticsView claimStatisticsView = new ClaimStatisticsView(
                claimList);

        Resources.inst.getOtp().injectObservableTab(Loc.c("statistics_claims"),
                claimStatisticsView.getNode(), null, Config.STATS_TAB_IMAGE, true);
    }
}
