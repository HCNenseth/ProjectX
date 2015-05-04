package main.view.table;

import main.localization.Loc;
import main.model.insurance.Insurance;

/**
 * Created by alex on 4/24/15.
 */
public class InsuranceTable extends Table<Insurance>
{
    public InsuranceTable()
    {
        //injectColumn(Column.generate(Loc.c("insurance"), "insuranceType"));
        injectColumn(Column.generate(Loc.c("type"), "type"));
        injectColumn(Column.generate(Loc.c("premium"), "premium"));
        injectColumn(Column.generate(Loc.c("amount"), "amount"));
        injectColumn(Column.generate(Loc.c("deductible"), "deductible"));
        injectColumn(Column.generate(Loc.c("status"), "status"));
    }
}
