package main.view.concrete;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.App;
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
    private int rowNum = 0;
    private Person person;

    public PersonView(Person p)
    {
        this.person = p;

        gp = new GridPane();

        gp.setMinWidth(App.WIDTH - (App.WIDTH / 20));

        ColumnConstraints keyColumn = new ColumnConstraints();
        //keyColumn.setHgrow(Priority.SOMETIMES);
        keyColumn.setPercentWidth(20);

        ColumnConstraints valueColumn = new ColumnConstraints();
        //valueColumn.setHgrow(Priority.ALWAYS);
        valueColumn.setPercentWidth(80);

        gp.getColumnConstraints().add(0, keyColumn);
        gp.getColumnConstraints().add(1, valueColumn);

        gp.setHgap(5);
        gp.setVgap(5);

        //gp.setGridLinesVisible(true);

        initButtonPanel();
        initPersonFields();
    }

    public void initButtonPanel()
    {
        GridPane buttonPane = new GridPane();
        Label newInsurance = new Label(Loc.get("new_insurance"));
        Label editPerson = new Label(Loc.get("edit"));

        Button b1 = new Button(Loc.get("car"));
        b1.setOnAction(e -> InsuranceController.create(person, ConcreteType.CAR));

        Button b2 = new Button(Loc.get("boat"));
        b2.setOnAction(e -> InsuranceController.create(person, ConcreteType.BOAT));

        Button b3 = new Button(Loc.get("house"));
        b3.setOnAction(e -> InsuranceController.create(person, ConcreteType.HOUSE));

        Button b4 = new Button(Loc.get("vacation_house"));
        b4.setOnAction(e -> InsuranceController.create(person, ConcreteType.VACATION_HOUSE));

        Button b5 = new Button(Loc.get("travel"));
        b5.setOnAction(e -> InsuranceController.create(person, ConcreteType.TRAVEL));

        Button editButton = new Button(Loc.get("edit"));
        editButton.setOnAction(e -> PersonController.edit(person));

        buttonPane.add(newInsurance, 0, 0);
        buttonPane.add(b1, 1, 0);
        buttonPane.add(b2, 2, 0);
        buttonPane.add(b3, 3, 0);
        buttonPane.add(b4, 4, 0);
        buttonPane.add(b5, 5, 0);
        buttonPane.add(editPerson, 6, 0);
        buttonPane.add(editButton, 7, 0);

        buttonPane.setHgap(5);

        gp.add(buttonPane, 0, rowNum++, 2, 1);
    }

    public void initPersonFields()
    {
        // firstname
        gp.add(new Label(Loc.get("firstname")), 0, rowNum);
        gp.add(new Label(person.getFirstname()), 1, rowNum++);

        // lastname
        gp.add(new Label(Loc.get("lastname")), 0, rowNum);
        gp.add(new Label(person.getLastname()), 1, rowNum++);
        // street address
        gp.add(new Label(Loc.get("street_address")), 0, rowNum);
        gp.add(new Label(person.getStreetAddress()), 1, rowNum++);

        // postal code
        gp.add(new Label(Loc.get("postal_code")), 0, rowNum);
        gp.add(new Label(person.getPostalCode()), 1, rowNum++);

        // city
        gp.add(new Label(Loc.get("city")), 0, rowNum);
        gp.add(new Label(person.getCity()), 1, rowNum++);

        // date of birth
        gp.add(new Label(Loc.get("date_of_birth")), 0, rowNum);
        gp.add(new Label(person.getDateOfBirth().toString()), 1, rowNum++);

        // insurances table
        gp.add(new Label(Loc.get("insurances")), 0, rowNum++);
        gp.add(TableController.getInsuranceTable(person.getInsurances().stream())
                .getTable(), 0, rowNum++, 2, 1);

        // claims table
        gp.add(new Label(Loc.get("claims")), 0, rowNum++);
        gp.add(TableController.getClaimsTable(person.getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public GridPane getNode() { return gp; }
}
