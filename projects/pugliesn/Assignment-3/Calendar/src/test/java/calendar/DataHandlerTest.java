
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import static org.hamcrest.CoreMatchers.*;

public class DataHandlerTest{

  //new for assignment 3
  private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  GregorianCalendar today, tomorrow;


  public boolean apptInList(List<Appt> list, Appt appt) {
    Iterator<Appt> it = list.iterator();
    while (it.hasNext()) {
      Appt temp = it.next();
      if (appt.getTitle().equals(temp.getTitle()))
          return true;
    }
    return false;
  }

  Appt Appt0 = new Appt(12, 30, 4, 30, 2018, "test appt", "this is a test appt", "foo@bar.com");
  Appt Appt1 = new Appt(0, 9, 2, 22, 2018, "test appt", "this is a test appt", "foo@bar.com");
  Appt Appt2 = new Appt(5, 54, 12, 5, 2018, "test appt", "this is a test appt", "foo@bar.com");
  Appt Appt3 = new Appt(-1, -1, -1, -1, -1, "invalid appt", "this is an invalid test appt", "foo@bar.com");
  Appt Appt4 = new Appt(1, 30, 1, 16, 2018, "test appt", "this is a test appt", "foo@bar.com");

  //new for assignment 3
  @Before
  public void setUp() {
      System.setOut(new PrintStream(outContent));

      today = new GregorianCalendar(2018, 4, 30);
      tomorrow = new GregorianCalendar(2018, 4, 30);
      tomorrow.add(Calendar.DAY_OF_MONTH, 1);

      outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
      System.setOut(System.out);
  }

  @Test(timeout = 4000)
  public void testConstructor0()  throws Throwable  {
    DataHandler dh0 = new DataHandler();
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "calendar.xml");
    dh0.saveAppt(Appt0);
    assertTrue(file.exists() );
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
    assertFalse(dh1.saveAppt(Appt3) );
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

  //new assignment 3 tests there

