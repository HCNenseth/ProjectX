package test;

import main.validator.StringMatcher;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ValidatorTest.java
 * The type Validator test.
 */
public class ValidatorTest
{
    /**
     * Test _ email.
     */
    @Test
    public void test_email()
    {
        assertTrue(StringMatcher.email("test@example.com"));
        assertTrue(StringMatcher.email("test.best@example.com"));
        assertTrue(StringMatcher.email("test.best@love.example.com"));

        assertFalse(StringMatcher.email("user@example"));
        assertFalse(StringMatcher.email("user.false@example"));
        assertFalse(StringMatcher.email("@example.com"));
        assertFalse(StringMatcher.email("@example"));
        assertFalse(StringMatcher.email("horse@"));
    }

    /**
     * Test _ firstname.
     */
    @Test
    public void test_firstname()
    {
        assertTrue(StringMatcher.firstname("John"));
        assertTrue(StringMatcher.firstname("John Carl"));
        assertTrue(StringMatcher.firstname("John-Carl"));

        // Some special chars
        assertTrue(StringMatcher.firstname("Jørn"));
    }

    /**
     * Test _ lastname.
     */
    @Test
    public void test_lastname()
    {
        assertTrue(StringMatcher.lastname("Doe"));
        assertTrue(StringMatcher.lastname("Doe Smith"));
        assertTrue(StringMatcher.lastname("Doe-Smith"));

        // Some special chars
        assertTrue(StringMatcher.lastname("Jørnson"));
    }

    /**
     * Test _ regnr.
     */
    @Test
    public void test_regnr()
    {
        assertTrue(StringMatcher.regnr("ABC123"));
    }

    /**
     * Test _ type.
     */
    @Test
    public void test_type()
    {
        assertTrue(StringMatcher.type("Sailboat"));
    }

    /**
     * Test _ color.
     */
    @Test
    public void test_color()
    {
        assertTrue(StringMatcher.color("Purple"));
        assertTrue(StringMatcher.color("Purplish yellow"));

        // Some special chars
        assertTrue(StringMatcher.color("Rød"));
        assertTrue(StringMatcher.color("Rød grønn"));
    }

    /**
     * Test _ year.
     */
    @Test
    public void test_year()
    {
        assertTrue(StringMatcher.year("1999"));

        assertFalse(StringMatcher.year("20O0"));
        assertFalse(StringMatcher.year("100"));
        assertFalse(StringMatcher.year("10000"));
        assertFalse(StringMatcher.year("Horse"));
    }

    /**
     * Test _ length.
     */
    @Test
    public void test_length()
    {
        assertTrue(StringMatcher.length("32.2"));
        assertTrue(StringMatcher.length("32"));

        assertFalse(StringMatcher.length("32.21.1"));
        assertFalse(StringMatcher.length("Horse"));
        assertFalse(StringMatcher.length("32 M"));
        assertFalse(StringMatcher.length("32 F"));
    }

    /**
     * Test _ power.
     */
    @Test
    public void test_power()
    {
        assertTrue(StringMatcher.power("144.5"));
        assertTrue(StringMatcher.power("144"));

        assertFalse(StringMatcher.power("133.2.3"));
        assertFalse(StringMatcher.power("Apple"));

        assertFalse(StringMatcher.power("150 HK"));
        assertFalse(StringMatcher.power("150 HP"));
    }
}
