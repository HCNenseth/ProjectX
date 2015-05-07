package main.controller;

import main.config.Config;
import main.localization.Loc;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.statistics.ClaimStatisticsView;
import main.view.concrete.statistics.InsuranceStatisticsView;
import main.view.concrete.statistics.PersonStatisticsView;

import java.util.List;

/**
 * Created by alex on 5/7/15.
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
        List<Person> persons = (List<Person>) Storage.getInstance().get(Config.PERSONS);
        PersonStatisticsView personStatisticsView = new PersonStatisticsView(persons);

        Resources.inst.getOtp().injectObservableTab(Loc.c("persons"),
                personStatisticsView.getNode(), null, true);
    }

    private static void loadInsuranceStatistics()
    {
        List<Insurance> insurances = (List<Insurance>)Storage.getInstance().get(Config.INSURANCES);
        InsuranceStatisticsView insuranceStatisticsView = new InsuranceStatisticsView(insurances);

        Resources.inst.getOtp().injectObservableTab(Loc.c("insurances"),
                insuranceStatisticsView.getNode(), null, true);
    }

    private static void loadClaimsStatistics()
    {
        List<Claim> claims = (List<Claim>)Storage.getInstance().get(Config.CLAIMS);
        ClaimStatisticsView claimStatisticsView = new ClaimStatisticsView(claims);

        Resources.inst.getOtp().injectObservableTab(Loc.c("claims"),
                claimStatisticsView.getNode(), null, true);
    }
}
