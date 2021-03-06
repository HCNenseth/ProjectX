package main.view.table;

import main.localization.Loc;
import main.model.person.Person;

/**
 * PersonTable.java
 */
public class PersonTable extends Table<Person>
{
    /**
     * Instantiates a new Person table.
     */
    public PersonTable()
    {
        // customerId
        injectColumn(Column.generate(Loc.c("id"), "id"));

        // name
        injectColumn(Column.generate(Loc.c("firstname"), "firstname"));
        injectColumn(Column.generate(Loc.c("lastname"), "lastname"));

        // contact information
        injectColumn(Column.generate(Loc.c("email"), "email"));
        injectColumn(Column.generate(Loc.c("phone_number"), "phoneNumber"));

        // location data
        injectColumn(Column.generate(Loc.c("street_address"), "streetAddress"));
        injectColumn(Column.generate(Loc.c("postal_code"), "postalCode"));
        injectColumn(Column.generate(Loc.c("city"), "city"));

        // status data
        injectColumn(Column.generate(Loc.c("status"), "status"));
    }
}
