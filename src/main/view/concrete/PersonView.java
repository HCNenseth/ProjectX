package main.view.concrete;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.config.Config;
import main.controller.InsuranceController;
import main.controller.PersonController;
import main.controller.TableController;
import main.localization.Loc;
import main.model.Model;
import main.model.Status;
import main.model.insurance.InsuranceType;
import main.model.person.Person;
import main.view.StandardGridPane;
import main.view.table.SugarTable;

/**
 * Created by alex on 4/28/15.
 */
public class PersonView extends StandardGridPane
{
    private Person person;
    private int cellGap = 5;
    private int rowNum = 0;
    private boolean drawn = false;

    /**
     * Instantiates a new Person view.
     *
     * @param person the person
     */
    public PersonView(Person person)
    {
        super(ColumnCount.ONE);

        this.person = person;
        draw();
    }

    private void draw()
    {
        if (drawn) {
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
        b1.setOnAction(e -> InsuranceController.create(person,
                InsuranceType.CAR));

        // boat
        Button b2 = new Button(Loc.c("boat"));
        b2.setOnAction(e -> InsuranceController.create(person,
                InsuranceType.BOAT));

        // house
        Button b3 = new Button(Loc.c("house"));
        b3.setOnAction(e -> InsuranceController.create(person,
                InsuranceType.HOUSE));

        // vacation house
        Button b4 = new Button(Loc.c("vacation_house"));
        b4.setOnAction(e -> InsuranceController.create(person,
                InsuranceType.VACATION_HOUSE));

        // travel
        Button b5 = new Button(Loc.c("travel"));
        b5.setOnAction(e -> InsuranceController.create(person,
                InsuranceType.TRAVEL));

        HBox leftAlignedButtons = new HBox();
        leftAlignedButtons.setSpacing(cellGap);
        leftAlignedButtons.getChildren().addAll(b1, b2, b3, b4, b5);

        // edit person
        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> PersonController.edit(person));

        // refresh person
        Button refreshButton = new Button(Loc.c("refresh"));
        refreshButton.setOnAction(e -> draw());

        HBox rightAlignedButtons = new HBox();
        rightAlignedButtons.setSpacing(cellGap);
        rightAlignedButtons.getChildren().addAll(editButton, refreshButton);

        AnchorPane.setLeftAnchor(leftAlignedButtons, 0d);
        AnchorPane.setRightAnchor(rightAlignedButtons, 0d);

        buttonPane.getChildren().addAll(leftAlignedButtons, rightAlignedButtons);

        add(buttonPane, 0, rowNum++, 2, 1);
    }

