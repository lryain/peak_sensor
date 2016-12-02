/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.common.utils;

import com.creditcloud.model.loan.Duration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sobranie
 */
public class DateUtilsTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static final GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();

    public DateUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of offset method, of class DateUtils.
     */
    @Test
    public void testOffset() throws ParseException {
        System.out.println("offset");
        Date asOfDate = sdf.parse("20110130");
        Duration duration = new Duration(395);
        Date expResult = sdf.parse("20120229");
        Date result = DateUtils.offset(asOfDate, duration);
        assertEquals(expResult, result);
    }

    /**
     * Test of listDates method, of class DateUtils.
     */
    @Test
    public void testListDates() throws ParseException {

        Date start = sdf.parse("20130601");
        Date end = sdf.parse("20130630");
        List result = DateUtils.listDates(start, end);
        assertEquals(result.size(), 30);

        start = sdf.parse("20130630");
        result = DateUtils.listDates(start, end);
        assertEquals(result.size(), 1);

        start = sdf.parse("20130730");
        result = DateUtils.listDates(start, end);
        assertEquals(result.size(), 0);
    }

    @Test
    public void testGet0OClock() {
        calendar.set(2013, 8, 1, 1, 2, 3);
        Date date = calendar.getTime();
        Date result = DateUtils.get0OClock(date);
        calendar.set(2013, 8, 1, 0, 0, 0);
        Date exp = calendar.getTime();
        assertEquals(result, exp);
    }
    
    @Test
    public void testDateParse(){
        String date = "2014-01-03";
        Date result = DateUtils.parse(date, "yyyy-MM-dd");
        assertNotNull(result);
        
        Date result2 = DateUtils.parse(date, "yyyy-MM-dd HH");
        assertNull(result2);
        
        String date3 = "2014-01-03 22";
        Date result3 = DateUtils.parse(date3, "yyyy-MM-dd HH");
        assertNotNull(result3);
    }
    
    @Test
    public void testDateFormat(){
        Date date1 = new Date();
        String result1 = DateUtils.format(date1, "yyyy-MM-dd");
        assertNotNull(result1);
        
        String result2 = DateUtils.format(date1, "yyyy-MM-dd HH:mm");
        assertNotNull(result2);
    }
}