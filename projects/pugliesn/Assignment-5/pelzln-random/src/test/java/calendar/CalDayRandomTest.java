package calendar;


import org.junit.Test;
import calendar.Appt;
import calendar.CalDay;
import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

		private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 60 seconds */
		private static final int NUM_TESTS=200;
		/**
		 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
		 */
	    public static int RandomSelectRecur(Random random){
	        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

	    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
	        return RecurArray[n] ; // return the value of the  appointments to recur
	        }
		/**
		 * Return a randomly selected appointments to recur forever or Never recur  !.
		 */
	    public static int RandomSelectRecurForEverNever(Random random){
	        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

	    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
	        return RecurArray[n] ; // return appointments to recur forever or Never recur
	        }
	   /**
	     * Generate Random Tests that tests Appt Class.
	     */
		 @Test
		  public void radnomtest()  throws Throwable  {

			 long startTime = Calendar.getInstance().getTimeInMillis();
			 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


			 System.out.println("Start testing...");

			try{
				for (int iteration = 0; elapsed < TestTimeout; iteration++) {
					long randomseed =System.currentTimeMillis(); //10
		//			System.out.println(" Seed:"+randomseed );
					Random random = new Random(randomseed);

					 int startHour=ValuesGenerator.getRandomIntBetween(random, -10, 24);
					 int startMinute=ValuesGenerator.getRandomIntBetween(random, -10, 60);
					 int startDay=ValuesGenerator.getRandomIntBetween(random, -10, 32);
					 int startMonth=ValuesGenerator.getRandomIntBetween(random, -10, 13);
					 int startYear=ValuesGenerator.getRandomIntBetween(random, -10, 2018);

					 String title=ValuesGenerator.getString(random);;
					 //String description="This is my birthday party.";
					 String description = ValuesGenerator.getString(random);
					 //String emailAddress= "xyz@gmail.com";
					 String emailAddress= ValuesGenerator.getString(random);

					 int startHour2=ValuesGenerator.getRandomIntBetween(random, -10, 24);
					 int startMinute2=ValuesGenerator.getRandomIntBetween(random, -10, 60);
					 int startDay2=ValuesGenerator.getRandomIntBetween(random, -10, 32);
					 int startMonth2=ValuesGenerator.getRandomIntBetween(random, -10, 13);
					 int startYear2=ValuesGenerator.getRandomIntBetween(random, -10, 2018);

					 String title2=ValuesGenerator.getString(random);;
					 //String description="This is my birthday party.";
					 String description2 = ValuesGenerator.getString(random);
					 //String emailAddress= "xyz@gmail.com";
					 String emailAddress2= ValuesGenerator.getString(random);

					 //Construct a new Appointment object with the initial data
					 //Construct a new Appointment object with the initial data
			         Appt appt = new Appt(startHour,
			                  startMinute ,
			                  startDay ,
			                  startMonth ,
			                  startYear ,
			                  title,
			                 description,
			                 emailAddress);

						appt.setValid();

						Appt appt2 = new Appt(startHour2,
										 startMinute2 ,
										 startDay2 ,
										 startMonth2 ,
										 startYear2 ,
										 title2,
										description2,
										emailAddress2);

				 appt2.setValid();
						GregorianCalendar currentDay = new GregorianCalendar(startYear, startMonth, startDay);
				 //if(!appt.getValid())continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					LinkedList<Appt> expected= new LinkedList<Appt>();
					 int newDay=ValuesGenerator.getRandomIntBetween(random, -10, 32);
					 int newMonth=ValuesGenerator.getRandomIntBetween(random, -10, 13);
					 int newYear=ValuesGenerator.getRandomIntBetween(random, -10, 2018);
					 currentDay = new GregorianCalendar(newYear, newMonth, newDay);
					 CalDay cd = new CalDay(currentDay);
					 cd.addAppt(appt);
					 cd.addAppt(appt2);
					 LinkedList<Appt> actual = cd.getAppts();
					 if (!appt2.getValid() || !appt.getValid() ) continue;
					 if (startHour2 > startHour) {
						 expected.add(appt);
						 expected.add(appt2);
					 } else if (startHour2 < startHour){
						 expected.add(appt2);
						 expected.add(appt);
					 } else {
						 expected.add(appt);
						 expected.add(appt2);
					 }
					 //System.out.println("expected:\n" + expected.toString());
					 //System.out.println("actual:\n" + actual.toString());
					 //if startDay ==
					 assertTrue(expected.equals(actual) );

					 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				        if((iteration%10000)==0 && iteration!=0 )
				              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

				}
			}
			}catch(NullPointerException e){

			}

			 System.out.println("Done testing...");
		 }
	}
