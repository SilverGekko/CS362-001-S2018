
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class DataHandlerTest{


    /**
     * tests getApptRange, I think
     * I don't really understand anything about the inner workings of the function so I don't know how to test it
     */
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      LinkedList<CalDay> days = new LinkedList<CalDay>();
      CalDay cdA = new CalDay( new GregorianCalendar(3,15,2014) );
      CalDay cdB = new CalDay( new GregorianCalendar(3,18,2014) );
      Appt apptA = new Appt(15,3,2014,"ayy","lmao","ayy@lm.ao");
      Appt apptBA = new Appt(13,0,18,3,2014,"ayy","lmao","ayy@lm.ao");
      Appt apptBB = new Appt(15,30,18,3,2014,"ayy","lmao","ayy@lm.ao");
      cdA.addAppt(apptA);
      cdB.addAppt(apptBA);
      cdB.addAppt(apptBB);
      days.add(cdA);
      days.add(cdB);

      DataHandler dh0 = new DataHandler();
      assertEquals(dh0.getApptRange( new GregorianCalendar(3,10,2014), new GregorianCalendar(3,20,2014) ), days);
  }


    /**
     * This technically tests saveAppt but it doesn't really do anything with the internals of the function
     * same with deleteAppt
     */
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt1 = new Appt(1,2,3,"ayy","lmao","ayy@lm.ao");
      DataHandler dh1 = new DataHandler();
      assertTrue(dh1.saveAppt(appt1));
      assertTrue(dh1.deleteAppt(appt1));
  }

}
