package main.view.table;

import main.localization.Loc;
import main.model.claim.Claim;

/**
 * Created by alex on 4/24/15.
 */
public class ClaimTable extends Table<Claim>
{
    public ClaimTable()
    {
        injectColumn(Column.generate(Loc.c("id"), "id"));
        injectColumn(Column.generate(Loc.c("date_of_claim"), "claimDate"));
        injectColumn(Column.generate(Loc.c("date_of_damages"), "dateOfDamages"));
        injectColumn(Column.generate(Loc.c("amount"), "amount"));
        injectColumn(Column.generate(Loc.c("description"), "desc"));
        injectColumn(Column.generate(Loc.c("status"), "status"));
    }
}
