package main.model.insurance.vehicle;

import main.model.Status;
import main.model.claim.vehicle.CarClaim;

import java.time.LocalDate;
import java.util.List;

/**
 * Static protected class for helping car insurances to
 * calculate correct bonus
 */
class CarBonus
{
    private enum Levels
    {
        L45(45, 365*1),
        L55(55, 365*2),
        L65(65, 365*3),
        L70(70, 365*4),
        L80(80, 365*5);

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

            // important fall through
            return L80;
        }
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
    // TODO - fix this broken algorithm!
    static int getBonus(Car car)
    {
        List<CarClaim> claims = car.getClaims();

        int days = (car.getDate().compareTo(LocalDate.now()));

        if (claims.size() == 0
                || claims.stream().filter(i -> i.getStatus() == Status.ACTIVE).count() == 0)
        {
            return Levels.getLevel(days).bonus;
        }

        CarClaim newest = claims.stream()
                .filter(c -> c.getStatus() == Status.ACTIVE)
                .sorted((c, e) -> e.getDateOfDamages().compareTo(LocalDate.now()))
                .findFirst().get();

        days -= (newest.getDateOfDamages().compareTo(LocalDate.now()));

        return Levels.getLevel(days).bonus;
    }
}
