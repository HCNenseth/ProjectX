package main.view.concrete.report;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * InsuranceReportView.java
 */
public class InsuranceReportView extends ReportView
{
    private List<Insurance> insuranceList;

    /**
     * Instantiates a new Insurance report view.
     *
     * @param insuranceList the insurance list
     */
    public InsuranceReportView(List<Insurance> insuranceList)
    {
        this.insuranceList = insuranceList;

        loadData();
    }

    private void loadData()
    {
        LocalDate upperBound = insuranceList.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Insurance::getDate)))
                .findFirst()
                .get()
                .getDate();

        LocalDate lowerBound = insuranceList.stream()
                .sorted(Comparator.comparing(Insurance::getDate))
                .findFirst()
                .get()
                .getDate();

        addHeader(String.format("%s: %s - %s",
                Loc.c("range"), lowerBound, upperBound));

        // all
        addKey(Loc.c("total_insurance_count"));
        addValue(String.format("%d", insuranceList.size()));

        IntStream claims = insuranceList.stream()
                .mapToInt(i -> i.getClaims().size());
        if (claims.count() > 0) {
            addKey(Loc.c("average_claims_per_insurance"));
            addValue(String.format("%.2f",
                    insuranceList.stream()
                            .mapToInt(p -> p.getClaims().size())
                            .average()
                            .getAsDouble()));
        }

        // car
        addKey(Loc.c("total_car_insurance_count"));
        int carInsuranceCount = (int) insuranceList.stream()
                .filter(i -> i.identify() == InsuranceType.CAR)
                .count();
        addValue(String.format("%d", carInsuranceCount));
        if (carInsuranceCount > 0) {
            addKey(Loc.c("total_car_insurance_claim_count"));
            addValue(String.format("%d",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.CAR)
                            .mapToInt(i -> i.getClaims().size())
                            .sum()));
            addKey(Loc.c("average_car_insurance_claim"));
            addValue(String.format("%.2f",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.CAR)
                            .mapToInt(i -> i.getClaims().size())
                            .average()
                            .getAsDouble()));
        }

        // boat
        addKey(Loc.c("total_boat_insurance_count"));
        int boatInsuranceCount = (int) insuranceList.stream()
                .filter(i -> i.identify() == InsuranceType.BOAT)
                .count();
        addValue(String.format("%d", boatInsuranceCount));
        if (boatInsuranceCount > 0) {
            addKey(Loc.c("total_boat_insurance_claim_count"));
            addValue(String.format("%d",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.BOAT)
                            .mapToInt(i -> i.getClaims().size())
                            .sum()));
            addKey(Loc.c("average_boat_insurance_claim"));
            addValue(String.format("%.2f",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.BOAT)
                            .mapToInt(i -> i.getClaims().size())
                            .average()
                            .getAsDouble()));
        }

        // house
        addKey(Loc.c("total_house_insurance_count"));
        int houseInsuranceCount = (int) insuranceList.stream()
                .filter(i -> i.identify() == InsuranceType.HOUSE)
                .count();
        addValue(String.format("%d", houseInsuranceCount));
        if (houseInsuranceCount > 0) {
            addKey(Loc.c("total_house_insurance_claim_count"));
            addValue(String.format("%d",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.HOUSE)
                            .mapToInt(i -> i.getClaims().size())
                            .sum()));
            addKey(Loc.c("average_house_insurance_claim"));
            addValue(String.format("%.2f",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.HOUSE)
                            .mapToInt(i -> i.getClaims().size())
                            .average()
                            .getAsDouble()));
        }

        // vacation house
        addKey(Loc.c("total_vacation_house_insurance_count"));
        int vacationHouseInsuranceCount = (int) insuranceList.stream()
                .filter(i -> i.identify() == InsuranceType.VACATION_HOUSE)
                .count();
        addValue(String.format("%d", vacationHouseInsuranceCount));
        if (vacationHouseInsuranceCount > 0) {
            addKey(Loc.c("total_vacation_house_insurance_claim_count"));
            addValue(String.format("%d",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.VACATION_HOUSE)
                            .mapToInt(i -> i.getClaims().size())
                            .sum()));
            addKey(Loc.c("average_vacation_house_insurance_claim"));
            addValue(String.format("%.2f",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.VACATION_HOUSE)
                            .mapToInt(i -> i.getClaims().size())
                            .average()
                            .getAsDouble()));
        }

        // travel
        addKey(Loc.c("total_travel_insurance_count"));
        int travelInsuranceCount = (int) insuranceList.stream()
                .filter(i -> i.identify() == InsuranceType.TRAVEL)
                .count();

        addValue(String.format("%d", travelInsuranceCount));
        if (travelInsuranceCount > 0) {
            addKey(Loc.c("total_travel_insurance_claim_count"));
            addValue(String.format("%d",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.TRAVEL)
                            .mapToInt(i -> i.getClaims().size())
                            .sum()));
            addKey(Loc.c("average_travel_insurance_claim"));
            addValue(String.format("%.2f",
                    insuranceList.stream()
                            .filter(i -> i.identify() == InsuranceType.TRAVEL)
                            .mapToInt(i -> i.getClaims().size())
                            .average()
                            .getAsDouble()));
        }
    }
}
