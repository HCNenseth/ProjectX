import java.util.*;

public class Main {

    public Main()
    {
        List<Customer> customers = new ArrayList<>();

        int count = 4000000;

        System.out.println("=> Generating "+ count +" customers...\n");
        for (int i = 0; i < count; i++) {
            Customer tmp = new Customer();
            tmp.setFirstname(randomFirstname());
            tmp.setLastname(randomLastname());
            tmp.setBirthdate(randomDate());
            tmp.setCity(randomCity());
            String randomStreetName = String.format("%s %s %d",
                    randomCity(), randomStreetType(), randInt(1, 100));
            tmp.setAddress(randomStreetName);
            customers.add(tmp);
        }

        // Lambdas FTW!
        printPersons(customers,
                c -> c.getAge() >= 45
                        && c.getAge() <= 55
                        && c.getLastname() == "Moore"
                        && c.getAddress().contains("Road")
                        && (c.getCity() == "Bristol" || c.getCity() == "Leeds")
        );
    }

    public void printPersons(List<Customer> roster, Predicate<Customer> tester)
    {
        for (Customer c : roster) {
            if (tester.test(c)) {
                System.out.println(c);
            }
        }
    }

    public String randomFirstname()
    {
        return DataBank.firstnames[randInt(0, DataBank.firstnames.length - 1)];
    }

    public String randomLastname()
    {
        return DataBank.lastnames[randInt(0, DataBank.lastnames.length - 1)];
    }

    public String randomCity()
    {
        return DataBank.cities[randInt(0, DataBank.cities.length - 1)];
    }

    public String randomStreetType()
    {
        return DataBank.streetType[randInt(0, DataBank.streetType.length - 1)];
    }

    public Date randomDate()
    {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randInt(1920, 1998);
        int day = randInt(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.DAY_OF_YEAR, day);

        return gc.getTime();
    }

    public int randInt(int min, int max)
    {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
