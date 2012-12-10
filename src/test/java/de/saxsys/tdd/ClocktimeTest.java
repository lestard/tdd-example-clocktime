package de.saxsys.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClocktimeTest {

    @Test
    public void testDefaultConstructor() {
        Clocktime time = new Clocktime();

        assertEquals(0, time.seconds());
        assertEquals(0, time.minutes());
        assertEquals(0, time.hours());
    }

    @Test
    public void testConstructorOneParam() {
        int hours = 3;
        Clocktime time = new Clocktime(hours);

        assertEquals(hours, time.hours());
    }

    @Test
    public void testConstructorTwoParams() {
        int hours = 3;
        int minutes = 45;

        Clocktime time = new Clocktime(hours, minutes);

        assertEquals(hours, time.hours());
        assertEquals(minutes, time.minutes());
    }

    @Test
    public void testConstructorThreeParams() {
        int hours = 3;
        int minutes = 45;
        int seconds = 13;

        Clocktime time = new Clocktime(hours, minutes, seconds);
        assertEquals(hours, time.hours());
        assertEquals(minutes, time.minutes());
        assertEquals(seconds, time.seconds());
    }

    @Test
    public void testConstructorOneParamWithNegativeValue() {
        int hours = -5;

        Clocktime time = new Clocktime(hours);

        assertEquals(19, time.hours());
    }

    @Test
    public void testConstructorTwoParamsWithNegativeValue() {
        int hours = -5;
        int minutes = -20;

        Clocktime time = new Clocktime(hours, minutes);

        assertEquals(18, time.hours());
        assertEquals(40, time.minutes());
    }

    @Test
    public void testConstructorThreeParamsWithNegativeValue() {
        int hours = -5;
        int minutes = -20;
        int seconds = -15;

        Clocktime time = new Clocktime(hours, minutes, seconds);

        assertEquals(18, time.hours());
        assertEquals(39, time.minutes());
        assertEquals(45, time.seconds());
    }

    @Test
    public void testConstructorOneParamBiggerValueThenRange() {
        int hours = 193;

        Clocktime time = new Clocktime(hours);

        assertEquals(1, time.hours());
    }

    @Test
    public void testConstructorTwoParamsBiggerValueThenRange() {
        int hours = 21;
        int minutes = 153;

        Clocktime time = new Clocktime(hours, minutes);

        assertEquals(23, time.hours());
        assertEquals(33, time.minutes());
    }

    @Test
    public void testConstructorThreeParamsBiggerValueThenRange() {
        int hours = 30;
        int minutes = 213;
        int seconds = 73;

        Clocktime time = new Clocktime(hours, minutes, seconds);

        assertEquals(9, time.hours());
        assertEquals(34, time.minutes());
        assertEquals(13, time.seconds());
    }

    @Test
    public void testCopyConstructor() {
        int hours = 12;
        int minutes = 34;
        int seconds = 5;

        Clocktime time = new Clocktime(hours, minutes, seconds);

        Clocktime clone = new Clocktime(time);

        assertEquals(hours, clone.hours());
        assertEquals(minutes, clone.minutes());
        assertEquals(seconds, clone.seconds());

    }

    @Test
    public void testToStringDefault() {

        Clocktime time = new Clocktime();

        assertEquals("00:00:00", time.toString());
    }

    @Test
    public void testToString() {
        int hours = 12;
        int minutes = 3;
        int seconds = 34;

        Clocktime time = new Clocktime(hours, minutes, seconds);

        assertEquals("12:03:34", time.toString());
    }

    @Test
    public void testEqualsSame() {
        Clocktime time = new Clocktime(1, 2, 3);

        assertTrue(time.equals(time));
    }

    @Test
    public void testEqualsWithNull() {
        Clocktime time = new Clocktime(1, 2, 3);

        assertFalse(time.equals(null));
    }

    @Test
    public void testEqualsSameValues() {
        Clocktime time1 = new Clocktime(1, 2, 3);
        Clocktime time2 = new Clocktime(1, 2, 3);

        assertTrue(time1.equals(time2));
        assertTrue(time2.equals(time2));
    }

    @Test
    public void testEqualsDifferentValues() {
        Clocktime time1 = new Clocktime(1, 2, 3);
        Clocktime time2 = new Clocktime(2, 3, 4);

        assertFalse(time1.equals(time2));
        assertFalse(time2.equals(time1));
    }

    @Test
    public void testHashcode() {
        Clocktime time1 = new Clocktime(1, 2, 3);
        Clocktime time2 = new Clocktime(1, 2, 3);

        assertEquals(time1.hashCode(), time2.hashCode());
    }

    @Test
    public void testSeconds() {
        Clocktime time = new Clocktime(0, 0, 59);
        assertEquals(59, time.seconds());

        time = new Clocktime(0, 0, 60);
        assertEquals(0, time.seconds());
    }

    @Test
    public void testMinutes() {
        Clocktime time = new Clocktime(0, 59);
        assertEquals(59, time.minutes());

        time = new Clocktime(0, 60);
        assertEquals(0, time.minutes());
    }

    @Test
    public void testHours() {
        Clocktime time = new Clocktime(24);
        assertEquals(0, time.hours());

        time = new Clocktime(23);
        assertEquals(23, time.hours());
    }

    @Test
    public void testAdd() {
        Clocktime time = new Clocktime();

        time.add(5);

        assertEquals(5, time.seconds());
    }

    @Test
    public void testAddMinutes() {
        Clocktime time = new Clocktime();

        time.add(65);

        assertEquals(1, time.minutes());
        assertEquals(5, time.seconds());
    }

    @Test
    public void testAddHours() {
        Clocktime time = new Clocktime();

        time.add(18970);

        assertEquals(5, time.hours());
        assertEquals(16, time.minutes());
        assertEquals(10, time.seconds());
    }

    @Test
    public void testAddNegativeValue() {
        Clocktime time = new Clocktime(1, 2, 3);

        // - 2 h 15m 42 s
        time.add(-8142);

        assertEquals(22, time.hours());
        assertEquals(46, time.minutes());
        assertEquals(21, time.seconds());
    }

    @Test
    public void testAddDateChange() {

        Clocktime time = new Clocktime(23, 59, 59);

        time.add(1);

        assertEquals(0, time.hours());
        assertEquals(0, time.minutes());
        assertEquals(0, time.seconds());
    }

    @Test
    public void testDiff() {

        Clocktime time1 = new Clocktime(0, 0, 0);
        Clocktime time2 = new Clocktime(0, 0, 1);

        assertEquals(1, time1.diff(time2));

        assertEquals(86399, time2.diff(time1));
    }

    @Test
    public void testDiff2() {
        Clocktime time1 = new Clocktime(10, 20, 30);
        Clocktime time2 = new Clocktime(20, 30, 40);

        assertEquals(36610, time1.diff(time2));
        assertEquals(49790, time2.diff(time1));
    }

}
