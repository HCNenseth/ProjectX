package test;

import main.model.Status;
import main.model.claim.vehicle.CarClaim;
import main.model.insurance.vehicle.Car;
import main.model.insurance.vehicle.CarBonus;
import main.model.person.Person;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CarBonusTest.java
 *
 * The type Car bonus test.
 */
public class CarBonusTest
{
    /**
     * Main test.
     */
    @Test
    public void mainTest()
    {
        LocalDate now = LocalDate.now();
        Person p = new Person.Builder("John", "Doe").build();

        Car c1 = new Car.Builder(p, "AB1000")
                .build();
        assertTrue(c1.getBonus() == CarBonus.Levels.L1.getBonus());

        Car c2 = new Car.Builder(p, "AB1000")
                .date(LocalDate.of(now.getYear() - 2, 1, 1))
                .build();
        assertTrue(c2.getBonus() == CarBonus.Levels.L2.getBonus());

        Car c3 = new Car.Builder(p, "AB1000")
                .date(LocalDate.of(now.getYear() - 3, 1, 1))
                .build();
        assertTrue(c3.getBonus() == CarBonus.Levels.L3.getBonus());

        Car c4 = new Car.Builder(p, "AB1000")
                .date(LocalDate.of(now.getYear() - 4, 1, 1))
                .build();
        assertTrue(c4.getBonus() == CarBonus.Levels.L4.getBonus());

        Car c5 = new Car.Builder(p, "AB1000")
                .date(LocalDate.of(now.getYear() - 5, 1, 1))
                .build();
        assertTrue(c5.getBonus() == CarBonus.Levels.L5.getBonus());

        Car c6 = new Car.Builder(p, "AB1000")
                .date(LocalDate.of(now.getYear() - 15, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L5.getBonus());


        // crash c5
        new CarClaim.Builder(p, c5)
                .status(Status.ACTIVE)
                .build();

        assertFalse(c5.getBonus() == CarBonus.Levels.L5.getBonus());
        assertTrue(c5.getBonus() == CarBonus.Levels.L1.getBonus());

        // crash c4
        new CarClaim.Builder(p, c4)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(
                        now.getYear() - 2, 1, 1
                ))
                .build();
        assertFalse(c4.getBonus() == CarBonus.Levels.L4.getBonus());
        assertTrue(c4.getBonus() == CarBonus.Levels.L2.getBonus());

        // crash c6
        new CarClaim.Builder(p, c6).status(Status.INACTIVE).build();
        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 10, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L5.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 5, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L5.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 4, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L4.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.INACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 3, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L4.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 3, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L3.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 2, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L2.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.of(now.getYear() - 1, 1, 1))
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L1.getBonus());

        new CarClaim.Builder(p, c6)
                .status(Status.ACTIVE)
                .dateOfDamages(LocalDate.now())
                .build();
        assertTrue(c6.getBonus() == CarBonus.Levels.L1.getBonus());
    }
}
