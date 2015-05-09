package main.view.concrete;

import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import main.config.Config;
import main.controller.InsuranceController;
import main.controller.PersonController;
import main.controller.TableController;
import main.localization.Loc;
import main.model.insurance.InsuranceType;
import main.model.person.Person;
import main.view.StandardGridPane;

import java.awt.*;
import java.util.DoubleSummaryStatistics;

/**
 * Created by alex on 4/28/15.
 */
public class PersonView extends StandardGridPane
{
    private Person person;
    private int cellGap = 5;
    private int rowNum = 0;
    private boolean drawn = false;

    public PersonView(Person p)
    {
        this.person = p;

        draw();
    }

    private void draw()
    {
        if(drawn)
        {
            getChildren().clear();
            rowNum = 0;
        }

        initButtonPanel();
        initPersonFields();
        drawn = true;
    }

    private void initButtonPanel()
    {
        AnchorPane buttonPane = new AnchorPane();

        // car
        Button b1 = new Button(Loc.c("car"));
        b1.setOnAction(e -> InsuranceController.create(person, InsuranceType.CAR));

        // boat
        Button b2 = new Button(Loc.c("boat"));
        b2.setOnAction(e -> InsuranceController.create(person, InsuranceType.BOAT));

        // house
        Button b3 = new Button(Loc.c("house"));
        b3.setOnAction(e -> InsuranceController.create(person, InsuranceType.HOUSE));

        // vacation house
        Button b4 = new Button(Loc.c("vacation_house"));
        b4.setOnAction(e -> InsuranceController.create(person, InsuranceType.VACATION_HOUSE));

        // travel
        Button b5 = new Button(Loc.c("travel"));
        b5.setOnAction(e -> InsuranceController.create(person, InsuranceType.TRAVEL));

        // edit person
        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> PersonController.edit(person));

        // refresh person
        Button refreshButton = new Button(Loc.c("refresh"));
        refreshButton.setOnAction(e -> draw());

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.getChildren().addAll(b1, b2, b3, b4, b5);
        hBox2.getChildren().addAll(editButton, refreshButton);

        AnchorPane.setLeftAnchor(hBox1, 0d);
        AnchorPane.setRightAnchor(hBox2, 0d);

        buttonPane.getChildren().addAll(hBox1, hBox2);
        buttonPane.setPadding(new Insets(10, 0, 10, 0));

        add(buttonPane, 0, rowNum++, 2, 1);
    }

    private void initPersonFields()
    {
        int left = 0, right = 1;

        // firstname
        add(new Label(Loc.c("firstname")), left, rowNum);
        add(new Label(person.getFirstname()), right, rowNum++);

        // lastname
        add(new Label(Loc.c("lastname")), left, rowNum);
        add(new Label(person.getLastname()), right, rowNum++);
        // street address
        add(new Label(Loc.c("street_address")), left, rowNum);
        add(new Label(person.getStreetAddress()), right, rowNum++);

        // postal code
        add(new Label(Loc.c("postal_code")), left, rowNum);
        add(new Label(person.getPostalCode()), right, rowNum++);

        // city
        add(new Label(Loc.c("city")), left, rowNum);
        add(new Label(person.getCity()), right, rowNum++);

        // date of birth
        add(new Label(Loc.c("date_of_birth")), left, rowNum);
        add(new Label(person.getDateOfBirth().toString()), right, rowNum++);

        // phone number
        add(new Label(Loc.c("phone_number")), left, rowNum);
        add(new Label(person.getPhoneNumber()), right, rowNum++);

        // email
        add(new Label(Loc.c("email")), left, rowNum);
        add(new Label(person.getEmail()), right,rowNum++);

        // status
        add(new Label(Loc.c("status")), left, rowNum);
        add(new Label(person.getStatus().getValue()), right, rowNum++);

        // insurances table
        add(new Label(Loc.c("insurances")), left, rowNum++);
        add(TableController.getInsuranceTable(person.getInsurances().stream())
                .getTable(), 0, rowNum++, 2, 1);

        // claims table
        add(new Label(Loc.c("claims")), left, rowNum++);
        add(TableController.getClaimsTable(person.getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public StandardGridPane getNode() { return this; }
}
