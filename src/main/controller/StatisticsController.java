package main.controller;

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

    public static void view(Payload p)
    {
        switch ((Type)p.getEnumValue()) {
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

    public static void loadPersonStatistics(List<Person> personList)
    {
        PersonStatisticsView personStatisticsView = new PersonStatisticsView(
                personList);

        Resources.inst.getOtp().injectObservableTab(Loc.c("persons"),
                personStatisticsView.getNode(), null, true);
    }

    public static void loadInsuranceStatistics(List<Insurance> insuranceList)
    {
        InsuranceStatisticsView insuranceStatisticsView = new InsuranceStatisticsView(
                insuranceList);

        Resources.inst.getOtp().injectObservableTab(Loc.c("insurances"),
                insuranceStatisticsView.getNode(), null, true);
    }

    public static void loadClaimsStatistics(List<Claim> claimList)
    {
        ClaimStatisticsView claimStatisticsView = new ClaimStatisticsView(
                claimList);

        Resources.inst.getOtp().injectObservableTab(Loc.c("claims"),
                claimStatisticsView.getNode(), null, true);
    }
}
