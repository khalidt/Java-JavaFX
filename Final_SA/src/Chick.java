//package eggHunt;
/**
 * Snakes in the Grass.
 * Chick.java
 *
 * @author Alice Fischer
 * @version March 18, 2012, from version of 4/30/07.
 */
import javafx.application.Platform;

public class Chick extends Thread {
    private EggHunt hunt;
    private int speed;
    //--------------------------------------------------------------------------
    public Chick( EggHunt hunt, int speed ) {
        this.hunt = hunt;
        this.speed = speed;
    }

    //--------------------------------------------------------------------------
    /** The hen alternates between laying eggs and taking naps.
     *  The sleep command puts a thread into a suspended state. When the nap is
     *  over the interrupt system will throw an exception, which we receive in
     *  the catch block.
     */
    public void run()  {
        System.out.println(" Chick is running. ");
        try {
            while ( ! hunt.isDone() ){
                Egg e = new Egg(10+Math.random()*380, 10+Math.random()*380 );
                synchronized (hunt) {
                    hunt.oEggList.add( e );
                    hunt.laid++;
                }
                layOnGrass( e );
                 // Lay eggs at slightly random times and take naps between eggs.
                sleep (speed + (int)(Math.random() * 1000 ));  // milliseconds
            }
        }
        // Come here when the nap is over.
        catch (InterruptedException e) { return; }
    }
    

   //--------------------------------------------------------------------------
   public void layOnGrass( final Egg eg ){
        Platform.runLater( new Runnable() {
            public void run() {
                hunt.grass.getChildren().add( eg );
            }
        });
    }

   //--------------------------------------------------------------------------
   public void tracker( final String s ){
        Platform.runLater( new Runnable() {
            public void run() {
                hunt.message.setText( s );
            }
        });
    }
}