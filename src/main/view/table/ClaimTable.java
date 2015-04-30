package main.view.table;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.person.Person;

/**
 * Created by alex on 4/24/15.
 */
public class ClaimTable extends Table<Claim>
{
    public ClaimTable()
    {
        injectColumn(Column.generate(Loc.get("date"), "date"));
        injectColumn(Column.generate(Loc.get("amount"), "amount"));
        injectColumn(Column.generate(Loc.get("description"), "desc"));
        injectColumn(Column.generate(Loc.get("status"), "status"));
    }
}