  @Test(timeout = 4000)
  public void testSave0a()  throws Throwable  {
    DataHandler dh1 = new DataHandler("assignment3.xml", true);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "assignment3.xml");
    assertTrue(dh1.saveAppt(Appt0) );
    assertTrue(dh1.saveAppt(Appt1) );
    assertTrue(dh1.deleteAppt(Appt1) );
  }

  @Test(timeout = 4000)
  public void testConstructor0a()  throws Throwable  {
    DataHandler dh0 = new DataHandler("  23%$# ##dfd.xml", true);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "  23%$# ##dfd.xml");
    dh0.saveAppt(Appt0);

    assertFalse(file.exists() );
  }

  @Test(timeout = 4000)
  public void testConstructor0b()  throws Throwable  {
    DataHandler dh0 = new DataHandler("  23%$# ##dfd.xml", true);
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "  23%$# ##dfd.xml");

    //changed to false
    System.out.println("this should be empty");
    assertEquals("this should be empty" + System.getProperty("line.separator"), outContent.toString() );
  }

  @Test(timeout = 4000)
  public void testGetApptRange1a()  throws Throwable  {
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

    System.out.println("this should be empty");
    assertEquals("this should be empty" + System.getProperty("line.separator"), outContent.toString() );
  }

  @Test(timeout = 4000)
  public void testGetApptRange1b()  throws Throwable  {
    Appt0 = new Appt(12, 30, 2, 1, 2018, "test appt", "this is a test appt", "foo@bar.com");
    DataHandler dh1 = new DataHandler("assignment3_1.xml");
    GregorianCalendar day1 = new GregorianCalendar(2018, 0, 1);
    GregorianCalendar day2 = new GregorianCalendar(2018, 0, 2);
    GregorianCalendar cal0 = new GregorianCalendar(2018, 0, 1);
    GregorianCalendar cal0a = new GregorianCalendar(2018, 0, 1);
    CalDay calday0 = new CalDay(cal0);
    CalDay calday0a = new CalDay(cal0a);
    LinkedList<CalDay> expectedList = new LinkedList<CalDay>();
    expectedList.add(calday0a);
    calday0.addAppt(Appt0);
    dh1.saveAppt(Appt0);
    LinkedList<CalDay> actualList = (LinkedList<CalDay>)dh1.getApptRange(day1, day2);

    assertThat(actualList, is(not(expectedList)) );
  }

  @Test(timeout = 4000)
  public void testPropagation() throws Throwable {

    DataHandler dataHandler;
    dataHandler = new DataHandler();

    int startHour=16;
    int startMinute=30;
    int startDay=-1;
    int startMonth=4+1;
    int startYear=2018;
    String title="Visit";
    String description="Visiting my parents!";
    String emailAddress="pugliesn@oregonstate.edu";
    //Construct a new Appointment object with the initial data
    Appt appt4 = new Appt(startHour,
             startMinute ,
             startDay ,
             startMonth ,
             startYear ,
             title,
            description,
            emailAddress);
    int[] recurDaysArr4={2,3,4};
    appt4.setRecurrence( recurDaysArr4, Appt.RECUR_BY_WEEKLY, 3, Appt.RECUR_NUMBER_FOREVER);

    appt4.setValid();

    System.out.println(appt4.toString());


    dataHandler.saveAppt(appt4);
  }

  @Test(timeout = 4000)
  public void testMoreSaving() throws Throwable {
    DataHandler dh0 = new DataHandler();
    //check if the file is on disk and when it was modified. this will tell us if the datahandler actually wrote to disk
    File f = new File("calendar.xml");
    long modifiedTimeOriginal = f.lastModified(), modifiedTimeNew;

    //make some appointments that are boundary conditions
    Appt appt0 = new Appt(-1, -1, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 0", "Description 0", "foo0@bar0.com");
    Appt appt1 = new Appt(15, 61, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 1", "Description 1", "foo1@bar1.com");
    Appt appt2 = new Appt(2, 45, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 2", "Description 2", "foo2@bar2.com");
    Appt appt3 = new Appt(12, 1, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 3", "Description 3", "foo3@bar3.com");

    //set them to valid
    //appt 0 will never be valid
    appt1.setValid(); //this will be invalid which is what we want
    appt2.setValid();
    appt3.setValid();

    //current no apps present
    List<CalDay> days = dh0.getApptRange(today, tomorrow);
    int startNumberOfAppts = days.get(0).getSizeAppts();

    assertTrue(dh0.saveAppt(appt0) );
    days = dh0.getApptRange(today, tomorrow);
    assertEquals(days.get(0).getSizeAppts(), 1 + startNumberOfAppts);
    modifiedTimeNew = f.lastModified();
    assertFalse(modifiedTimeOriginal == modifiedTimeNew);

    assertFalse(dh0.saveAppt(appt1) );
    days = dh0.getApptRange(today, tomorrow);
    assertEquals(days.get(0).getSizeAppts(), 1 + startNumberOfAppts);
    modifiedTimeNew = f.lastModified();
    assertFalse(modifiedTimeOriginal == modifiedTimeNew);
  }

  @Test(timeout = 4000)
  public void testValidField() throws Throwable {
    DataHandler dh0 = new DataHandler("calendar_test2.xml", false);
    Appt appt0 = new Appt(15, 30, 3, 30, 2018, "Title 0", "Description 0", "foo0@bar0.com");
    appt0.setValid();
    dh0.deleteAppt(appt0);
    dh0.saveAppt(appt0);
    dh0.deleteAppt(appt0);

    Field field = DataHandler.class.getDeclaredField("valid");
    field.setAccessible(true);
    field.setBoolean(dh0, false);
    assertEquals(dh0.getApptRange(today, tomorrow), null);
  }

  @Test(timeout = 4000)
  public void testDateOutOfRange0a() throws Throwable {
    DataHandler dh0 = new DataHandler("calendar_test.xml");
    List<CalDay> days;
    try {
      days = dh0.getApptRange(tomorrow, today);
      fail();
    }
    catch(DateOutOfRangeException e) {}
    days = dh0.getApptRange(today, tomorrow);
    tomorrow.add(Calendar.MONTH, 1);
    days = dh0.getApptRange(today, tomorrow);
    assertEquals("", outContent.toString() );
  }

  @Test(timeout = 4000)
  public void testRecurringAppts() throws Throwable {
	  DataHandler dh0 = new DataHandler("recurring_test", false);
	  Appt appt0 = new Appt(15, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 0", "Desc 0", "foo@bar.com");
	  Appt appt1 = new Appt(15, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 1", "Desc 1", "foo@bar.com");
	  Appt appt2 = new Appt(15, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 2", "Desc 2", "foo@bar.com");
	  Appt appt3 = new Appt(15, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 3", "Desc 3", "foo@bar.com");
	  Appt appt4 = new Appt(15, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 4", "Desc 4", "foo@bar.com");
    Appt appt5 = new Appt(15, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "Title 5", "Desc 5", "foo@bar.com");

    //recur on the [9th] and [10th] days every [1] [weeks] [forever]
    //should be invalid
    appt0.setRecurrence(new int[] {9, 10}, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);

    //recur on the same day as it started every [1] [weeks] [forever]
    appt1.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);

    //recur on the [3rd] [5th] [6th] days every [1] [weeks] [forever]
    appt2.setRecurrence(new int[] {3,5,6}, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);

    //recur on the same day it started every [1] [months] [forever]
    appt3.setRecurrence(null, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);

    //recur on the same day it started every [1] [years] [forever]
    appt4.setRecurrence(null, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);

    //recur on the same day it started every [1] $BADVALUE [forever]
    //should be invalid?
    appt5.setRecurrence(null, 26, 1, Appt.RECUR_NUMBER_FOREVER);

    appt0.setValid();
    appt1.setValid();
    appt2.setValid();
    appt3.setValid();
    appt4.setValid();
    appt5.setValid();

    //try to save some invalids
    dh0.saveAppt(appt0);
    dh0.deleteAppt(appt0);
    dh0.saveAppt(appt5);
    dh0.deleteAppt(appt5);

    //save all the appointments
    dh0.saveAppt(appt0);
    dh0.saveAppt(appt1);
    dh0.saveAppt(appt2);
    dh0.saveAppt(appt3);
    dh0.saveAppt(appt4);
    dh0.saveAppt(appt5);

    //check for appointments for the next 3 years
    tomorrow.add(Calendar.YEAR, 3);

    //get all the appts between toda and the new "tomorrow"
    try {
      List<CalDay> days = dh0.getApptRange(today, tomorrow);
      assertNotNull(days);
      Iterator<CalDay> it = days.iterator();

      CalDay day = it.next();

      assertEquals("", outContent.toString());
      assertEquals(day.getSizeAppts(), 6);

      //check if each appointment is in the list
      while (it.hasNext() ) {
        day = it.next();
        List<Appt> appts = day.getAppts();
        assertFalse(apptInList(appts, appt0) );

        GregorianCalendar current = new GregorianCalendar(day.getYear(), day.getMonth() - 1, day.getDay() );
        if(current.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
          //assertFalse("first loop assert " + current.toString(), apptInList(appts, appt3) );
          if(current.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
          //  assertFalse("second loop assert " + current.toString(), apptInList(appts, appt4) );
          }
        }

        int apptday = current.get(Calendar.DAY_OF_WEEK);
        //if (apptday == 3 || apptday == 5 || apptday == 6) {
          //assertTrue("appt is the current day " + current.toString(), apptInList(appts, appt2));
        //}
      //  assertEquals(current.toString(), apptInList(appts, appt1), apptday == today.get(Calendar.DAY_OF_WEEK));
      }
    } catch (NullPointerException e) { fail("null pointer exception"); }
    catch (RuntimeException e) { fail("runtime exception"); }
  }
}
