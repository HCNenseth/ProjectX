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
        this.injectColumn(Column.generate(Loc.get("firstname"), "firstname"));
        this.injectColumn(Column.generate(Loc.get("lastname"), "lastname"));
        this.injectColumn(Column.generate(Loc.get("street_address"), "streetAddress"));
        this.injectColumn(Column.generate(Loc.get("postal_code"), "postalCode"));
        this.injectColumn(Column.generate(Loc.get("city"), "city"));
    }
}
