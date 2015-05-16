package main.view.table;

import main.localization.Loc;
import main.model.insurance.Insurance;

/**
 * InsuranceTable.java
 */
public class InsuranceTable extends Table<Insurance>
{
    /**
     * Instantiates a new Insurance table.
     */
    public InsuranceTable()
    {
        injectColumn(Column.generate(Loc.c("id"), "id"));
        injectColumn(Column.generate(Loc.c("type"), "type"));
        injectColumn(Column.generate(Loc.c("premium"), "premium"));
        injectColumn(Column.generate(Loc.c("amount"), "amount"));
        injectColumn(Column.generate(Loc.c("deductible"), "deductible"));
        injectColumn(Column.generate(Loc.c("status"), "status"));
    }
}
