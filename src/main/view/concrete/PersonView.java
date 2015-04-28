package main.view.concrete;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.controller.TableController;
import main.localization.Loc;
import main.model.person.Person;

/**
 * Created by alex on 4/28/15.
 */
public class PersonView
{
    private GridPane gp;
    private int rowNum = 0;
    private Person person;

    public PersonView(Person p)
    {
        this.person = p;

        gp = new GridPane();
        initPersonFields();
    }

    public void initPersonFields()
    {
        gp.add(new Label(Loc.get("firstname")), 0, rowNum);
        gp.add(new Label(person.getFirstname()), 1, rowNum++);

        gp.add(new Label(Loc.get("lastname")), 0, rowNum);
        gp.add(new Label(person.getLastname()), 1, rowNum++);

        gp.add(new Label(Loc.get("street_address")), 0, rowNum);
        gp.add(new Label(person.getStreetAddress()), 1, rowNum++);

        gp.add(new Label(Loc.get("postal_code")), 0, rowNum);
        gp.add(new Label(person.getPostalCode()), 1, rowNum++);

        gp.add(new Label(Loc.get("city")), 0, rowNum);
        gp.add(new Label(person.getCity()), 1, rowNum++);

        gp.add(new Label(Loc.get("date_of_birth")), 0, rowNum);
        gp.add(new Label(person.getDateOfBirth().toString()), 1, rowNum++);


        gp.add(new Label(Loc.get("insurances")), 0, rowNum++);
        gp.add(TableController.getInsuranceTable(person.getInsurances().stream())
                .getTable(), 0, rowNum++);

        gp.add(new Label(Loc.get("claims")), 0, rowNum++);
        gp.add(TableController.getClaimsTable(person.getClaims().stream())
                .getTable(), 0, rowNum++);
    }

    public GridPane getNode() { return gp; }
}
