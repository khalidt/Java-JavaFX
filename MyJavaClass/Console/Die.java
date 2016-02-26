//  Demo for classes, class functions,
//  Die.java
//  Die
//
import java.util.*;

public class Die {
    private int faces;  // The number of sides on the die.
    private int value;	// The face that is showing.

    // Constructor:  initialize members.
    Die ( int faces ) {
        this.faces = faces;
        value = roll();
        System.out.println( this );
    }

    // Default constructor:
    Die ( ) {
        faces = 6;
        value = roll();
    }

    // Post: return is between 1 and faces.
    public int roll(){
        value = (int)(Math.random() * faces) + 1;
        return value;
    }

    public int getValue(){  return value;  }

    public String toString(){
        return "faces: " +faces +", value: " +value ;
    }

    //--------------------------------------------------------------------------
    public static  void  main( String[] args ) {
        int nSides;
        Die[] dice = new Die[4];
        Scanner sc = new Scanner( System.in );

        System.out.println("Roll Four Dice");
        System.out.print("  How many sides do your dice have? ");
        nSides = sc.nextInt();
        for( int k=0; k<4; ++k) dice[k] = new Die(nSides);

        System.out.println ( "Your dice have been rolled:" );
        for( int k=0; k<4; ++k) {
            dice[k].roll();
            System.out.print (dice[k].getValue() + "  ");
        }
        System.out.println();
    }
}
