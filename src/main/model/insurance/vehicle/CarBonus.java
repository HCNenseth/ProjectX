package main.model.insurance.vehicle;

import main.model.Status;
import main.model.claim.vehicle.CarClaim;

import java.time.LocalDate;
import java.util.List;

/**
 * CarBonus.java
 *
 * Static protected class for helping car insurances to
 * calculate correct bonus
 */
public class CarBonus
{
    private static final int daysInYear = 365;

    public enum Levels
    {
        L1(45, daysInYear * 1),
        L2(55, daysInYear * 2),
        L3(65, daysInYear * 3),
        L4(70, daysInYear * 4),
        L5(80, daysInYear * 5);

        int bonus, days;

        Levels(int bonus, int days)
        {
            this.bonus = bonus;
            this.days = days;
        }

        public static Levels getLevel(int days)
        {
            for (Levels l : Levels.values()) {
                if (days <= l.days) { return l; }
            }

            // fall through catch
            return L5;
        }

        public int getBonus() { return bonus; }

        public int getDays(Levels l) { return l.days; }

    }


    /**
     * Requirements: Take car insurance and extract all claim connected.
     * Check the last claim date (if any) and compare this date against
     * the insurance date (car insurance). Take the insurance date day count
     * (from current day) and subtract the claim date (again: if any).
     * Insert this number into the above enum, and let the enum spit out
     * correct bonus level...
     * @param car
     * @return
     */
    static int getBonus(Car car)
    {
        List<CarClaim> claims = car.getClaims();

        int days = Math.abs(car.getDate().compareTo(LocalDate.now())) * daysInYear;

        if (claims.size() == 0
                || claims.stream().filter(i -> i.getStatus() == Status.ACTIVE).count() == 0)
        {
            return Levels.getLevel(days).bonus;
        }

        CarClaim newest = claims.stream()
                .filter(c -> c.getStatus() == Status.ACTIVE)
                .sorted((c, e) -> e.getDateOfDamages().compareTo(LocalDate.now()))
                .findFirst().get();

        days = (Math.abs(newest.getDateOfDamages().compareTo(LocalDate.now())) * daysInYear);

        return Levels.getLevel(days).bonus;
    }
}
