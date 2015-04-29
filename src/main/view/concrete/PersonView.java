package main.view.concrete;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.config.Config;
import main.controller.InsuranceController;
import main.controller.PersonController;
import main.controller.TableController;
import main.localization.Loc;
import main.model.insurance.ConcreteType;
import main.model.person.Person;

/**
 * Created by alex on 4/28/15.
 */
public class PersonView
{
    private GridPane gp;
    private Person person;
    private int cellGap = 5;
    private int rowNum = 0;

    public PersonView(Person p)
    {
        this.person = p;

        gp = new GridPane();

        ColumnConstraints keyColumn = new ColumnConstraints();
        //keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(20);

        ColumnConstraints valueColumn = new ColumnConstraints();
        //valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(80);

        gp.getColumnConstraints().add(0, keyColumn);
        gp.getColumnConstraints().add(1, valueColumn);

        gp.setHgap(cellGap);
        gp.setVgap(cellGap);

        initButtonPanel();
        initPersonFields();
    }

    public void initButtonPanel()
    {
        int column = 0, row = 0;

        ToolBar buttonPane = new ToolBar();
        Label newInsurance = new Label(Loc.get("new_insurance"));
        Label editPerson = new Label(Loc.get("edit"));

        // car
        Button b1 = new Button(Loc.get("car"));
        b1.setOnAction(e -> InsuranceController.create(person, ConcreteType.CAR));

        // boat
        Button b2 = new Button(Loc.get("boat"));
        b2.setOnAction(e -> InsuranceController.create(person, ConcreteType.BOAT));

        // house
        Button b3 = new Button(Loc.get("house"));
        b3.setOnAction(e -> InsuranceController.create(person, ConcreteType.HOUSE));

        // vacation house
        Button b4 = new Button(Loc.get("vacation_house"));
        b4.setOnAction(e -> InsuranceController.create(person, ConcreteType.VACATION_HOUSE));

        // travel
        Button b5 = new Button(Loc.get("travel"));
        b5.setOnAction(e -> InsuranceController.create(person, ConcreteType.TRAVEL));

        // edit person
        Button editButton = new Button(Loc.get("edit"));
        editButton.setOnAction(e -> PersonController.edit(person));

        buttonPane.getItems().addAll(b1,b2,b3,b4,b5);

        /*
        buttonPane.add(newInsurance, column++, row);
        buttonPane.add(b1, column++, row);
        buttonPane.add(b2, column++, row);
        buttonPane.add(b3, column++, row);
        buttonPane.add(b4, column++, row);
        buttonPane.add(b5, column++, row);
        buttonPane.add(editPerson, column++, row);
        buttonPane.add(editButton, column++, row);

        buttonPane.setHgap(cellGap);
        */

        gp.add(buttonPane, 0, rowNum++, 2, 1);
    }

    public void initPersonFields()
    {
        int left = 0, right = 1;

        // firstname
        gp.add(new Label(Loc.get("firstname")), left, rowNum);
        gp.add(new Label(person.getFirstname()), right, rowNum++);

        // lastname
        gp.add(new Label(Loc.get("lastname")), left, rowNum);
        gp.add(new Label(person.getLastname()), right, rowNum++);
        // street address
        gp.add(new Label(Loc.get("street_address")), left, rowNum);
        gp.add(new Label(person.getStreetAddress()), right, rowNum++);

        // postal code
        gp.add(new Label(Loc.get("postal_code")), left, rowNum);
        gp.add(new Label(person.getPostalCode()), right, rowNum++);

        // city
        gp.add(new Label(Loc.get("city")), left, rowNum);
        gp.add(new Label(person.getCity()), right, rowNum++);

        // date of birth
        gp.add(new Label(Loc.get("date_of_birth")), left, rowNum);
        gp.add(new Label(person.getDateOfBirth().toString()), right, rowNum++);

        // insurances table
        gp.add(new Label(Loc.get("insurances")), left, rowNum++);
        gp.add(TableController.getInsuranceTable(person.getInsurances().stream())
                .getTable(), 0, rowNum++, 2, 1);

        // claims table
        gp.add(new Label(Loc.get("claims")), left, rowNum++);
        gp.add(TableController.getClaimsTable(person.getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public GridPane getNode() { return gp; }
}
