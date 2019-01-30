package com.spournasseh.calendartool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalendarToolUnitTest {

    @Test
    public void gregorianToIranianTest() throws Exception {
        CalendarTool tool = new CalendarTool();
        tool.setGregorianDate(2019, 1, 30);
        assertEquals("1397/11/10", tool.getIranianDate());
        assertEquals(1397, tool.getIranianYear());
        assertEquals(11, tool.getIranianMonth());
        assertEquals(10, tool.getIranianDay());
        assertEquals("چهارشنبه", tool.getIranianWeekDay());
        assertEquals("10 بهمن", tool.getIranianStringShorter());
        assertEquals("10 بهمن 1397", tool.getIranianStringShort());
        assertEquals("چهارشنبه 10 بهمن 1397", tool.getIranianStringLong());
    }

    @Test
    public void iranianToGregorianTest() throws Exception {
        CalendarTool tool = new CalendarTool();
        tool.setIranianDate(1397, 11, 10);
        assertEquals("2019/1/30", tool.getGregorianDate());
        assertEquals(2019, tool.getGregorianYear());
        assertEquals(1, tool.getGregorianMonth());
        assertEquals(30, tool.getGregorianDay());
        assertEquals("Wednesday", tool.getGregorianWeekDay());
        assertEquals("30 January 2019", tool.getGregorianStringShort());
        assertEquals("Wednesday 30 January 2019", tool.getGregorianStringLong());
        assertEquals("30 ژانویه 2019", tool.getGregorianStringFarsiShort());
        assertEquals("چهارشنبه 30 ژانویه 2019", tool.getGregorianStringFarsiLong());
    }


    @Test
    public void leapTest() {
        CalendarTool tool = new CalendarTool();
        assertEquals(false, tool.isPersianLeapYear(1394));
        assertEquals(true, tool.isPersianLeapYear(1395));
        assertEquals(true, tool.isPersianLeapYear(1408));

        assertEquals(false, tool.isGregorianLeapYear(1900));
        assertEquals(true, tool.isGregorianLeapYear(2000));
        assertEquals(true, tool.isGregorianLeapYear(1904));
    }
}