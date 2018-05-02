/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

import java.util.*;

public class CalDayTest{

  GregorianCalendar cal0 = new GregorianCalendar(2018, 4, 30);
  Appt appt0 = new Appt(22, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt1 = new Appt(20, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt2 = new Appt(18, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt3 = new Appt(5, 30, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt4 = new Appt(4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt5 = new Appt(13, 10, 4, 30, 2017, "test appt please ignore", "This is another test", "foo@bar.com");
  Appt appt6 = new Appt(0, 0, 4, 30, 2010, "test appt please ignore", "This is another test", "foo@bar.com");
  CalDay calday0 = new CalDay(cal0);
  CalDay calday1 = new CalDay();
  LinkedList<Appt> appts = new LinkedList<Appt>();

  @Test(timeout = 4000)
  public void testConstructor0()  throws Throwable  {
    CalDay empty = new CalDay();
    assertFalse(empty.isValid() );
  }
  @Test(timeout = 4000)
  public void testConstructor1()  throws Throwable  {

    //changed to 5
    assertEquals("\t --- 5/30/2018 --- \n --- -------- Appointments ------------ --- \n\n", calday0.toString() );
  }

  @Test(timeout = 4000)
  public void testAddAppt0()  throws Throwable  {
    appts.add(appt0);
    calday0.addAppt(appt0);
    assertEquals(appts, calday0.getAppts() );
  }

  @Test(timeout = 4000)
  public void testAddAppt1()  throws Throwable  {
    appts.add(appt1);
    appts.add(appt0);
    calday0.addAppt(appt0);
    calday0.addAppt(appt1);
    assertEquals(appts, calday0.getAppts() );
  }

  @Test(timeout = 4000)
  public void testAddAppt2()  throws Throwable  {
    appts.add(appt1);
    appts.add(appt0);
    calday0.addAppt(appt1);
    calday0.addAppt(appt0);
    assertEquals(appts, calday0.getAppts() );
  }

  @Test(timeout = 4000)
  public void testIterator0()  throws Throwable  {
    calday0.addAppt(appt0);
    assertNotNull(calday0.iterator() );
  }

  @Test(timeout = 4000)
  public void testIterator1()  throws Throwable  {
    assertNull(calday1.iterator() );
  }

  @Test(timeout = 4000)
  public void testToStringEmpty()  throws Throwable  {
    StringBuilder sb = new StringBuilder();
    assertEquals(sb.toString(), calday1.toString() );
  }

  @Test(timeout = 4000)
  public void testToString0()  throws Throwable  {
    calday0.addAppt(appt0);

    //changed to 5
    assertEquals("\t --- 5/30/2018 --- \n --- -------- Appointments ------------ --- \n\t30/4/2017 at 10:30am ,test appt please ignore, This is another test\n \n", calday0.toString() );
  }

  @Test(timeout = 4000)
  public void testGetFullInfomrationApp0()  throws Throwable  {
    calday0.addAppt(appt0);
    //System.out.println(calday0.getFullInfomrationApp(calday0) );
    assertEquals("4-30-2018 \n\t10:30PM test appt please ignore This is another test ", calday0.getFullInfomrationApp(calday0) );
  }

  @Test(timeout = 4000)
  public void testGetFullInfomrationApp1()  throws Throwable  {
    calday0.addAppt(appt3);
    //System.out.println(calday0.getFullInfomrationApp(calday0) );
    assertEquals("4-30-2018 \n\t5:30AM test appt please ignore This is another test ", calday0.getFullInfomrationApp(calday0) );
  }

  @Test(timeout = 4000)
  public void testGetFullInfomrationApp2()  throws Throwable  {
    calday0.addAppt(appt4);
    //System.out.println(calday0.getFullInfomrationApp(calday0) );
    assertEquals("4-30-2018 \n\ttest appt please ignore This is another test ", calday0.getFullInfomrationApp(calday0) );
  }

  @Test(timeout = 4000)
  public void testGetFullInfomrationApp3()  throws Throwable  {
    calday0.addAppt(appt5);
    //System.out.println(calday0.getFullInfomrationApp(calday0) );
    assertEquals("4-30-2018 \n\t1:10PM test appt please ignore This is another test ", calday0.getFullInfomrationApp(calday0) );
  }

  @Test(timeout = 4000)
  public void testGetFullInfomrationApp4()  throws Throwable  {
    calday0.addAppt(appt6);
    //System.out.println(calday0.getFullInfomrationApp(calday0) );
    assertEquals("4-30-2018 \n\t12:00AM test appt please ignore This is another test ", calday0.getFullInfomrationApp(calday0) );
  }

}
