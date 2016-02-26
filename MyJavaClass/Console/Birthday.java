//  Starting point for Program 2
//  Birthday.java
//  Die
//
import java.util.*;

//------------------------------------------------------------------------------
public class Birthday{
    public static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May",
    "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    public static final String[] days = { "Sunday", "Monday", "Tuesday",
    "Wednesday", "Thursday", "Friday", "Saturday" };
    public static final int[] startsOn = {4, 0, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2, };

    // The collection of data members stores the STATE of an object.
    // Data members of a class are normally private.   Document each one.
    private String month;   // 3-letter abbreviation for the month.
    private int date;       // Will be 1..31
    private String day;     // The day of the week.

    //--------------------------------------------------------------------------
    // Compute a new random value for the die.
    // Postcondition: the return value is between 1 and faces.
    Birthday( String m, int d){
        month = m;
        date = d;
        calculateDay();
    }

    //--------------------------------------------------------------------------
    private void calculateDay() {
        int found, k, answer;
        for(k=0; k<12; ++k) {
            if (months[k].equals(month))  break;
        }
        found = k;
        if (found == 12)
            System.out.println("Your month name was not a valid 3-letter abbreviation.");
        else {
            answer = (startsOn[k] + date -1)%7;
            day = days[answer];
        }
    }

    //---------- A get function gives read-only access to a private data member.
    public String getDay(){  return day;  }

    //--------------------------------------------------------------------------
    // Define toString for every class.
    // Return a string that reports the state of the class. Used for debugging.
    public String toString(){
        return month +" " + date ;
    }

    //--------------------------------------------------------------------------
    public static  void  main( String[] args ) {
        int date;
        String monthname;
        Scanner sc = new Scanner( System.in );

        System.out.println("\nBirthday Day Calculator, Welcome!");
        System.out.print ("Months are: ");
        for( String s : months) System.out.print( s+"  " );

        System.out.println("\n\nPlease enter your birth month and date:");
        monthname = sc.next();
        date = sc.nextInt();
        Birthday b = new Birthday (monthname, date);

        System.out.printf ( "Your %s birthday is on %s this year\n\n",
                           b.toString(), b.getDay() );
    }
}
