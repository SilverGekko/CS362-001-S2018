/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
import java.io.*;
public class ApptTest  {

  //new for assignment 3
  private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
  Appt appt1 = new Appt(4, 30, 2017, "another birthday", "This is another birthday party", "foo@bar.com");
  Appt appt2 = new Appt(12, 30, 7, 12, 2017, "another birthday", "This is another birthday party", "foo@bar.com");
  Appt appt3 = new Appt(22, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt4 = new Appt(0, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt5 = new Appt(0, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);

  //new for assignment 3
  @Before
  public void setUpStreams() {
      //System.setOut(new PrintStream(outContent));
      System.setErr(new PrintStream(errContent));
  }

  //new for assignment 3
  @After
  public void restoreStreams() {
      //System.setOut(System.out);
      System.setErr(System.err);
  }
/*
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }
*/
@Test(timeout = 4000)
 public void testConstructor0()  throws Throwable  {
   //Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
   String string0 = appt0.toString();
   assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
}

@Test(timeout = 4000)
 public void testConstructor1()  throws Throwable  {
   String string1 = appt1.toString();
   //assertEquals("\t30/4/2017 at -1:-1pm ,another birthday, This is another birthday party\n", string1);
}

@Test(timeout = 4000)
  public void testSetXMLelement() throws Throwable {
    appt1.setXmlElement(null);
    assertNull(appt1.getXmlElement() );
  }

@Test(timeout = 4000)
  public void testValidHour() throws Throwable {
    appt2.setStartHour(99);
    appt2.setValid();
    assertFalse(appt2.getValid() );
  }

@Test(timeout = 4000)
  public void testValidMinute() throws Throwable {
    appt2.setStartMinute(99);
    appt2.setValid();
    assertFalse(appt2.getValid() );
  }

@Test(timeout = 4000)
  public void testValidDay() throws Throwable {
    appt2.setStartMinute(200);
    appt2.setValid();
    assertFalse(appt2.getValid() );
  }

@Test(timeout = 4000)
  public void testValidMonth() throws Throwable {
    appt2.setStartMonth(99);
    appt2.setValid();
    assertFalse(appt2.getValid() );
  }

@Test(timeout = 4000)
  public void testValidYear() throws Throwable {
    appt2.setStartYear(-99);
    appt2.setValid();
    assertFalse(appt2.getValid() );
  }

@Test(timeout = 4000)
  public void testValidDaysInMonth() throws Throwable {
    appt2.setStartMonth(4);
    appt2.setStartDay(31); //April 31st doesn't exist
    appt2.setValid();
    assertFalse(appt2.getValid() );
  }

@Test(timeout = 4000)
  public void testSetTitle0() throws Throwable {
    appt2.setTitle("foobar");
    assertEquals("foobar", appt2.getTitle() );
  }

@Test(timeout = 4000)
  public void testSetTitle1() throws Throwable {
    appt2.setTitle(null);
    assertEquals("", appt2.getTitle() );
  }

@Test(timeout = 4000)
  public void testSetDescription0()  throws Throwable  {
    appt5.setDescription("test description");
    assertEquals("test description", appt5.getDescription() );
    }

@Test(timeout = 4000)
  public void testSetDescription1()  throws Throwable  {
    appt5.setDescription(null);
    assertEquals("", appt5.getDescription() );
    }


@Test(timeout = 4000)
 public void testIsOn0()  throws Throwable  {
   assertTrue(appt4.isOn(4,30,2017) );
 }

@Test(timeout = 4000)
  public void testIsOn1()  throws Throwable  {
    assertFalse(appt4.isOn(4,29,2017) );
 }

 @Test(timeout = 4000)
   public void testIsOn2()  throws Throwable  {
     assertFalse(appt4.isOn(4,30,2010) );
  }

@Test(timeout = 4000)
  public void testIsOn3()  throws Throwable  {
    assertFalse(appt4.isOn(5,30,2017) );
 }

@Test(timeout = 4000)
  public void testHasTimeSet0()  throws Throwable  {
    assertTrue(appt4.hasTimeSet() );
  }

@Test(timeout = 4000)
  public void testHasTimeSet1()  throws Throwable  {
    assertFalse(appt1.hasTimeSet() );
  }

@Test(timeout = 4000)
 public void testSetRecurrance0()  throws Throwable  {
   int[] recurDay = new int[]{2,3,4};
   appt4.setRecurrence(recurDay, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
   assertTrue(appt4.isRecurring() );
  }

@Test(timeout = 4000)
public void testSetRecurrance1()  throws Throwable  {
  int[] recurDay = new int[]{2,3,4};
  appt4.setRecurrence(recurDay, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
  assertEquals(2, appt4.getRecurIncrement() );
  }

@Test(timeout = 4000)
 public void testSetRecurrance2()  throws Throwable  {
   assertFalse(appt4.isRecurring() );
  }

@Test(timeout = 4000)
public void testSetRecurDays0()  throws Throwable  {
  appt4.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
  assertArrayEquals(new int[0], appt4.getRecurDays() );
  }

@Test(timeout = 4000)
public void testSetRecurDays1()  throws Throwable  {
  int[] days = {1,2,3};
  appt4.setRecurrence(days, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
  assertArrayEquals(days, appt4.getRecurDays() );
  }

@Test(timeout = 4000)
  public void testgetRecurBy()  throws Throwable  {
    int[] days = {1,2,3};
    appt4.setRecurrence(days, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
    assertEquals(2, appt4.getRecurIncrement() );
    }

@Test(timeout = 4000)
public void testGetEmailAddress0()  throws Throwable  {
  assertEquals("foo@bar.com", appt4.getEmailAddress() );
  }

@Test(timeout = 4000)
public void testGetEmailAddress1()  throws Throwable  {
  //assertEquals(null, appt5.getEmailAddress() );
  }

@Test(timeout = 4000)
 public void testRepresntationApp0()  throws Throwable  {
   String string3 = appt3.toString();
   assertEquals("\t30/4/2017 at 10:30pm ,test appt please ignore, This is another test\n", string3);
 }

 @Test(timeout = 4000)
  public void testRepresntationApp1()  throws Throwable  {
    String string4 = appt4.toString();
    assertEquals("\t30/4/2017 at 12:30am ,test appt please ignore, This is another test\n", string4);
  }

  //new for assignment 3

  @Test(timeout = 4000)
  public void testXMLNull() throws Throwable {
    Appt Appt0a = new Appt(0, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);

    assertNull(Appt0a.getXmlElement() );
  }

  @Test(timeout = 4000)
    public void testgetRecurBy2()  throws Throwable  {
      int[] days = {1,2,3};
      appt4.setRecurrence(days, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      assertEquals(1, appt4.getRecurBy() );
  }

  @Test(timeout = 4000)
   public void testRepresntationApp0a()  throws Throwable  {
     Appt Appt0a = new Appt(15, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);
     Appt Appt0b = new Appt(9, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);
     String string1 = Appt0a.toString();
     String string2 = Appt0b.toString();

     //changed to am
     assertEquals("\t30/4/2017 at 3:30pm ,test appt please ignore, This is another test\n", string1);
     assertEquals("\t30/4/2017 at 9:30am ,test appt please ignore, This is another test\n", string2);
   }

   //TODO: fix this one so it kills the mutant on line 377-378
   @Test(timeout = 4000)
   public void testToStringError() throws Throwable {
     String app2String = appt2.toString();
     //System.out.println("ERROR CONTENTS: " + errContent.toString());
     assertNotEquals("\tThis appointment is not valid", errContent.toString() );
   }

   //TODO: fix this one so it kills the mutant on line 377-378
   @Test(timeout = 4000)
     public void testValidHour0() throws Throwable {
       appt2.setStartHour(11);
       String app3String = appt3.toString();
       assertEquals("\t30/4/2017 at 10:30pm ,test appt please ignore, This is another test\n", app3String );
     }

  @Test(timeout = 4000)
    public void testStartMonthValidMutation0() throws Throwable {
      appt3.setStartMonth(1);
      appt3.setValid();

      assertTrue(appt3.getValid() );
  }

  @Test(timeout = 4000)
    public void testStartMonthValidMutation1() throws Throwable {
      appt3.setStartMonth(12);
      appt3.setValid();

      assertTrue(appt3.getValid() );
  }

  @Test(timeout = 4000)
    public void testStartHourValidMutation0() throws Throwable {
      appt3.setStartMonth(1);
      appt3.setStartHour(0);
      appt3.setValid();

      //System.out.println("appt3 startHour: " + appt3.getStartHour() );

      assertTrue(appt3.getValid() );
  }

  @Test(timeout = 4000)
    public void testStartHourValidMutation1() throws Throwable {
      appt3.setStartMonth(1);
      appt3.setStartHour(23);
      appt3.setValid();

      //System.out.println("appt3 startHour: " + appt3.getStartHour() );

      assertTrue(appt3.getValid() );
  }

  @Test(timeout = 4000)
    public void testStartMinuteValidMutation0() throws Throwable {
      appt3.setStartMonth(1);
      appt3.setStartHour(23);
      appt3.setStartMinute(0);
      appt3.setValid();

      //System.out.println("appt3 startHour: " + appt3.getStartHour() );

      assertTrue(appt3.getValid() );
  }

  @Test(timeout = 4000)
    public void testStartMinuteValidMutation1() throws Throwable {
      appt3.setStartMonth(1);
      appt3.setStartHour(23);
      appt3.setStartMinute(59);
      appt3.setValid();

      //System.out.println("appt3 startHour: " + appt3.getStartHour() );

      assertTrue(appt3.getValid() );
  }

  @Test(timeout = 4000)
    public void testStartYearValidMutation0() throws Throwable {
      appt3.setStartMonth(1);
      appt3.setStartHour(23);
      appt3.setStartMinute(59);
      appt3.setStartYear(0);
      appt3.setValid();

      //System.out.println("appt3 startHour: " + appt3.getStartHour() );

      assertFalse(appt3.getValid() );
  }

}
