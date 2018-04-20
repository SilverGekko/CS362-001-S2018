
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

public class DataHandlerTest{

  @Test(timeout = 4000)
  public void testConstructor0()  throws Throwable  {
    DataHandler dh0 = new DataHandler();
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "calendar.xml");
    assertTrue(file.exists() );
  }
  @Test(timeout = 4000)
  public void testConstructor1()  throws Throwable  {
    DataHandler dh1 = new DataHandler("Test_Filename.xml");
    File file = new File(System.getProperty("user.dir") +
    System.getProperty("file.separator") + "Test_Filename.xml");
    file.createNewFile();
    assertTrue(file.exists() );
  }

}
