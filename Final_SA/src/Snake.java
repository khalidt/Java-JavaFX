//package eggHunt;
/**
 * Snakes in the Grass.
 * Snake.java
 *  
 * @author Alice Fischer
 * @version March 18, 2012, from version of 4/30/07.
 */

import javafx.geometry.Point2D;     // for the click-point
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;

public class Snake extends Thread {
	private EggHunt hunt;
    private MyPoint p;      // The point at which the snake is born.
	private final long death;
	private static int shared=1;
	private int serial;

	//--------------------------------------------------------------------------
	public Snake( EggHunt hunt, MouseEvent ev, long lifetime ) {
		this.hunt = hunt;
		serial = shared++;
        p = new MyPoint( ev.getX(), ev.getY());
		death = lifetime + System.currentTimeMillis();
 	}
	//--------------------------------------------------------------------------
	public void run() { 
		int eggs; 
		long now = 0;   // Initialize it to make eclipse happy.
		do { 
			eggs = eatEggs();
			System.out.printf("Snake %d found %d eggs.\n", serial, eggs);  
			try {
				synchronized( hunt ) {
					// Hibernate until Chick lays another egg.
					hunt.wait( death - System.currentTimeMillis() );
					hunt.notify();
				}
			}
			catch (InterruptedException e) { continue; }

			now = System.currentTimeMillis();
		} while ( now < death );
		// Snake will die a natural death now.
	}

    //--------------------------------------------------------------------------
    // Eats the eggs that are close enough to point p.
    public int eatEggs() { 	// Called by the Snakes.
        int ate = 0;
        Egg egg;
        double dSq;

        // Lock the shared eggList before searching it.
        synchronized (this) {
            for(int k=0; k<hunt.oEggList.size(); ) {
                egg = hunt.oEggList.get(k);
                dSq = p.distanceSq( egg.getCenter());
                if (dSq < hunt.radiusSq) {
                    eatFromGrass( hunt.oEggList.remove( k ));
                    ++ate;
                }
                else ++k;
            }
        }
        return ate;
    }


   //--------------------------------------------------------------------------
   public void eatFromGrass( final Egg eg ){
        Platform.runLater( new Runnable() {
            public void run() {
                hunt.grass.getChildren().remove( eg );
            }
        });
    }
}
