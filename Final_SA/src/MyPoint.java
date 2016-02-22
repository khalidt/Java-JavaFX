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

public class MyPoint {
    double x;
    double y;

    //--------------------------------------------------------------------------
    public MyPoint( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    double distanceSq( Point2D pt2 ){
        double deltaX = x - pt2.getX();
        double deltaY = y - pt2.getY();
        return deltaX * deltaX + deltaY * deltaY;
    }
}