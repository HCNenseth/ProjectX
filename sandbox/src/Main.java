/**
 * Example usage of Lambdas and Stream on a fairly big data set.
 */
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public Main()
    {
        List<Customer> customers = new ArrayList<>();

        int count = 800000;
        int yearLow = 1920;
        int yearHigh = 1995;

        // Generate random customers
        System.out.println("=> Generating " + count + " customers...\n");
        for (int i = 0; i < count; i++) {
            Customer tmp = new Customer();
            tmp.setFirstname(randomFirstname());
            tmp.setLastname(randomLastname());
            tmp.setBirthdate(randomDate(yearLow, yearHigh));
            tmp.setCity(randomCity());
            String randomStreetName = String.format("%s %s %d",
                    randomCity(), randomStreetType(), randInt(1, 100));
            tmp.setAddress(randomStreetName);
            customers.add(tmp);
        }

        // Lambdas
        System.out.println("=> Printing using lambdas");
        printPersons(customers, c -> c.getAge() >= 45
                        && c.getAge() <= 55
                        && c.getLastname().equals("Moore")
                        && c.getFirstname().contains("ar")
                        && c.getAddress().contains("Road")
                        && (c.getCity().equals("Bristol") || c.getCity().equals("Leeds"))
        );

        // Stream API
        System.out.println("=> Printing using streams");
        printPersons(customers.stream().filter(c -> c.getAge() >= 45
                        && c.getAge() <= 55
                        && c.getLastname().equals("Moore")
                        && c.getFirstname().contains("ar")
                        && c.getAddress().contains("Road")
                        && (c.getCity().equals("Bristol") || c.getCity().equals("Leeds"))
                    ).iterator()
        );

        // An even cheekier version of the above code.
        // Notice the absent of external method call
        System.out.println("=> Printing using streams (tight version)");
        customers.stream().filter(c -> c.getAge() >= 45
                        && c.getAge() <= 55
                        && c.getLastname().equals("Moore")
                        && c.getFirstname().contains("ar")
                        && c.getAddress().contains("Road")
                        && (c.getCity().equals("Bristol") || c.getCity().equals("Leeds"))
        ).forEach(System.out::println);

        // Steam - Save as separate list
        System.out.println("=> Printing using filtered list object");
        List<Customer> filteredCustomers = customers.stream().filter(c -> c.getAge() >= 45
                        && c.getAge() <= 55
                        && c.getLastname().equals("Moore")
                        && c.getFirstname().contains("ar")
                        && c.getAddress().contains("Road")
                        && (c.getCity().equals("Bristol") || c.getCity().equals("Leeds"))
        ).collect(Collectors.toList());
        for (Customer c : filteredCustomers)
            System.out.println(c);
    }

    /**
     * The lambda way
     * @param roster
     * @param tester
     */
    public void printPersons(List<Customer> roster, Predicate<Customer> tester)
    {
        for (Customer c : roster) {
            if (tester.test(c)) {
                System.out.println(c);
            }
        }
    }

    /**
     * The Stream way
     * @param iterator
     */
    public void printPersons(Iterator<Customer> iterator)
    {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Helper method for outputting random firstname
     * @return
     */
    public String randomFirstname()
    {
        return DataBank.firstnames[randInt(0, DataBank.firstnames.length - 1)];
    }

    /**
     * Helper method for outputting random lastname
     * @return
     */
    public String randomLastname()
    {
        return DataBank.lastnames[randInt(0, DataBank.lastnames.length - 1)];
    }

    /**
     * Helper method for outputting random city
     * @return
     */
    public String randomCity()
    {
        return DataBank.cities[randInt(0, DataBank.cities.length - 1)];
    }

    /**
     * Helper method for outputting random street type
     * @return
     */
    public String randomStreetType()
    {
        return DataBank.streetType[randInt(0, DataBank.streetType.length - 1)];
    }

    /**
     * Helper method for generating random date in range
     * @param low
     * @param high
     * @return
     */
    public Date randomDate(int low, int high)
    {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randInt(low, high);
        int day = randInt(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.DAY_OF_YEAR, day);

        return gc.getTime();
    }

    /**
     * Helper method for generating random numbers in range
     * @param min
     * @param max
     * @return
     */
    public int randInt(int min, int max)
    {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args)
    {
        new Main();
    }
}
