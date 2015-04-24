package main.view.table;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.person.Person;

/**
 * Created by alex on 4/24/15.
 */
public class InsuranceTable extends Table<Insurance>
{
    public InsuranceTable()
    {
        this.injectColumn(Column.generate(Loc.get("customer"), "customer"));
        this.injectColumn(Column.generate(Loc.get("premium"), "premium"));
        this.injectColumn(Column.generate(Loc.get("amount"), "amount"));
    }
}
