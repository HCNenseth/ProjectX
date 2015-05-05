package main.view.concrete;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import main.controller.InsuranceController;
import main.controller.PersonController;
import main.controller.TableController;
import main.localization.Loc;
import main.model.insurance.ConcreteType;
import main.model.person.Person;
import main.view.StandardGridPane;

/**
 * Created by alex on 4/28/15.
 */
public class PersonView
{
    private StandardGridPane gp;
    private Person person;
    private int cellGap = 5;
    private int rowNum = 0;

    public PersonView(Person p)
    {
        this.person = p;

        gp = new StandardGridPane();

        initButtonPanel();
        initPersonFields();
    }

    public void initButtonPanel()
    {
        ToolBar buttonPane = new ToolBar();

        // car
        Button b1 = new Button(Loc.c("car"));
        b1.setOnAction(e -> InsuranceController.create(person, ConcreteType.CAR));

        // boat
        Button b2 = new Button(Loc.c("boat"));
        b2.setOnAction(e -> InsuranceController.create(person, ConcreteType.BOAT));

        // house
        Button b3 = new Button(Loc.c("house"));
        b3.setOnAction(e -> InsuranceController.create(person, ConcreteType.HOUSE));

        // vacation house
        Button b4 = new Button(Loc.c("vacation_house"));
        b4.setOnAction(e -> InsuranceController.create(person, ConcreteType.VACATION_HOUSE));

        // travel
        Button b5 = new Button(Loc.c("travel"));
        b5.setOnAction(e -> InsuranceController.create(person, ConcreteType.TRAVEL));

        // edit person
        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> PersonController.edit(person));

        buttonPane.getItems().addAll(b1, b2, b3, b4, b5, editButton);

        gp.add(buttonPane, 0, rowNum++, 2, 1);
    }

    public void initPersonFields()
    {
        int left = 0, right = 1;

        // firstname
        gp.add(new Label(Loc.c("firstname")), left, rowNum);
        gp.add(new Label(person.getFirstname()), right, rowNum++);

        // lastname
        gp.add(new Label(Loc.c("lastname")), left, rowNum);
        gp.add(new Label(person.getLastname()), right, rowNum++);
        // street address
        gp.add(new Label(Loc.c("street_address")), left, rowNum);
        gp.add(new Label(person.getStreetAddress()), right, rowNum++);

        // postal code
        gp.add(new Label(Loc.c("postal_code")), left, rowNum);
        gp.add(new Label(person.getPostalCode()), right, rowNum++);

        // city
        gp.add(new Label(Loc.c("city")), left, rowNum);
        gp.add(new Label(person.getCity()), right, rowNum++);

        // date of birth
        gp.add(new Label(Loc.c("date_of_birth")), left, rowNum);
        gp.add(new Label(person.getDateOfBirth().toString()), right, rowNum++);

        // phone number
        gp.add(new Label(Loc.c("phone_number")), left, rowNum);
        gp.add(new Label(person.getPhoneNumber()), right, rowNum++);

        // email
        gp.add(new Label(Loc.c("email")), left, rowNum);
        gp.add(new Label(person.getEmail()), right,rowNum++);

        // insurances table
        gp.add(new Label(Loc.c("insurances")), left, rowNum++);
        gp.add(TableController.getInsuranceTable(person.getInsurances().stream())
                .getTable(), 0, rowNum++, 2, 1);

        // claims table
        gp.add(new Label(Loc.c("claims")), left, rowNum++);
        gp.add(TableController.getClaimsTable(person.getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public StandardGridPane getNode() { return gp; }
}
