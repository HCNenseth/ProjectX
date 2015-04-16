package model;

/**
 * Created by alex on 4/16/15.
 */

import model.insurance.vehicle.Boat;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
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

        Person person1 = new Person.Builder("Hans Christian", "Nenseth").dateOfBirth(Calendar.getInstance()).city("Oslo").postalCode("0476").street("Torshov gate 8c").build();
        Boat boat1 = new Boat.Builder(person1, "AB1234").build();
        short horsePower = 750;
        short length = 3;
        Boat.Propulsion propulsion = Boat.Propulsion.B;
        Boat.Type type = Boat.Type.C;

        Boat boat2 = new Boat.Builder(person1, "CBD445").horsePower(horsePower).length(length).propulsion(propulsion).type(type).build();
        System.out.println(boat1);
        System.out.println(boat2);
    }
}
