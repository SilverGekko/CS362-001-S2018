package calendar;

import java.util.*;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	/**
	 * Return a randomly selected method to be tests !.
	 */
		public static String RandomSelectMethod(Random random){
				String[] methodArray = new String[] {"deleteAppt","getApptRange"};// The list of the of methods to be tested in the Appt class

			int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

				return methodArray[n] ; // return the method name
				}
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 60 seconds */
	private static final int NUM_TESTS=100;
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
				 int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			   int[] recurDays=ValuesGenerator.generateRandomArrayWithNull(random, sizeArray);
			   int recur=ApptRandomTest.RandomSelectRecur(random);
			   int recurIncrement = ValuesGenerator.RandInt(random);
			   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
			   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);


					appt.setValid();

					Appt appt2 = new Appt(startHour2,
									 startMinute2 ,
									 startDay2 ,
									 startMonth2 ,
									 startYear2 ,
									 title2,
									description2,
									emailAddress2);
				 appt2.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
			 appt2.setValid();
					GregorianCalendar currentDay = new GregorianCalendar(startYear, startMonth, startDay);
			 //if(!appt.getValid())continue;
			for (int i = 0; i < NUM_TESTS; i++) {
				String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
				if (methodName.equals("deleteAppt")){

					int newDay=ValuesGenerator.getRandomIntBetween(random, -10, 32);
					int newMonth=ValuesGenerator.getRandomIntBetween(random, -10, 13);
					int newYear=ValuesGenerator.getRandomIntBetween(random, -10, 2018);
					currentDay = new GregorianCalendar(startYear, startMonth, startDay);
					GregorianCalendar today = new GregorianCalendar(startYear, startMonth, startDay);
					GregorianCalendar tomorrow = new GregorianCalendar(startYear2, startMonth2, startDay2);
					CalDay cd = new CalDay(currentDay);
					boolean type;
					int type_int = random.nextInt(100) + 1;
					if (type_int % 2 == 0)
							type = false;
					else
							type = true;
					DataHandler dh = new DataHandler("calendar_test" + i + "_" + Boolean.toString(type) + ".xml", type);
					dh.saveAppt(appt);
					dh.saveAppt(appt2);
					dh.deleteAppt(appt);
					dh.deleteAppt(appt2);
					dh.save();
					dh.deleteAppt(appt);

					try {
						assertNotNull(dh.getApptRange(today, tomorrow) );
					} catch (DateOutOfRangeException e) {
						continue;
					}
			}
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
