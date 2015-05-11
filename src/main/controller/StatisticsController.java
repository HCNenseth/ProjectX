package main.controller;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.statistics.ClaimStatisticsView;
import main.view.concrete.statistics.InsuranceStatisticsView;
import main.view.concrete.statistics.PersonStatisticsView;


/**
 * StatisticsController.java
 */
public class StatisticsController
{
    public enum Type
    {
        PERSON, INSURANCE, CLAIM
    }

    public static void view(Payload p)
    {
        switch ((Type)p.getEnumValue()) {
            case PERSON: loadPersonStatistics(); break;
            case INSURANCE: loadInsuranceStatistics(); break;
            case CLAIM: loadClaimsStatistics(); break;
            default:
                throw new IllegalStateException("Unknown enum type");
        }
    }

    private static void loadPersonStatistics()
    {
        PersonStatisticsView personStatisticsView = new PersonStatisticsView(
                Person.getPersons());

        Resources.inst.getOtp().injectObservableTab(Loc.c("persons"),
                personStatisticsView.getNode(), null, true);
    }

    private static void loadInsuranceStatistics()
    {
        InsuranceStatisticsView insuranceStatisticsView = new InsuranceStatisticsView(
                Insurance.getInsurances());

        Resources.inst.getOtp().injectObservableTab(Loc.c("insurances"),
                insuranceStatisticsView.getNode(), null, true);
    }

    private static void loadClaimsStatistics()
    {
        ClaimStatisticsView claimStatisticsView = new ClaimStatisticsView(
                Claim.getClaims());

        Resources.inst.getOtp().injectObservableTab(Loc.c("claims"),
                claimStatisticsView.getNode(), null, true);
    }
}
