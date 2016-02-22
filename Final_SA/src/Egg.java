//package eggHunt;
/**
 *  Egg.java
 *  The Data class for EggCarton
 *  
 *  @author  Alice Fischer 
 *  @version March, 2015, from earlier versions of March 2013, 3/18/08, 11/28/04.
 */
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.*;
import javafx.geometry.Point2D;     // for the click-point

public class Egg extends Ellipse {
    private static double EggLong = 18;
    private static double EggHigh = 14;

    //--------------------------------------------------------------------------
    public Egg( double x, double y ) {
        setCenterX( x );
        setCenterY( y );
        setRadiusX( EggLong );
        setRadiusY( EggHigh );

        double r =  Math.random()* .5 + .4;          // Choose a color.
        double g =  Math.random()* .5 + .4;
        double b =  Math.random()* .5 + .4;
        setFill( new Color( r, g, b, 1) );
		System.out.printf("Egg at %f.2, %f.2\n", x, y);
    }

    Point2D getCenter() { return new Point2D(getCenterX(), getCenterY()); }
}