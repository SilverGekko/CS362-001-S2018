/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.GregorianCalendar;
import java.util.LinkedList;


public class CalDayTest{

    /**
     * tests the constructor and its setter functions
     */
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      GregorianCalendar gc0 = new GregorianCalendar(2018, 3, 20);
      CalDay cal0 = new CalDay(gc0);
      assertEquals( cal0.getDay(), 20 );
      assertEquals( cal0.getMonth(), 4 );
      assertEquals( cal0.getYear(), 2018 );
      assertTrue( cal0.isValid() );
  }


    /**
     * tests the add, and setter and getter for the appointment linkedlist
     */
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 20);
      CalDay cal1 = new CalDay(gc1);
      LinkedList<Appt> appts1 = new LinkedList<Appt>();
      Appt apptA = new Appt(12,30,1,2,3,"ayy","lmao","ayy@lm.ao");
      Appt apptB = new Appt(18,30,1,2,3,"ayy","lmao","ayy@lm.ao");
      Appt apptC = new Appt(0,0,1,2,3,"ayy","lmao","ayy@lm.ao");
      appts1.add( apptA );
      appts1.add( apptB );
      appts1.add( apptC );

      cal1.addAppt( apptA );
      cal1.addAppt( apptB );
      cal1.addAppt( apptC );
      assertEquals( cal1.getSizeAppts(), 3 );
      assertEquals( appts1, cal1.getAppts() );
  }


    /**
     * tests the constructor given no args
     */
  @Test(timeout=4000)
  public void test02() throws Throwable {
      CalDay cal2 = new CalDay();
      assertFalse( cal2.isValid() );
  }


    /**
     * tests the toString function with no appointments
     */
  @Test(timeout=4000)
  public void test03() throws Throwable {
      GregorianCalendar gc3 = new GregorianCalendar(2018,7,3);
      CalDay cal3 = new CalDay(gc3);
      assertEquals( cal3.toString(), "\t --- 08/03/2018 --- \n --- -------- Appointments ------------ --- \n\n" );
  }


    /**
     * tests the getFullInfomrationApp function with two appointments
     * this is also misspelled btw
     * @throws Throwable
     */
  @Test(timeout=4000)
  public void test04() throws Throwable {
      GregorianCalendar gc4 = new GregorianCalendar(2018,7,3);
      CalDay cal4 = new CalDay(gc4);
      Appt apptA = new Appt(11,30,1,2,3,"ayy","lmao","ayy@lm.ao");
      Appt apptB = new Appt(18,30,1,2,3,"ayy","lmao","ayy@lm.ao");
      Appt apptC = new Appt(0,0,1,2,3,"ayy","lmao","ayy@lm.ao");
      cal4.addAppt(apptA);
      cal4.addAppt(apptB);
      cal4.addAppt(apptC);
      assertEquals(cal4.getFullInfomrationApp(cal4), "7-3-2018\n\t11:30AM ayy lmao \n\t6:30PM ayy lmao \n\t12:00AM ayy lmao ");
  }


  public void test05() throws Throwable {
      CalDay day = new CalDay();
      assertEquals(day.toString(),"");
  }
}
