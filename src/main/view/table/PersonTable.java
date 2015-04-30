package main.view.table;

import main.localization.Loc;
import main.model.person.Person;

/**
 * Created by alex on 4/24/15.
 */
public class PersonTable extends Table<Person>
{
    public PersonTable()
    {
        injectColumn(Column.generate(Loc.get("firstname"), "firstname"));
        injectColumn(Column.generate(Loc.get("lastname"), "lastname"));
        injectColumn(Column.generate(Loc.get("street_address"), "streetAddress"));
        injectColumn(Column.generate(Loc.get("postal_code"), "postalCode"));
        injectColumn(Column.generate(Loc.get("city"), "city"));
        injectColumn(Column.generate(Loc.get("status"), "status"));
    }
}
