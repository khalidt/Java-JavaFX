/**
 * @author khalid
 *	
 */

import java.util.*;

public class Birthday {

	public static final String[] months = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	public static final int[] numberofDaysInAmonth = { 31, 28, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31 };
	public static final String[] days = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };
	public static final int[] startsOn = { 4, 0, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2, };
	private String month; // 3-letter abbreviation.
	private int date; // Will be 1..31
	private String day; // The day of the week.
	// ishould use two variable to know which is the month and the number of day
	// in the year
	private int daynumber; // this is the number of day in the year
	private int numberOfmonth; // its from 1 .. 12

	Birthday(String m, int d) {
		month = m;
		date = d;
		calculateDay();
	}

	private void calculateDay() {
		int found, k, answer;
		for (k = 0; k < 12; ++k) {
			if (month.equals(months[k]))
				break;
		}
		found = k;
		if (found == 12)
			System.out.println("Your month name was not....");
		else {
			answer = (startsOn[k] + date - 1) % 7;
			day = days[answer];
			numberOfmonth = k; // k= the number of month so i will pass it to
								// numberOfmonth to save it
		}

	}

	public int dayofyear(int x) { // x is the date of birthday to add it with
									// numberOfmonth

		daynumber = 0; // number of days in the year
		daynumber += x;// i add the date first to calculate it with
						// numberofDaysInAmonth
		for (int g = 0; g < numberOfmonth; g++)
			daynumber += numberofDaysInAmonth[g];
		return daynumber;

	}

	public String getday() {
		return day;
	}

	public String toString() {
		return " ";
	}

	public static void main(String[] args) {

		int date;
		String monthname;
		Scanner sc = new Scanner(System.in);
		System.out.println("\nBirthday Calculator, Welcome!");
		System.out.print("Months are: ");
		for (String s : months)
			System.out.print(s + " ");
		System.out.println("\n\nEnter birth month and date:");
		monthname = sc.next();
		date = sc.nextInt();
		Birthday b = new Birthday(monthname, date);
		System.out.printf("Your %s birthday is on %s this year\n\n",
				b.toString(), b.getday());
		System.out.println("The number Of day in the year is "+	b.dayofyear(date));
		sc.close();

	}
}
/*
Birthday Calculator, Welcome!
Months are: Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec 

Enter birth month and date:
Feb 1
Your   birthday is on Sunday this year

The number Of day in the year is 32
---------------------------------------
Birthday Calculator, Welcome!
Months are: Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec 

Enter birth month and date:
Jan 1
Your   birthday is on Thursday this year

The number Of day in the year is 1
------------------------------------
Enter birth month and date:
Dec 31
Your   birthday is on Thursday this year

The number Of day in the year is 365



*/