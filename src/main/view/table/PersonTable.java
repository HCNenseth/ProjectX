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
        injectColumn(Column.generate(Loc.c("firstname"), "firstname"));
        injectColumn(Column.generate(Loc.c("lastname"), "lastname"));
        injectColumn(Column.generate(Loc.c("street_address"), "streetAddress"));
        injectColumn(Column.generate(Loc.c("postal_code"), "postalCode"));
        injectColumn(Column.generate(Loc.c("city"), "city"));
        injectColumn(Column.generate(Loc.c("status"), "status"));
    }
}
