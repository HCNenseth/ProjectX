package main.controller;

import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.table.ClaimTable;
import main.view.table.InsuranceTable;
import main.view.table.PersonTable;

import java.util.stream.Stream;

/**
 * TableController.java
 */
public class TableController
{
    /**
     * Gets person table.
     *
     * @param personStream the person stream
     * @return the person table
     */
    public static PersonTable getPersonTable(Stream<Person> personStream)
    {
        PersonTable personTable = new PersonTable();
        personTable.setOnEditAction(PersonController::edit);
        personTable.setOnViewAction(PersonController::view);
        personTable.setOnDoubleClickAction(PersonController::view);

        personStream.forEach(personTable::insertData);

        return personTable;
    }

    /**
     * Gets insurance table.
     *
     * @param insuranceStream the insurance stream
     * @return the insurance table
     */
    public static InsuranceTable getInsuranceTable(Stream<Insurance> insuranceStream)
    {
        InsuranceTable insuranceTable = new InsuranceTable();
        insuranceTable.setOnEditAction(InsuranceController::edit);
        insuranceTable.setOnViewAction(InsuranceController::view);
        insuranceTable.setOnDoubleClickAction(InsuranceController::view);

        insuranceStream.forEach(insuranceTable::insertData);

        return insuranceTable;
    }

    /**
     * Gets claims table.
     *
     * @param claimStream the claim stream
     * @return the claims table
     */
    public static ClaimTable getClaimsTable(Stream<Claim> claimStream)
    {
        ClaimTable claimTable = new ClaimTable();
        claimTable.setOnEditAction(ClaimController::edit);
        claimTable.setOnViewAction(ClaimController::view);
        claimTable.setOnDoubleClickAction(ClaimController::view);

        claimStream.forEach(claimTable::insertData);

        return claimTable;
    }
}
