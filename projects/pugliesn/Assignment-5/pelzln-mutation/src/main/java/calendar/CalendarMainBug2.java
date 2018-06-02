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





public class CalendarMainBug2 {
	public static void main(String[] args) throws DateOutOfRangeException, IOException {
		System.out.println("Calendar Main 2: \n" );
		Appt Appt0a = new Appt(15, 30, 4, 30, 2017, "test appt please ignore", "This is another test", null);
		Appt0a.setDescription("breakpoint testing");
		String string1 = Appt0a.toString();

		System.out.print(string1);

    System.exit(0);

	}
}
