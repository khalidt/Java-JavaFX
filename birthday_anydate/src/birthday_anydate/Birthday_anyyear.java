package birthday_anydate;

/**
 * @author khalid
 *	
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Birthday_anyyear {

	public static void main(String argu[]) throws ParseException {
		Scanner sc = new Scanner(System.in);
		String input;
		String[] Day = { "Sunday", "Monday", "Tuesday", "Wednesday","Thursday", "Friday", "Saturday" };
		System.out.print("please Enter Your Birthday [DD/MM/YYYY]:");
		input = sc.nextLine();
		int day_num;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); // using calendar to see
													// which was the day
		date = new SimpleDateFormat("dd/MM/yyyy").parse(input); // convert input
																// string date
																// to date
		calendar.setTime(date);// set the calendar on the date that was entered
		day_num = calendar.get(Calendar.DAY_OF_WEEK);// get the number of day
														// from the calendar
		System.out.println("You were born on: " + Day[day_num - 1]);
	}
}
/*---------output---------------
please Enter Your Birthday [DD/MM/YYYY]:19/01/1988
You were born on: Tuesday

please Enter Your Birthday [DD/MM/YYYY]:19/01/2015
You were born on: Monday

*/