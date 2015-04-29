package test;

/**
 * Created by alex on 4/16/15.
 */

import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.ConcreteType;
import main.model.insurance.Insurance;
import main.model.insurance.property.House;
import main.model.insurance.travel.Travel;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;
import main.model.person.Person;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class StorageTest
{

    public static final String[] firstnames = {
        "James", "John", "Robert", "Michael", "William",
        "David", "Richard", "Charles", "Joseph", "Thomas",
        "Daniel", "Paul", "Christopher", "Mark", "Paul",
        "Donald", "George", "Kenneth", "Steven", "Brian",
        "Mary", "Linda", "Patricia", "Barbara", "Elizabeth",
        "Jennifer", "Maria", "Susan", "Margaret", "Lisa",
        "Nancy", "Karen", "Betty", "Sandra", "Betty", "Ruth",
        "Laura", "Sarah", "Kimberly", "Jessica", "Shirley"
    };

    public static final String[] lastnames = {
        "Smith", "Johnson", "Williams", "Jones", "Brown",
        "Davis", "Miller", "Moore", "Taylor", "Jackson",
        "Anderson", "White", "Harris", "Martin", "Garcia",
        "Thomas", "Robinson", "Clark", "Rodriguez", "Lewis",
        "Lee", "Walker", "Hall", "Allen", "Hernandez", "King",
        "Wright", "Lopez", "Gonzales", "Nelson", "Perez",
        "Turner", "Phillips", "Collins", "Stewart", "Sanchez",
        "Morris", "Rogers", "Parker", "Evans", "Edwards", "Scott"
    };

    public static final String[] cities = {
        "Bath", "Birmingham", "Bradford", "Brighton & Hove",
        "Bristol", "Cambridge", "Canterbury", "Carlisle",
        "Chelmsford", "Chester", "Coventry", "Derby",
        "Durham", "Ely", "Exeter", "Gloucester", "Hereford",
        "Kingston upon Hull", "Lancaster", "Leeds", "Leicester",
        "Lincoln", "City of London", "Manchester", "Norwich",
        "Nottingham", "Oxford", "Peterborough", "Plymouth",
        "Portsmouth", "Preston", "Ripon", "Salford", "Salisbury",
        "Sheffield", "Southampton", "Sunderland", "Truro", "Wakfield",
        "Wells", "Westminister", "Winchester", "Worcester", "York"
    };

    public static final String[] streetType = {
        "Road", "Street", "Bridge", "Way", "Ground", "Place", "Crossroad"
    };

    /**
     * Simple String and Integers test of Storage class
     */
    @Test
    public void testStorage()
    {
        String[] strings = {"Input 1", "Input 2", "Input 3"};
        Integer[] ints = {2, 3, 5, 7, 11, 13};
        Storage.injectFilename("strings.dat");

        List<String> stringsToFile = new LinkedList<>();
        List<Integer> intsToFile = new LinkedList<>();

        for (String s : strings) { stringsToFile.add(s); }
        for (Integer i : ints) { intsToFile.add(i); }

        Storage.getInstance().put("strings", stringsToFile);
        Storage.getInstance().put("ints", intsToFile);

        /* save and read to/from file */
        try {
            Storage.getInstance().save();
            Storage.getInstance().read();
        } catch (IOException | ClassNotFoundException e) {
            // YOLO!!!
        }

        List<String> stringsFromFile = (List<String>)Storage.getInstance()
                .get("strings");
        List<Integer> intsFromFile = (List<Integer>)Storage.getInstance()
                .get("ints");

        assertEquals(stringsFromFile, stringsToFile);
        assertEquals(intsFromFile, intsToFile);
        assertArrayEquals(stringsFromFile.toArray(), strings);
        assertArrayEquals(intsFromFile.toArray(), ints);
    }

    @Test public void testInsurance()
    {
        Storage.injectFilename("insurances.dat");

        List<Person> persons = new LinkedList<>();
        List<Insurance> insurances = new LinkedList<>();
        List<Claim> claims = new LinkedList<>();

        Person person1 = new Person.Builder("Hans Christian", "Nenseth")
                .dateOfBirth(LocalDate.now())
                .city("Oslo")
                .postalCode("0476")
                .streetAddress("Torshov gate 8c")
                .build();

        Person person2 = new Person.Builder("Hans Petter", "Osvold")
                .dateOfBirth(LocalDate.now())
                .streetAddress("Moldegata 17")
                .postalCode("0445")
                .city("Oslo")
                .build();

        persons.add(person1);
        persons.add(person2);

        Car car1 = new Car.Builder(person1, "AB12345").build();
        Car car2 = new Car.Builder(person1, "AB23456")
                .propulsion(Car.Propulsion.A)
                .type(Car.Type.C)
                .build();

        House house1 = new House.Builder(person1,
                "Pilestredet 100", "0361").build();

        House house2 = new House.Builder(person1,
                "Pilestredet 100", "0361")
                .amount(3000)
                .desc("Nice building")
                .premium(100000)
                .type(House.Type.A)
                .standard(House.Standard.A)
                .material(House.Material.A)
                .build();

        Boat boat1 = new Boat.Builder(person1, "AB1234")
                .build();

        Boat boat2 = new Boat.Builder(person1, "CBD445")
                .horsePower(750)
                .length(3)
                .propulsion(Boat.Propulsion.B)
                .type(Boat.Type.C)
                .amount(50000)
                .desc("super boat")
                .build();

        Boat boat3 = new Boat.Builder(person1, "CBD445")
                .horsePower(750)
                .length(3)
                .propulsion(Boat.Propulsion.B)
                .type(Boat.Type.C)
                .amount(50000)
                .desc("super boat")
                .owner(person2)
                .build();

        Claim claim1 = new Claim.Builder(person1, house1)
                .contacts("WITNESS 1: 986 161 15")
                .date(LocalDate.of(randInt(1985, 2014), randInt(1,12), randInt(1,28)))
                .description("Car damage: total.")
                .type(Claim.Type.A)
                .build();

        claims.add(claim1);

        insurances.add(car1);
        insurances.add(car2);
        insurances.add(house1);
        insurances.add(house2);
        insurances.add(boat1);
        insurances.add(boat2);
        insurances.add(boat3);

        assertTrue(person1.getInsurances().contains(car1));
        assertTrue(person1.getInsurances().contains(car2));
        assertTrue(person1.getInsurances().contains(house1));
        assertTrue(person1.getInsurances().contains(house2));
        assertTrue(person1.getInsurances().contains(boat1));
        assertTrue(person1.getInsurances().contains(boat2));
        assertEquals(boat1.getCustomer(), person1);
        assertEquals(boat2.getCustomer(), person1);
        assertEquals(claim1.getCustomer(), person1);
        assertEquals(claim1.getInsurance(), house1);

        Storage.getInstance().put("persons", persons);
        Storage.getInstance().put("insurances", insurances);
        Storage.getInstance().put("claims", claims);

        /* save and read to/from file */
        try {
            Storage.getInstance().save();
            Storage.getInstance().read();
        } catch (IOException | ClassNotFoundException e) {
            // YOLO!!!
        }

        List<Person> personsFromFile = (List<Person>)Storage.getInstance()
                .get("persons");
        List<Insurance> insurancesFromFile = (List<Insurance>)Storage.getInstance()
                .get("insurances");
        List<Claim> claimsFromFile = (List<Claim>)Storage.getInstance()
                .get("claims");


        // TODO compare/equal the elements

        assertTrue(insurancesFromFile.get(0).identify().equals(ConcreteType.CAR));
        assertTrue(insurancesFromFile.get(1).identify().equals(ConcreteType.CAR));
        assertTrue(insurancesFromFile.get(2).identify().equals(ConcreteType.HOUSE));
        assertTrue(insurancesFromFile.get(3).identify().equals(ConcreteType.HOUSE));
        assertTrue(insurancesFromFile.get(4).identify().equals(ConcreteType.BOAT));
        assertTrue(insurancesFromFile.get(5).identify().equals(ConcreteType.BOAT));

        /*
        assertTrue(personsFromFile.contains(person2));
        assertTrue(insurancesFromFile.contains(car1));
        assertTrue(insurancesFromFile.contains(car2));
        assertTrue(insurancesFromFile.contains(house1));
        assertTrue(insurancesFromFile.contains(house2));
        assertTrue(insurancesFromFile.contains(boat1));
        assertTrue(insurancesFromFile.contains(boat2));
        assertTrue(insurancesFromFile.contains(boat3));
        assertTrue(claimsFromFile.contains(claim1));
        */

    }

    /**
     * Generate a log of data for stress testing the Storage class
     */
    @Test public void massInsertPersons()
    {
        /**
         * 2000:    ~ 3.7MB file
         * 20000:   ~ 37MB file
         * 200000:  ~ 370MB file
         */
        int count = 2000;

        Storage.injectFilename("persons_big.dat");

        List<Person> persons = new LinkedList<>();
        List<Insurance> insurances = new LinkedList<>();
        List<Claim> claims = new LinkedList<>();

        /* generate a lot of persons, with various insurances and claims */
        for (int i = 0; i < count; i++) {
            String city = randomCity();
            String streetName = String.format("%s %s %d",
                    randomCity(), randomStreetType(), randInt(1, 100));

            Person tmp =  new Person.Builder(randomFirstname(), randomLastname())
                    .city(city)
                    .postalCode(String.valueOf(randInt(1000, 9000)))
                    .streetAddress(streetName)
                    .dateOfBirth(LocalDate.of(randInt(1930, 2014),
                            randInt(1,12), randInt(1,28)))
                    .build();

            /* add some random car insurances */
            for (int j = 0; j < randInt(0,10); j++) {
                Car car = new Car.Builder(tmp, "ABC123")
                        .amount(randInt(1000, 4000))
                        .premium(randInt(300, 700))
                        .deductible(randInt(4000, 12000))
                        //.bonus(randInt(50,80))
                        .mileage(randInt(4000, 20000))
                        /*
                        .registrationYear(
                                LocalDate.of(randInt(1980, 2014),
                                        randInt(1,12),
                                        randInt(1,28))
                                )
                                */
                        .type(Car.Type.A)
                        .amount(randInt(4000, 10000))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 5); k++) {
                    claims.add(new Claim.Builder(tmp, car).build());
                }
                insurances.add(car);
            }

            /* add some random boat insurances */
            for (int j = 0; j < randInt(0,10); j++) {
                Boat boat = new Boat.Builder(tmp, "ABC123")
                        .amount(randInt(1000, 4000))
                        .premium(randInt(300, 700))
                        .deductible(randInt(4000, 12000))
                        .horsePower(randInt(20, 400))
                        .length(randInt(10, 50))
                        /*
                        .registrationYear(LocalDate.of(randInt(1940, 2014),
                                randInt(1,12), randInt(1,28)))
                        */
                        .type(Boat.Type.A)
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 5); k++) {
                    claims.add(new Claim.Builder(tmp, boat).build());
                }
                insurances.add(boat);
            }

            /* add some random houses */
            for (int j = 0; j < randInt(0,10); j++) {
                House house = new House.Builder(tmp, streetName, randInt(1000, 9000) + "")
                        .material(House.Material.A)
                        .amount(randInt(1000, 4000))
                        .premium(randInt(300, 700))
                        .deductible(randInt(4000, 12000))
                        .standard(House.Standard.A)
                        .type(House.Type.A)
                        .year(randInt(1900, 2014))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 5); k++) {
                    claims.add(new Claim.Builder(tmp, house).build());
                }
                insurances.add(house);
            }

            /* add some claims */

            /* add some random travel insurances */
            for (int j = 0; j < randInt(0,10); j++) {
                Travel travel = new Travel.Builder(tmp)
                        .amount(randInt(1000, 4000))
                        .premium(randInt(300, 700))
                        .deductible(randInt(4000, 12000))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 5); k++) {
                    claims.add(new Claim.Builder(tmp, travel).build());
                }
                insurances.add(travel);
            }
            persons.add(tmp);
        }

        /* add them all to storage */
        Storage.getInstance().put("persons", persons);
        Storage.getInstance().put("insurances", insurances);
        Storage.getInstance().put("claims", claims);

        /* save and read to/from file */
        try {
            Storage.getInstance().save();
            Storage.getInstance().read();
        } catch (IOException | ClassNotFoundException e) {
            // YOLO!!!
        }
    }

    /**
     * Helper method for outputting random firstname
     * @return
     */
    public String randomFirstname()
    {
        return firstnames[randInt(0, firstnames.length - 1)];
    }

    /**
     * Helper method for outputting random lastname
     * @return
     */
    public String randomLastname()
    {
        return lastnames[randInt(0, lastnames.length - 1)];
    }

    /**
     * Helper method for outputting random city
     * @return
     */
    public String randomCity()
    {
        return cities[randInt(0, cities.length - 1)];
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
     * Helper method for outputting random streetAddress type
     * @return
     */
    public String randomStreetType()
    {
        return streetType[randInt(0, streetType.length - 1)];
    }
}
