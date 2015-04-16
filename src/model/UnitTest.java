package model;

/**
 * Created by alex on 4/16/15.
 */

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTest {

    @Test public void testStorage()
    {
        Storage.injectFilename("strings.dat");

        List<String> tmp = new LinkedList<>();

        assertTrue(tmp.add("Input 1"));
        assertTrue(tmp.add("Input 2"));
        assertTrue(tmp.add("Input 3"));

        Storage.getInstance().put("Strings", tmp);

        try {
            Storage.getInstance().save();
        } catch (IOException ioe) {
            //
        }
    }
}
