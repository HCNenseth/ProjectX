package model;

/**
 * Created by alex on 4/16/15.
 */

import model.insurance.vehicle.Boat;
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

        //assertTrue(person1.getInsurances().contains(boat1));
        //assertTrue(person1.getInsurances().contains(boat2));
        //assertEquals(boat1.getCustomer(), person1);
        //assertEquals(boat2.getCustomer(), person1);


        System.out.println(boat3);
        System.out.println(boat2);
    }
}
