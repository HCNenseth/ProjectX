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

    static int getBonus(Car car)
    {
        List<CarClaim> claims = (List<CarClaim>)car.getClaims();

        if (claims.size() == 0) { return -1; }

        CarClaim newest = claims.stream()
                .filter(c -> c.getStatus() == Status.ACTIVE)
                .sorted((c, e) -> e.getClaimDate().compareTo(LocalDate.now()))
                .findFirst()
                .get();

        return Levels.getLevel(newest.getClaimDate().compareTo(LocalDate.now())).bonus;
    }
}
