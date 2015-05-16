package test;

import main.config.Config;
import main.model.Status;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.claim.property.PropertyClaim;
import main.model.claim.travel.TravelClaim;
import main.model.claim.vehicle.BoatClaim;
import main.model.claim.vehicle.CarClaim;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceType;
import main.model.insurance.property.House;
import main.model.insurance.property.VacationHouse;
import main.model.insurance.travel.Travel;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;
import main.model.person.Person;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * StorageTest.java
 *
 * The type Storage test.
 */
public class StorageTest
{

    /**
     * The constant firstnames.
     */
    public static final String[] firstnames = {
            "Jonas", "Mathias", "Mattis", "Alexander", "Hans",
            "Petter", "Hans-Petter", "Hans Christian", "Andreas",
            "Elias", "Kristian", "Christian", "Sebastian", "Stian",
            "Sander", "Markus", "Tobias", "Anders", "Lara", "Thea",
            "Emma", "Sara", "Julie", "Ida", "Camilla", "Hanna",
            "Nora", "Ingrid", "Emilie", "Amalie", "Hanne", "Linda",
            "Katja", "Berit", "Kristin", "Christina", "Stine"
    };

    /**
     * The constant lastnames.
     */
    public static final String[] lastnames = {
            "Olsen", "Hansen", "Johnsen", "Johansen", "Larsen",
            "Andreasen", "Pedersen", "Nilsen", "Kristiansen", "Jensen",
            "Karlsen", "Pettersen", "Eriksen", "Berg", "Haugen", "Hagen",
            "Dahl", "Halvorsen", "Lund", "Jakobsen", "Moen", "Gundersen",
            "Iversen", "Strand", "Solberg", "Paulsen", "Knutsen", "Eide",
            "Lie", "Lunde", "Moe", "Fredriksen", "Bakken", "Amundsen",
            "Solheim", "Aas", "Myhre", "Ali", "Sandvik", "Vik", "Sandvik"
    };

    /**
     * The constant cities.
     */
    public static final String[] cities = {
            "Alta", "Arendal", "Askim", "Bergen", "Brekstad", "Brevik",
            "Brumunddal", "Bryne", "Drammen", "Egersund", "Elverum",
            "Fagernes", "Fauske", "Flekkefjord", "Fredrikstad", "Grimstad",
            "Halden", "Hamar", "Hammerfest", "Horten", "Kirkenes", "Kongsberg",
            "Kopervik", "Kristiansand", "Kristiansund", "Larvik", "Molde",
            "Moss", "Mysen", "Namsos", "Narvik", "Notodden", "Odda", "Orkanger",
            "Oslo", "Rjukan", "Sandefjord", "Sandnes", "Sandvika", "Ski",
            "Skien", "Sortland", "Stavern", "Stord", "Svelvik", "Trondheim",
            "Ulsteinvik", "Vinstra"
    };

    /**
     * The constant domains.
     */
    public static final String[] domains = {
            "email", "yahoo", "google", "hotmail", "outlook",
            "generell-skade", "vannskade", "kollisjon", "steinras",
            "storm", "kuvelting", "natur-katastrofe", "vulkan"
    };

    /**
     * The constant streetType.
     */
    public static final String[] streetType = {
            "Vei", "Gate", "Bru", "Sted", "Plass", "Krysset"
    };

    /**
     * Simple String and Integers test of Storage class
     */
    @Test
    public void testStorage()
    {
        String[] strings = {"Input 1", "Input 2", "Input 3"};
        Integer[] ints = {2, 3, 5, 7, 11, 13};
        Storage.injectFilename("data/strings.dat");

        List<String> stringsToFile = new LinkedList<>();
        List<Integer> intsToFile = new LinkedList<>();

        for (String s : strings) {
            stringsToFile.add(s);
        }
        for (Integer i : ints) {
            intsToFile.add(i);
        }

        Storage.getInstance().put("strings", stringsToFile);
        Storage.getInstance().put("ints", intsToFile);

        /* save and read to/from file */
        try {
            Storage.getInstance().save();
            Storage.getInstance().read();
        } catch (IOException | ClassNotFoundException e) {
            // YOLO!!!
        }

        List<String> stringsFromFile = (List<String>) Storage.getInstance()
                .get("strings");
        List<Integer> intsFromFile = (List<Integer>) Storage.getInstance()
                .get("ints");

        assertEquals(stringsFromFile, stringsToFile);
        assertEquals(intsFromFile, intsToFile);
        assertArrayEquals(stringsFromFile.toArray(), strings);
        assertArrayEquals(intsFromFile.toArray(), ints);
    }

