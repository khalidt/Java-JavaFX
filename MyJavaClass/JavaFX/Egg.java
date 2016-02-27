import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.paint.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Arc;
import javafx.scene.text.*;
import javafx.geometry.*;               // For centering

import javafx.scene.control.*;
import javafx.scene.input.*;        // For KeyEvent and KeyCode
import javafx.event.EventHandler;

public class Egg3 extends Application
{
    // The model:  an egg, its size, and its position. -------------------------
    Text key, tx;
    Ellipse egg = new Ellipse(150, 150, 42, 50);
    Arc topHalf = new Arc(212, 130, 42, 50, 0, 180);
    Arc bottomHalf = new Arc(150, 150, 42, 50, 180, 180);
    Paint  eggColor;

    // Viewer elements -----------------------
    Stage st;
    Pane demo = new Pane();
    Scene sn = new Scene( demo, 300, 300 );

    public static final Color paleGray = new Color(.6, .6, .6, 1);
    public static final Color lightBlue = new Color(.4, .4, 1, 1);
    public static final Color lightGreen = new Color(.4, 1, .4, 1);
    Color silver = new Color(.75, .75, .75, 1);
    Color noColor = new Color(0, 0, 0, 0);
    Background bkSilver = new Background(
       new BackgroundFill(silver, null, new Insets(2)) );
    Background bkIvory = new Background(
       new BackgroundFill( Color.IVORY, null, new Insets(2)) );

    Font f = Font.font ("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16);

    //--------------------------------------------------------------------------
    public void start( Stage st )
    {
        this.st = st;
        buildGui();
        sn.setOnKeyReleased(new KeyPressed());

        // ----------------------------------------------
        st.setScene(sn);
        st.setTitle("Keyboard Demo");
        st.setScene(sn);
        st.show();
    }

    //--------------------------------------------------------------------------
    class KeyPressed implements EventHandler<KeyEvent> {
        public void handle( KeyEvent e ){
            double centerX = egg.getCenterX();
            double centerY = egg.getCenterY();
            double radiusX = egg.getRadiusX();
            double radiusY = egg.getRadiusY();

            bottomHalf.setFill( noColor );
            topHalf.setFill( noColor );
            egg.setFill( eggColor );

            KeyCode code = e.getCode();
            key.setText( code.toString() );
            System.out.println ("Typed key " + code.toString());
            switch ( code ) {
                case RIGHT: egg.setCenterX( centerX + 5); break;
                case LEFT:  egg.setCenterX( centerX - 5); break;
                case DOWN:  egg.setCenterY( centerY + 5); break;
                case UP:    egg.setCenterY( centerY - 5); break;
                case Q:     System.exit(0); break;
                case G: {
                    egg.setFill(eggColor = Color.GREEN);
                    key.setText("  Green");
                    break;
                }
                case A: {
                    egg.setFill(eggColor = Color.PINK);
                    key.setText("  Presto!");
                    break;
                }
                case B: {
                    egg.setFill(eggColor = Color.BLUE);
                    key.setText("  Blue");
                    break;
                }
                case ENTER: {
                    egg.setFill(eggColor = silver);
                    key.setText("  Silver");
                    break;
                }
               case Z: {
                    egg.setCenterX( centerX - 15);
                    egg.setCenterY( centerY + 20);
                    key.setText("  Jump");
                    break;
                }
                case TAB: {
                    egg.setCenterX( centerX + 15);
                    egg.setCenterY( centerY - 20);
                    key.setText("  Jump");
                    break;
                }
               case DIGIT2: {
                    egg.setRadiusX( radiusX * 1.05 );
                    egg.setRadiusY( radiusY * 1.05 );
                    key.setText(" Grow");
                    break;
                }
                case DIGIT1: {
                    egg.setRadiusX( radiusX * .95 );
                    egg.setRadiusY( radiusY * .95 );
                    key.setText(" Shrink");
                    break;
                }
                case SPACE: {
                    double temp = egg.getRadiusX();
                    egg.setRadiusX( egg.getRadiusY());
                    egg.setRadiusY( temp );
                    key.setText(" Roll Over");
                    break;
                }
                case ESCAPE: {
                    egg.setCenterX( 0 );
                    egg.setCenterY( 0 );
                    key.setText(" Let me out of here!");
                    break;
                }
                case I: {
                    bottomHalf.setFill( eggColor );
                    topHalf.setFill( eggColor );
                    egg.setFill(noColor);
                    key.setText(" Oops! Bye now!");
                    break;
                }
                default: {
                    key.setText(" We don't process that key.");
                   
                System.out.println( "We don't process that key.");
                }
            }
            // Wrap to other edge if egg is disappearing. ----------------------
            if (egg.getCenterX() > sn.getWidth())  egg.setCenterX( 0 );
            if (egg.getCenterX() < 0)  egg.setCenterX( sn.getWidth() );
            if (egg.getCenterY() > sn.getHeight())  egg.setCenterY( 0 );
            if (egg.getCenterY() < 0)  egg.setCenterY( sn.getHeight() );
        }
    }

    //--------------------------------------------------------------------------
    private void buildGui() {
        key = new Text(3, sn.getHeight()-10, "Type A to start." );
        key.setFont(f);

        egg.setFill( eggColor = noColor );
        bottomHalf.setFill( noColor );
        topHalf.setFill( noColor );
        topHalf.setRotate( 80 );

        demo.setBackground(bkIvory);
        demo.getChildren().addAll( egg, topHalf, bottomHalf, key );
    }
    
}
