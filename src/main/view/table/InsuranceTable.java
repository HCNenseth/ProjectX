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
        //injectColumn(Column.generate(Loc.get("insurance"), "insuranceType"));
        injectColumn(Column.generate(Loc.get("type"), "type"));
        injectColumn(Column.generate(Loc.get("premium"), "premium"));
        injectColumn(Column.generate(Loc.get("amount"), "amount"));
        injectColumn(Column.generate(Loc.get("deductible"), "deductible"));
    }
}
