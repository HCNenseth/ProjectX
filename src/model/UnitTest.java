package model;

/**
 * Created by alex on 4/16/15.
 */

import model.insurance.Insurance;
import model.insurance.property.House;
import model.insurance.vehicle.Boat;
import model.insurance.vehicle.Car;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTest {

    @Test public void testStorage()
    {
        String[] strings = {"Input 1", "Input 2", "Input 3"};
        String key = "Strings";
        Storage.injectFilename("strings.dat");

        List<String> listToFile = new LinkedList<>();

        for (String s : strings) {
            listToFile.add(s);
        }
        Storage.getInstance().put(key, listToFile);

        /* save and read to/from file */
        try {
            Storage.getInstance().save();
            Storage.getInstance().read();
        } catch (IOException | ClassNotFoundException e) {
            // YOLO!!!
        }

        List<String> listFromFile = (List<String>)Storage.getInstance()
                .get(key);

        assertArrayEquals(listFromFile.toArray(), strings);
    }

    @Test public void testInsurance()
    {
        Storage.injectFilename("insurances.dat");

        List<Person> persons = new LinkedList<>();
        List<Insurance> insurances = new LinkedList<>();

        Person person1 = new Person.Builder("Hans Christian", "Nenseth")
                .dateOfBirth(Calendar.getInstance())
                .city("Oslo")
                .postalCode("0476")
                .street("Torshov gate 8c")
                .build();

        Person person2 = new Person.Builder("Hans Petter", "Osvold")
                .dateOfBirth(Calendar.getInstance())
                .street("Moldegata 17")
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

        Storage.getInstance().put("persons", persons);
        Storage.getInstance().put("insurances", insurances);

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

        assertTrue(personsFromFile.contains(person1));
        assertTrue(personsFromFile.contains(person2));
        assertTrue(insurancesFromFile.contains(car1));
        assertTrue(insurancesFromFile.contains(car2));
        assertTrue(insurancesFromFile.contains(house1));
        assertTrue(insurancesFromFile.contains(house2));
        assertTrue(insurancesFromFile.contains(boat1));
        assertTrue(insurancesFromFile.contains(boat2));
        assertTrue(insurancesFromFile.contains(boat3));

        //System.out.println(boat3);
        //System.out.println(boat2);
    }
}