    /**
     * Test insurance.
     */
    @Test
    public void testInsurance()
    {
        Storage.injectFilename("data/insurances.dat");

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
                .owner("some owner")
                .build();

        Claim claim1 = new PropertyClaim.Builder(person1, house1)
                .contacts("WITNESS 1: 986 161 15")
                .dateOfDamages(LocalDate.of(randInt(1985, 2014), randInt(1, 12), randInt(1, 28)))
                .desc("Car damage: total.")
                .type(PropertyClaim.Type.A)
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

        List<Person> personsFromFile = Person.getPersons();
        List<Insurance> insurancesFromFile = Insurance.getInsurances();
        List<Claim> claimsFromFile = Claim.getClaims();


        assertTrue(insurancesFromFile.get(0).identify().equals(InsuranceType.CAR));
        assertTrue(insurancesFromFile.get(1).identify().equals(InsuranceType.CAR));
        assertTrue(insurancesFromFile.get(2).identify().equals(InsuranceType.HOUSE));
        assertTrue(insurancesFromFile.get(3).identify().equals(InsuranceType.HOUSE));
        assertTrue(insurancesFromFile.get(4).identify().equals(InsuranceType.BOAT));
        assertTrue(insurancesFromFile.get(5).identify().equals(InsuranceType.BOAT));

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
    @Test
    public void massInsertPersons()
    {
        /**
         * 2000:    ~ 3.7MB file
         * 20000:   ~ 37MB file
         * 200000:  ~ 370MB file
         */
        int count = 2000;

        Storage.injectFilename("data/demodata.dat");

        List<Person> persons = new LinkedList<>();
        List<Insurance> insurances = new LinkedList<>();
        List<Claim> claims = new LinkedList<>();

        /* generate a lot of persons, with various insurances and claims */
        for (int i = 0; i < count; i++) {
            String city = randomCity();
            String streetName = String.format("%s %s %d",
                    randomCity(), randomStreetType(), randInt(1, 100));

            String firstname = randomFirstname();
            String lastname = randomLastname();
            String email = String.format("%s.%s@%s.com",
                    firstname, lastname, randomDomain());

            Person person = new Person.Builder(firstname, lastname)
                    .city(city)
                    .postalCode(String.valueOf(randInt(1000, 9000)))
                    .streetAddress(streetName)
                    .email(email)
                    .status(randomEnumValue(Status.class))
                    .phoneNumber(Integer.toString(randInt(20000000, 70000000)))
                    .dateOfBirth(LocalDate.of(randInt(1930, 2000),
                            randInt(1, 12), randInt(1, 28)))
                    .build();

            /* add some random car insurances */
            for (int j = 0; j < randInt(0, 2); j++) {
                Car car = new Car.Builder(person, "ABC123")
                        .date(LocalDate.of(randInt(1970, 2015),
                                randInt(1, 12), randInt(1, 28)))
                        .amount(randInt(1000, 4000))
                        .premium(randInt(5000, 15000))
                        .deductible(randInt(4000, 12000))
                                //.bonus(randInt(50,80))
                        .mileage(randInt(4000, 20000))
                        .registration(
                                LocalDate.of(randInt(1980, 2014),
                                        randInt(1, 12),
                                        randInt(1, 28))
                        )
                        .type(randomEnumValue(Car.Type.class))
                        .status(randomEnumValue(Status.class))
                        .amount(randInt(4000, 10000))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 2); k++) {
                    claims.add(new CarClaim.Builder(person, car)
                            .dateOfDamages(LocalDate.of(randInt(1960, 2014),
                                    randInt(1, 12), randInt(1, 28)))
                            .desc(randomDomain())
                            .deductible(randInt(5000, 10000))
                            .contacts(randomDomain())
                            .amount(randInt(10000, 20000))
                            .status(randomEnumValue(Status.class))
                            .type(randomEnumValue(CarClaim.Type.class))
                            .build());
                }
                insurances.add(car);
            }

            /* add some random boat insurances */
            for (int j = 0; j < randInt(0, 2); j++) {
                Boat boat = new Boat.Builder(person, "ABC123")
                        .date(LocalDate.of(randInt(1970, 2015),
                                randInt(1, 12), randInt(1, 28)))
                        .amount(randInt(1000, 4000))
                        .premium(randInt(5000, 20000))
                        .deductible(randInt(4000, 12000))
                        .horsePower(randInt(20, 400))
                        .length(randInt(10, 50))
                        .registration(LocalDate.of(randInt(1940, 2014),
                                randInt(1, 12), randInt(1, 28)))
                        .status(randomEnumValue(Status.class))
                        .type(randomEnumValue(Boat.Type.class))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 2); k++) {
                    claims.add(new BoatClaim.Builder(person, boat)
                            .dateOfDamages(LocalDate.of(randInt(1960, 2014),
                                    randInt(1, 12), randInt(1, 28)))
                            .desc(randomDomain())
                            .deductible(randInt(5000, 10000))
                            .contacts(randomDomain())
                            .type(randomEnumValue(BoatClaim.Type.class))
                            .status(randomEnumValue(Status.class))
                            .amount(randInt(10000, 20000))
                            .build());
                }
                insurances.add(boat);
            }

            /* add some random houses */
            for (int j = 0; j < randInt(0, 2); j++) {
                House house = new House.Builder(person, streetName, randInt(1000, 9000) + "")
                        .material(randomEnumValue(House.Material.class))
                        .standard(randomEnumValue(House.Standard.class))
                        .date(LocalDate.of(randInt(1970, 2015),
                                randInt(1, 12), randInt(1, 28)))
                        .type(randomEnumValue(House.Type.class))
                        .city(city)
                        .postalCode(String.valueOf(randInt(1000, 9000)))
                        .streetAddress(streetName)
                        .amount(randInt(1000, 4000))
                        .premium(randInt(5000, 10000))
                        .deductible(randInt(4000, 12000))
                        .status(randomEnumValue(Status.class))
                        .year(randInt(1900, 2014))
                        .squareMeter(randInt(30, 200))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 2); k++) {
                    claims.add(new PropertyClaim.Builder(person, house)
                            .dateOfDamages(LocalDate.of(randInt(1960, 2014),
                                    randInt(1, 12), randInt(1, 28)))
                            .desc(randomDomain())
                            .deductible(randInt(5000, 10000))
                            .contacts(randomDomain())
                            .amount(randInt(10000, 20000))
                            .status(randomEnumValue(Status.class))
                            .build());
                }
                insurances.add(house);
            }

            for (int j = 0; j < randInt(0, 2); j++) {
                VacationHouse vacationHouse = new VacationHouse.Builder(person,
                        streetName, randInt(1000, 9000) + "")
                        .material(randomEnumValue(VacationHouse.Material.class))
                        .standard(randomEnumValue(VacationHouse.Standard.class))
                        .type(randomEnumValue(VacationHouse.Type.class))
                        .amount(randInt(1000, 4000))
                        .premium(randInt(2000, 6000))
                        .city(city)
                        .postalCode(String.valueOf(randInt(1000, 9000)))
                        .streetAddress(streetName)
                        .date(LocalDate.of(randInt(1970, 2015),
                                randInt(1, 12), randInt(1, 28)))
                        .deductible(randInt(4000, 12000))
                        .status(randomEnumValue(Status.class))
                        .year(randInt(1900, 2014))
                        .squareMeter(randInt(30, 200))
                        .build();
                for (int k = 0; k < randInt(0, 2); k++) {
                    claims.add(new PropertyClaim.Builder(person, vacationHouse)
                            .dateOfDamages(LocalDate.of(randInt(1960, 2014),
                                    randInt(1, 12), randInt(1, 28)))
                            .desc(randomDomain())
                            .deductible(randInt(5000, 10000))
                            .contacts(randomDomain())
                            .amount(randInt(10000, 20000))
                            .status(randomEnumValue(Status.class))
                            .build());
                }
                insurances.add(vacationHouse);
            }

            /* add some random travel insurances */
            for (int j = 0; j < randInt(0, 2); j++) {
                Travel travel = new Travel.Builder(person)
                        .amount(randInt(1000, 4000))
                        .continent(randomEnumValue(Travel.Continent.class))
                        .date(LocalDate.of(randInt(1970, 2015),
                                randInt(1, 12), randInt(1, 28)))
                        .premium(randInt(1000, 3000))
                        .deductible(randInt(4000, 12000))
                        .build();
                /* add some random claims */
                for (int k = 0; k < randInt(0, 2); k++) {
                    claims.add(new TravelClaim.Builder(person, travel)
                            .dateOfDamages(LocalDate.of(randInt(1960, 2014),
                                    randInt(1, 12), randInt(1, 28)))
                            .desc(randomDomain())
                            .status(randomEnumValue(Status.class))
                            .deductible(randInt(5000, 10000))
                            .contacts(randomDomain())
                            .amount(randInt(10000, 20000))
                            .build());
                }
                insurances.add(travel);
            }
            persons.add(person);
        }

        /* add them all to storage */
        Storage.getInstance().put(Config.PERSONS, persons);
        Storage.getInstance().put(Config.INSURANCES, insurances);
        Storage.getInstance().put(Config.CLAIMS, claims);

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
     *
     * @return string
     */
    public String randomFirstname()
    {
        return firstnames[randInt(0, firstnames.length - 1)];
    }

    /**
     * Helper method for outputting random lastname
     *
     * @return string
     */
    public String randomLastname()
    {
        return lastnames[randInt(0, lastnames.length - 1)];
    }

    /**
     * Helper method for outputting random city
     *
     * @return string
     */
    public String randomCity()
    {
        return cities[randInt(0, cities.length - 1)];
    }

    /**
     * Helper method for outputting random streetAddress type
     *
     * @return string
     */
    public String randomStreetType()
    {
        return streetType[randInt(0, streetType.length - 1)];
    }

    /**
     * Random domain name.
     *
     * @return string
     */
    public String randomDomain()
    {
        return domains[randInt(0, domains.length - 1)];
    }


    /**
     * Insert generic enum, return random value!
     *
     * @param <E>  the type parameter
     * @param enumClass the enum class
     * @return e
     */
    public <E extends Enum> E randomEnumValue(Class<E> enumClass)
    {
        E[] values = enumClass.getEnumConstants();
        return values[randInt(0, values.length - 1)];
    }

    /**
     * Helper method for generating random numbers in range
     *
     * @param min the min
     * @param max the max
     * @return int
     */
    public int randInt(int min, int max)
    {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
