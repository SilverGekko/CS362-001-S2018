
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import java.io.*;
import java.util.*;

public class DataHandlerTest{

  Appt Appt0 = new Appt(12, 30, 4, 30, 2018, "test appt", "this is a test appt", "foo@bar.com");
  Appt Appt1 = new Appt(0, 9, 2, 22, 2018, "test appt", "this is a test appt", "foo@bar.com");
  Appt Appt2 = new Appt(5, 54, 12, 5, 2018, "test appt", "this is a test appt", "foo@bar.com");
  Appt Appt3 = new Appt(-1, -1, -1, -1, -1, "invalid appt", "this is an invalid test appt", "foo@bar.com");
  Appt Appt4 = new Appt(1, 30, 1, 16, 2018, "test appt", "this is a test appt", "foo@bar.com");

  @Test(timeout = 4000)
  public void testConstructor0()  throws Throwable  {
    DataHandler dh0 = new DataHandler();
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "calendar_test1.xml");
    dh0.saveAppt(Appt0);

    //changed to false
    assertFalse(file.exists() );
  }

  @Test(timeout = 4000)
  public void testConstructor1()  throws Throwable  {
    DataHandler dh0 = new DataHandler("Test_Filename0.xml");
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename0.xml");
    dh0.saveAppt(Appt0);
    dh0.saveAppt(Appt1);
    dh0.saveAppt(Appt2);
    assertTrue(file.exists() );
  }

  @Test(timeout = 4000)
  public void testInvalidAppt0()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename1.xml");
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename1.xml");
    Appt3.setValid();

    //changed to true
    assertTrue(dh1.saveAppt(Appt3) );
  }

  @Test(timeout = 4000)
  public void testInvalidAppt1()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename1.xml");
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename1.xml");
    Appt3.setValid();
    assertFalse(dh1.deleteAppt(Appt3) );
  }

  @Test(timeout = 4000)
  public void testDeleteAppt0()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename2.xml");
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename2.xml");
    dh1.saveAppt(Appt0);
    dh1.saveAppt(Appt1);
    assertTrue(dh1.deleteAppt(Appt1) );
  }

  @Test(timeout = 4000)
  public void testAutoSave0()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename2.xml", false);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename2.xml");
    dh1.saveAppt(Appt0);
    dh1.saveAppt(Appt1);
    assertTrue(dh1.deleteAppt(Appt1) );
  }

  @Test(timeout = 4000)
  public void testSaveAppt0()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename5.xml", false);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename2.xml");
    int[] recurDaysArr = {2,3,4};
    Appt0.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
    assertTrue(dh1.saveAppt(Appt0) );
  }

  @Test(expected = DateOutOfRangeException.class)
  public void testDateOutOfRange()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename3.xml");
    GregorianCalendar day1 = new GregorianCalendar(2017, 1, 1);
    GregorianCalendar day2 = new GregorianCalendar(2019, 1, 1);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename3.xml");
    dh1.saveAppt(Appt0);
    dh1.saveAppt(Appt1);
    dh1.getApptRange(day2, day1);
  }

  @Test(timeout = 4000)
  public void testGetApptRange0()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename4.xml");
    GregorianCalendar day1 = new GregorianCalendar(2018, 1, 21);
    GregorianCalendar day2 = new GregorianCalendar(2018, 6, 1);
    GregorianCalendar cal0 = new GregorianCalendar(2018, 4, 30);
    GregorianCalendar cal1 = new GregorianCalendar(2018, 2, 22);
    GregorianCalendar cal2 = new GregorianCalendar(2018, 12, 5);
    CalDay calday0 = new CalDay(cal0);
    CalDay calday1 = new CalDay(cal1);
    CalDay calday2 = new CalDay(cal2);
    calday0.addAppt(Appt0);
    calday1.addAppt(Appt1);
    calday2.addAppt(Appt2);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename4.xml");
    dh1.saveAppt(Appt0);
    dh1.saveAppt(Appt1);
    dh1.saveAppt(Appt2);
    LinkedList<CalDay> expectedList = new LinkedList<CalDay>();
    expectedList.add(calday1);
    expectedList.add(calday0);
    expectedList.add(calday2);
    LinkedList<CalDay> actualList = (LinkedList<CalDay>)dh1.getApptRange(day1, day2);
  }

  @Test(timeout = 4000)
  public void testGetApptRange1()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename4.xml");
    GregorianCalendar day1 = new GregorianCalendar(2018, 1, 21);
    GregorianCalendar day2 = new GregorianCalendar(2018, 6, 1);
    GregorianCalendar cal0 = new GregorianCalendar(2018, 4, 30);
    GregorianCalendar cal1 = new GregorianCalendar(2018, 2, 22);
    GregorianCalendar cal2 = new GregorianCalendar(2018, 12, 5);
    CalDay calday0 = new CalDay(cal0);
    CalDay calday1 = new CalDay(cal1);
    CalDay calday2 = new CalDay(cal2);
    int[] recurDaysArr = {2,3,4};
    Appt0.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
    Appt1.setRecurrence( recurDaysArr, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);
    Appt2.setRecurrence( recurDaysArr, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
    calday0.addAppt(Appt0);
    calday1.addAppt(Appt1);
    calday2.addAppt(Appt2);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename4.xml");
    dh1.saveAppt(Appt0);
    dh1.saveAppt(Appt1);
    dh1.saveAppt(Appt2);

    LinkedList<CalDay> actualList = (LinkedList<CalDay>)dh1.getApptRange(day1, day2);
  }

  @Test(timeout = 4000)
  public void testGetApptRange2()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename4.xml");
    GregorianCalendar day1 = new GregorianCalendar(2017, 1, 21);
    GregorianCalendar day2 = new GregorianCalendar(2025, 6, 1);
    GregorianCalendar cal0 = new GregorianCalendar(2018, 4, 30);
    GregorianCalendar cal1 = new GregorianCalendar(2018, 2, 22);
    GregorianCalendar cal2 = new GregorianCalendar(2018, 12, 5);
    CalDay calday0 = new CalDay(cal0);
    //int[] recurDaysArr = {2,3,4};
    Appt0.setRecurrence( null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
    calday0.addAppt(Appt0);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename4.xml");
    dh1.saveAppt(Appt0);
    dh1.getApptRange(day1, day2);
  }
}
