package main.view.concrete.report;

import main.localization.Loc;
import main.model.person.Person;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * PersonReportView.java
 */
public class PersonReportView extends ReportView
{
    private List<Person> personList;

    public PersonReportView(List<Person> personList)
    {
        this.personList = personList;
        loadData();
    }

    private void loadData()
    {
        LocalDate upperBound = personList.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Person::getDate)))
                .findFirst().get().getDate();

        LocalDate lowerBound = personList.stream()
                .sorted(Comparator.comparing(Person::getDate))
                .findFirst()
                .get()
                .getDate();

        addHeader(String.format("%s: %s - %s", Loc.c("range"), lowerBound, upperBound));

        addKey(Loc.c("total_person_count"));
        addValue(String.format("%d", personList.size()));

        IntStream insurances = personList.stream()
                .mapToInt(p -> p.getInsurances().size());
        if (insurances.count() > 0) {
            addKey(Loc.c("average_insurances_per_customer"));
            addValue(String.format("%.2f",
                    personList.stream()
                            .mapToInt(p -> p.getInsurances().size())
                            .average()
                            .getAsDouble()));
        }

        IntStream claims = personList.stream()
                .mapToInt(p -> p.getClaims().size());
        if (claims.count() > 0) {
            addKey(Loc.c("average_claims_per_customer"));
            addValue(String.format("%.2f",
                    personList.stream()
                            .mapToInt(p -> p.getClaims().size())
                            .average()
                            .getAsDouble()));
        }

    }
}
