package calendar;
/*
 * CalendarMain.java
 */
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;





public class CalendarMainBug1 {
	public static void main(String[] args) throws DateOutOfRangeException, IOException {
		System.out.println("Calendar Main 2: \n" );
		Appt Appt0a = new Appt(15, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);
		Appt Appt0b = new Appt(9, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);
		String string1 = Appt0a.toString();
		String string2 = Appt0b.toString();

		System.out.print(string1);
		System.out.print(string2);

    System.exit(0);

	}
}
