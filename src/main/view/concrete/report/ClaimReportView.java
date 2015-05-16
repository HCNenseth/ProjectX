package main.view.concrete.report;

import main.localization.Loc;
import main.model.claim.Claim;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ClaimReportView.java
 */
public class ClaimReportView extends ReportView
{
    private List<Claim> claimList;

    /**
     * Instantiates a new Claim report view.
     *
     * @param claimList the claim list
     */
    public ClaimReportView(List<Claim> claimList)
    {
        this.claimList = claimList;

        loadData();
    }

    private void loadData()
    {
        LocalDate upperBound = claimList.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Claim::getDate)))
                .findFirst()
                .get()
                .getDate();

        LocalDate lowerBound = claimList.stream()
                .sorted(Comparator.comparing(Claim::getDate))
                .findFirst()
                .get()
                .getDate();

        addHeader(String.format("%s: %s - %s",
                Loc.c("range"), lowerBound, upperBound));

        addKey(Loc.c("total_claims_count"));
        addValue(String.format("%d", claimList.size()));

        addKey(Loc.c("total_claim_amount"));
        addValue(String.format("%.2f",
                (float) claimList.stream().mapToDouble(c -> c.getAmount()).sum()));

        addKey(Loc.c("total_claim_deductible"));
        addValue(String.format("%.2f",
                (float) claimList.stream().mapToDouble(c -> c.getDeductible()).sum()));
    }
}
