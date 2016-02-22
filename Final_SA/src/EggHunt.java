//package eggHunt;
/**
 * Snakes in the eggList.
 * EggHunt.java
 *
 * @author Alice Fischer
 * @version March 18, 2012, from version of 4/30/07.
 */
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;         // Text
import javafx.geometry.*;           // for Insets
import javafx.animation.*;
import javafx.util.*;

import java.util.List;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

//==============================================================================
public class EggHunt extends Application {
    // These are for the command-line arguments.
    private int speed;
    double radiusSq;
    private long lifetime;

    // This is the model.
    private boolean done = false;
    int eggs = 1;           // The number of eggs currently available.
    int laid = 0;           // The number of eggs the chick has laid.
    final int goal = 15;    // the number of eggs for chick to win.

    private ArrayList< Egg > eggList = new ArrayList<Egg>();
    ObservableList< Egg > oEggList = FXCollections.observableArrayList(eggList);

    // This class is used to produce a concurrent threads that shares the model.
    private Chick C;
    //private Referee R;

    // These variables are part of the GUI view.
    Text mouse = new Text(" ");               // Click coordinate display
    Text message = new Text(" ");             // Win or lose message

    BorderPane mainPane = new BorderPane();
    Pane grass = new Pane();                    // Display of the eggList.
    Scene sn;

    //--------------------------------------------------------------------------
    // Chick will stop laying when done becomes true.
    public boolean isDone()   		   { return done; }
    public long getLifetime()   	   { return lifetime; }

    //==========================================================================
    private void buildGui() {
        Color bColor = new Color(.95, 1, .8, 1.);
        BackgroundFill bFill = new BackgroundFill( bColor, null, new Insets(2));
        Background bkGrass = new Background( bFill );
        grass.setBackground( bkGrass );

        VBox p2 = new VBox();                       // Scoreboard.
        p2.getChildren().addAll(message, mouse);

        mainPane.setCenter(grass);
        mainPane.setBottom(p2);
        sn = new Scene( mainPane, 400, 430 );
        System.out.println("Gui is built.");
    }

    /*=========================================================================
     * @version March 2015, from versions of March 18, 2012, 4/30/07.
     *
     * @param arg[0] Base frequency at which the hen lays eggs, in milliseconds.
     * @param arg[1] Distance from snake's birthplace at which he can eat an egg.
     * @param arg[2] Lifetime of a snake, in milliseconds.
     */
    public void start( Stage st )   throws InterruptedException  {

        // Get and process the command line arguments --------------------------
        Application.Parameters params = getParameters();
        final java.util.List<String> argv = params.getRaw();

        // If user supplied command-line arguments, use them.
        if (argv.size() == 3) {
            speed = Integer.parseInt(argv.get(0));
            double radius = Double.parseDouble( argv.get(1) );
            radiusSq = radius * radius;
            lifetime = Long.parseLong( argv.get(2) );
            System.out.println("Arg 0 = " + speed + ", Arg 1 = " + radius +
                               ", Arg 2 = " + lifetime);
        }
        else {	// Set the parameters to relatively small values.
            speed = 0;
            radiusSq = 900;
            lifetime = 2000;
        }
        //----------------------------------------------------------------------
        grass.setOnMouseClicked( e -> { doClick( e ); } );

        oEggList.addListener( new ListChangeListener(){
            public void onChanged( ListChangeListener.Change change) {
                int eggs = oEggList.size();  // Current size of arraylist.
                System.out.println ("  List change event caught");

                if (eggs >= goal) {
                    done = true;
                    tracker("  I won!  There are " + eggs + " eggs left!");
                }
                else if (eggs == 0) {  // Announce the score.
                    done = true;
                    tracker("  Super! You won!" );
                }
                else {
                    tracker("  There are " + eggs + " eggs.");
                }
            }
        } );
        
        //----------------------------------------------------------------------
        buildGui();
        st.setTitle("Snakes in the Grass");
        st.setScene( sn );
        st.show();

        // Get a new Chicken and tell it to start laying eggs.
        C = new Chick( this, speed );
        C.start();
    }

    //--------------------------------------------------------------------------
    public void tracker( final String s ){
        Platform.runLater( new Runnable() {
            public void run() {
                message.setText( s );
            }
        });
    }

    //==========================================================================
    // Handle a mouse click by spawning a Snake thread for that board position.
    public void doClick( MouseEvent ev ){
        double  x = ev.getX();
        double  y = ev.getY();
        mouse.setText("  New snake at ("+ x +", " +y +")" );
        System.out.print("  New snake at ("+ x +", " +y +")  " );
        Snake hiss = new Snake( this, ev, lifetime );
        hiss.start();
    }
    public static void main(String[] args) {
		Application.launch(args);
	}
}
