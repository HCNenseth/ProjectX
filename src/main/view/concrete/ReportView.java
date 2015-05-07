package main.view.concrete;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.InsuranceType;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class ReportView
{
    private StandardGridPane gp;
    private int rowNum = 0;
    private int headerSize = 16;

    public ReportView()
    {
        gp = new StandardGridPane();
    }

    public void injectCustomerData(List<Person> personList)
    {
        addHeader(Loc.c("persons"));

        addKey(Loc.c("total_person_count"));
        addValue(String.format("%d", personList.size()));

        addKey(Loc.c("average_insurances_per_customer"));
        addValue(String.format("%.2f",
                personList.stream()
                        .mapToInt(p -> p.getInsurances().size())
                        .average()
                        .getAsDouble()));

        addKey(Loc.c("average_claims_per_customer"));
        addValue(String.format("%.2f",
                personList.stream()
                        .mapToInt(p -> p.getClaims().size())
                        .average()
                        .getAsDouble()));

    }

    public void injectInsuranceData(List<Insurance> insuranceList)
    {
        addHeader(Loc.c("insurances"));

        // all
        addKey(Loc.c("total_insurance_count"));
        addValue(String.format("%d", insuranceList.size()));

        addKey(Loc.c("average_claims_per_insurance"));
        addValue(String.format("%.2f",
                insuranceList.stream()
                        .mapToInt(p -> p.getClaims().size())
                        .average()
                        .getAsDouble()));

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
        int vacationHouseInsuranceCount = (int)insuranceList.stream()
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

    public void injectClaimsData(List<Claim> claimList)
    {
        addHeader(Loc.c("claims"));

        addKey(Loc.c("total_claims_count"));
        addValue(String.format("%d", claimList.size()));

    }

    private void addHeader(String string)
    {
        Label label = new Label(string);
        label.setFont(new Font(headerSize));
        gp.add(label, 0, rowNum++, 2, 1);
        addColSpan(new Separator());
    }

    private void addColSpan(Node node)
    {
        gp.add(node, 0, rowNum++, 2, 1);
    }

    private void addKey(String string) { gp.add(new Label(string), 0, rowNum); }

    private void addValue(String string) { gp.add(new Label(string), 1, rowNum++); }

    public StandardGridPane getNode() { return gp; }

}