    private void initPersonFields()
    {
        GridPane innerGridPane = new GridPane();
        int leftInnerRowNum = 0, rightInnerRowNum = 0;
        int first = 0, second = 1, third = 2, fourth = 3;

        ColumnConstraints keyColumn = new ColumnConstraints();
        keyColumn.setPercentWidth(15);
        ColumnConstraints valueColumn = new ColumnConstraints();
        valueColumn.setPercentWidth(35);
        innerGridPane.getColumnConstraints().add(first, keyColumn);
        innerGridPane.getColumnConstraints().add(second, valueColumn);
        innerGridPane.getColumnConstraints().add(third, keyColumn);
        innerGridPane.getColumnConstraints().add(fourth, valueColumn);

        /* LEFT FIELDS (first and second column */

        // customerId
        innerGridPane.add(new Label(Loc.c("customer_id")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getId()),
                second, leftInnerRowNum++);

        // firstname
        innerGridPane.add(new Label(Loc.c("firstname")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getFirstname()),
                second, leftInnerRowNum++);

        // lastname
        innerGridPane.add(new Label(Loc.c("lastname")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getLastname()),
                second, leftInnerRowNum++);
        // street address
        innerGridPane.add(new Label(Loc.c("street_address")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getStreetAddress()),
                second, leftInnerRowNum++);

        // postal code
        innerGridPane.add(new Label(Loc.c("postal_code")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getPostalCode()),
                second, leftInnerRowNum++);

        // city
        innerGridPane.add(new Label(Loc.c("city")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getCity()),
                second, leftInnerRowNum++);

        // date of birth
        innerGridPane.add(new Label(Loc.c("date_of_birth")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getDateOfBirth().toString()),
                second, leftInnerRowNum++);

        // phone number
        innerGridPane.add(new Label(Loc.c("phone_number")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getPhoneNumber()),
                second, leftInnerRowNum++);

        // email
        innerGridPane.add(new Label(Loc.c("email")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getEmail()),
                second, leftInnerRowNum++);

        // status
        innerGridPane.add(new Label(Loc.c("status")),
                first, leftInnerRowNum);
        innerGridPane.add(new Label(person.getStatus().getValue()),
                second, leftInnerRowNum++);

        /* RIGHT FIELDS (third and fourth column) */

        int insuranceActiveCount = (int) person.getInsurances().stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .count();
        int insuranceTotalCount = (int) person.getInsurances().stream()
                .count();
        boolean totalCustomer = insuranceActiveCount >= Config.TOTAL_CUSTOMER_INSURANCE_COUNT;

        // number of insurances
        innerGridPane.add(new Label(Loc.c("number_of_insurances")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(String.format("%d (%d)",
                        insuranceActiveCount, insuranceTotalCount)),
                fourth, rightInnerRowNum++);

        // total customer
        innerGridPane.add(new Label(Loc.c("total_customer")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(totalCustomer ? Loc.c("true") : Loc.c("false")),
                fourth, rightInnerRowNum++);

        // sum of all active insurances
        innerGridPane.add(new Label(Loc.c("total_insurance_amount_sum")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(String.format("%.2f", person.getInsurances()
                .stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .mapToDouble(i -> i.getAmount())
                .sum())), fourth, rightInnerRowNum++);

        // premium of all insurances
        innerGridPane.add(new Label(Loc.c("total_insurance_premium_sum")),
                third, rightInnerRowNum);
        double insurancePremium = person.getInsurances()
                .stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .mapToDouble(i -> i.getPremium())
                .sum();
        if (totalCustomer) {
            insurancePremium *= ((Config.TOTAL_CUSTOMER_DISCOUNT / 100) + 1);
        }
        innerGridPane.add(new Label(String.format("%.2f", insurancePremium)),
                fourth, rightInnerRowNum++);

        // deductible of all insurances
        innerGridPane.add(new Label(Loc.c("total_insurance_deductible_sum")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(String.format("%.2f", person.getInsurances()
                .stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .mapToDouble(i -> i.getDeductible())
                .sum())), fourth, rightInnerRowNum++);

        // number of claims
        int claimsActiveCount = (int) person.getClaims().stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .count();
        int claimsTotalCount = (int) person.getClaims().stream()
                .count();
        innerGridPane.add(new Label(Loc.c("number_of_claims")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(String.format("%d (%d)",
                        claimsActiveCount, claimsTotalCount)),
                fourth, rightInnerRowNum++);

        // claims sum
        innerGridPane.add(new Label(Loc.c("total_claims_amount_sum")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(String.format("%.2f", person.getClaims()
                .stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .mapToDouble(i -> i.getAmount())
                .sum())), fourth, rightInnerRowNum++);

        // claims deductible
        innerGridPane.add(new Label(Loc.c("total_claims_deductible_sum")),
                third, rightInnerRowNum);
        innerGridPane.add(new Label(String.format("%.2f", person.getClaims()
                .stream()
                .filter(i -> i.getStatus() == Status.ACTIVE)
                .mapToDouble(i -> i.getDeductible())
                .sum())), fourth, rightInnerRowNum++);


        /* Add all to main gridpane */
        add(innerGridPane, 0, rowNum++);

        // insurances table
        add(new SugarTable(TableController.getInsuranceTable(person.getInsurances().stream())
                        .getTable(), Model.ModelType.INSURANCE, Loc.c("insurances")).getNode(),
                0, rowNum++);

        // claims table
        add(new SugarTable(TableController.getClaimsTable(person.getClaims().stream())
                        .getTable(), Model.ModelType.CLAIM, Loc.c("claims")).getNode(),
                0, rowNum++);
    }

    public StandardGridPane getNode()
    {
        return this;
    }
}
