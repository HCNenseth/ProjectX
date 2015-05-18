package main.model.insurance.vehicle;

import main.model.Status;
import main.model.claim.vehicle.CarClaim;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * CarBonus.java
 * <p>
 * Static protected class for helping car insurances to
 * calculate correct bonus
 */
public class CarBonus
{
    private static final int daysInYear = 365;

    public enum Levels
    {
        L1(45, daysInYear),
        L2(55, daysInYear * 2),
        L3(65, daysInYear * 3),
        L4(75, daysInYear * 4);

        final int bonus, days;

        /**
         * Instantiates a new Levels.
         *
         * @param bonus the bonus
         * @param days the days
         */
        Levels(int bonus, int days)
        {
            this.bonus = bonus;
            this.days = days;
        }

        /**
         * Gets level.
         *
         * @param days the days
         * @return the level
         */
        public static Levels getLevel(int days)
        {
            for (Levels l : Levels.values()) {
                if (days <= l.days) {
                    return l;
                }
            }

            // fall through catch
            return L4;
        }

        /**
         * Gets bonus.
         *
         * @return the bonus
         */
        public int getBonus()
        {
            return bonus;
        }

        /**
         * Gets days.
         *
         * @param l the l
         * @return the days
         */
        public int getDays(Levels l)
        {
            return l.days;
        }

    }


    /**
     * Requirements: Take car insurance and extract all claim connected.
     * Check the last claim date (if any) and compare this date against
     * the insurance date (car insurance). Take the insurance date day count
     * (from current day) and subtract the claim date (again: if any).
     * Insert this number into the above enum, and let the enum spit out
     * correct bonus level...
     *
     * @param car the car
     * @return bonus
     */
    static int getBonus(Car car)
    {
        List<CarClaim> claims = car.getClaims();

        int days = Math.abs(car.getDate().compareTo(LocalDate.now())) * daysInYear;

        if (claims.size() == 0
                || claims.stream().filter(i -> i.getStatus() == Status.ACTIVE).count() == 0) {
            return Levels.getLevel(days).bonus;
        }

        CarClaim newest = claims.stream()
                .filter(c -> c.getStatus() == Status.ACTIVE)
                .sorted(Collections.reverseOrder(Comparator.comparing(CarClaim::getDateOfDamages)))
                .findFirst().get();

        days = (Math.abs(newest.getDateOfDamages().compareTo(LocalDate.now())) * daysInYear);

        return Levels.getLevel(days).bonus;
    }
}
