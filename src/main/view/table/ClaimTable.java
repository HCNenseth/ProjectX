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
        this.injectColumn(Column.generate(Loc.get("customer"), "customer"));
        this.injectColumn(Column.generate(Loc.get("insurance"), "insurance"));
        this.injectColumn(Column.generate(Loc.get("description"), "desc"));
    }
}
