/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;




public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertTrue(appt0.hasTimeSet());
  }


    /**
     * This tests the second constructor which doesn't take hour and minute
     */
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt1 = new Appt(20, 4, 2020, "ayy", "you already know what it is", "silversurfer@hotmail.com");
      String string1 = appt1.toString();
      assertEquals(2, appt1.getRecurBy());
      assertFalse(appt1.isRecurring());
      assertEquals("\t4/20/2020 at -1:-1am ,ayy, you already know what it is\n", string1);
      assertEquals(0, appt1.getRecurIncrement());
      appt1.setValid();
      assertFalse(appt1.hasTimeSet());
  }


    /**
     * tests the set and get functinos for each of title, description, email when given null
     */
  @Test( timeout=4000)
  public void test02() throws Throwable {
      Appt appt2 = new Appt(20, 4, 2020, null, null, null);
      assertEquals( appt2.getTitle(), "" );
      assertEquals( appt2.getDescription(), "" );
      assertEquals( appt2.getEmailAddress(), "" );
  }


    /**
     * Tests that each if statement in setValid() fails with wrong data
     * Constructor tests already test if good data passes
     */
  @Test(timeout=4000)
  public void test03() throws Throwable {
      Appt appt3 = new Appt(-1, 2, 3, 4, 5, "ayy", "lmao", "ayy@lm.ao");
      appt3.setValid();
      assertFalse( appt3.getValid() );

      Appt appt4 = new Appt(1, 95, 3, 4, 5, "ayy", "lmao", "ayy@lm.ao");
      appt4.setValid();
      assertFalse( appt4.getValid() );

      Appt appt5 = new Appt(1, 2, 3, 0, 5, "ayy", "lmao", "ayy@lm.ao");
      appt5.setValid();
      assertFalse( appt5.getValid() );

      Appt appt6 = new Appt(1, 2, 3, 4, -1, "ayy", "lamo", "ayy@lm.ao");
      appt6.setValid();
      assertFalse( appt6.getValid() );

      Appt appt7 = new Appt(1, 2, 30, 2, 2001, "ayy", "lmao", "ayy@lm.ao");
      appt7.setValid();
      assertFalse( appt7.getValid() );
  }


  /**
   * Tests the setRecurrence function as well as the related getters
   */
  @Test(timeout=4000)
  public void test04() throws Throwable {
      Appt appt8 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      int[] days = {2};
      appt8.setRecurrence( days, 2, 1, 5 );
      assertTrue( appt8.isRecurring() );
      assertEquals( appt8.getRecurBy(), 2 );
      assertEquals( appt8.getRecurDays(), days );
      assertEquals( appt8.getRecurIncrement(), 1 );
      assertEquals( appt8.getRecurNumber(), 5 );

      Appt appt9 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt9.setRecurrence( null, 2, 1, 5 );
      int[] emptyDays = {};
      assertEquals( appt9.getRecurDays(), emptyDays );
  }


    /**
     * Tests toString() and its subordinate represntationApp()
     * by the way, that one is misspelled
     */
  @Test( timeout=4000)
  public void test05() throws Throwable {
      Appt appt10 = new Appt(21, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals( appt10.toString(), "\t14/9/2018 at 9:30pm ,Birthday Party, This is my birthday party\n");

      Appt appt11 = new Appt(0, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals( appt11.toString(), "\t14/9/2018 at 12:30am ,Birthday Party, This is my birthday party\n");
  }


    /**
     * tests isOn()
     */
  @Test(timeout=4000)
  public void test06() throws Throwable {
      Appt appt12 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertTrue( appt12.isOn(9, 14, 2018) );
      assertFalse( appt12.isOn(9, 15, 2018) );
      assertFalse( appt12.isOn(10, 14, 2018) );
      assertFalse( appt12.isOn(9, 14, 2019) );
  }

    /**
     * Tests that each if statement in setValid() fails with wrong data
     * Constructor tests already test if good data passes
     */
    @Test(timeout=4000)
    public void test07() throws Throwable {
        Appt appt13 = new Appt(27, 2, 3, 4, 5, "ayy", "lmao", "ayy@lm.ao");
        appt13.setValid();
        assertFalse( appt13.getValid() );

        Appt appt14 = new Appt(1, -2, 3, 4, 5, "ayy", "lmao", "ayy@lm.ao");
        appt14.setValid();
        assertFalse( appt14.getValid() );

        Appt appt15 = new Appt(1, 2, 3, 15, 5, "ayy", "lmao", "ayy@lm.ao");
        appt15.setValid();
        assertFalse( appt15.getValid() );

        Appt appt6 = new Appt(1, 2, -1, 4, 5, "ayy", "lamo", "ayy@lm.ao");
        appt6.setValid();
        assertFalse( appt6.getValid() );
    }

}