/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {
  Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
  Appt appt1 = new Appt(4, 30, 2017, "another birthday", "This is another birthday party", "foo@bar.com");
  Appt appt2 = new Appt(12, 30, 7, 12, 2017, "another birthday", "This is another birthday party", "foo@bar.com");
  Appt appt3 = new Appt(22, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt4 = new Appt(0, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt5 = new Appt(0, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);
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
   assertEquals("\t30/4/2017 at -1:-1pm ,another birthday, This is another birthday party\n", string1);
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
  assertEquals(null, appt5.getEmailAddress() );
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
}
