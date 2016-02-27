import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// This is a very simple JavaFX application.
public class Tree extends Application {
    // This is the viewer --------------------------------------------
    Circle r1, r2, r3, g1, g2, g3, b1, b2, b3, y1, y2, y3, o1, o2, o3;

    Color blueColor = new Color(.42,.65, .99, 1);
    Color skyColor = new Color(.7, .85, 1, .5);
    Color needleColor = new Color(.27,.38,.19, 1);
    Color shadowColor = new Color(.2, .27, .313, 1);
    Color trunkColor = new Color(.45,.23,0, 1);
    Color noColor = new Color(0,0,0,0);  // Transparent

    // This is the controller ----------------------------------------
    CheckBox blueBox = new CheckBox("Blue");
    CheckBox greenBox = new CheckBox("Green");
    CheckBox orangeBox = new CheckBox("Orange");
    CheckBox redBox = new CheckBox("Red");
    CheckBox yellowBox = new CheckBox("Yellow");
    ToggleButton powerButton = new ToggleButton("Turn on");

    Pane imagePane;
    HBox demo = new HBox();
    VBox colorPane = new VBox( 35 );

    //@Override
    public void start( Stage st ){
        redBox.setOnAction( new RedLight() );
        greenBox.setOnAction( new GreenLight() );
        blueBox.setOnAction( new BlueLight() );
        yellowBox.setOnAction( new YellowLight() );
        orangeBox.setOnAction( new OrangeLight() );
        powerButton.setOnAction( new Power() );

        imagePane = buildImage();
        colorPane.getChildren().addAll( blueBox, greenBox, orangeBox, redBox,
                                       yellowBox, powerButton );
        demo.getChildren().addAll( imagePane, colorPane );
        Scene sn = new Scene( demo, 375, 300 ); // Set window size.
        st.setTitle("Christmas Tree");
        st.setScene(sn);
        st.show();
    }

    Pane buildImage(){
        double w = 300;
        double h = 300;
        double mid = 150;

        Rectangle sky = new Rectangle( 0,0,w,h );
        sky.setFill(skyColor);
        h -= 40;
        Rectangle trunk = new Rectangle( mid-20, h, 40, 30 );
        trunk.setFill(trunkColor);

        // Add the tree branches --------------------------------------
        Polygon layer1 = new Polygon();
        layer1.setFill (shadowColor);
        layer1.getPoints().addAll(new Double[]{mid, h-180, mid-90, h, mid+90, h});
        h -= 20;

        Polygon layer2 = new Polygon();
        layer2.setFill(needleColor);
        layer2.getPoints().addAll(new Double[]{mid, h-180, mid-100, h, mid+100,h});
        h -= 80;

        Polygon layer3 = new Polygon();
        layer3.setFill (shadowColor);
        layer3.getPoints().addAll(new Double[]{mid, h-120, mid-70, h, mid+70, h});
        h -= 20;

        Polygon layer4 = new Polygon();
        layer4.setFill(needleColor);
        layer4.getPoints().addAll(new Double[]{mid, h-120, mid-80, h, mid+80, h});

        Pane tree = new Pane(sky, trunk, layer1, layer2, layer3, layer4 );

        // Now add the lights-------------------------
        r1 = new Circle(mid-20, h-80, 6, needleColor);
        r2 = new Circle(mid-20, h+10, 6, shadowColor);
        r3 = new Circle(mid+60, h+86, 6, needleColor);
        g1 = new Circle(mid-45, h-40, 6, needleColor);
        g2 = new Circle(mid-16, h+50, 6, needleColor);
        g3 = new Circle(mid+40, h+100, 6, noColor);
        b1 = new Circle(mid+60, h-20, 6, needleColor);
        b2 = new Circle(mid+20, h-56, 6, needleColor);
        b3 = new Circle(mid-52, h+60, 6, needleColor);
        y1 = new Circle(mid-10, h-36, 6, needleColor);
        y2 = new Circle(mid+30, h+40, 6, needleColor);
        y3 = new Circle(mid-50, h+100, 6, noColor);
        o1 = new Circle(mid, h-120, 6, skyColor);
        o2 = new Circle(mid+20, h-24, 6, needleColor);
        o3 = new Circle(mid+10, h+80, 6, needleColor);
        tree.getChildren().addAll(r1,r2,r3, g1,g2,g3, b1,b2,b3, y1,y2,y3, o1,o2,o3);
        return tree;
    }

    //---------------------------------------------------------
    void setAll(){
        setBlue(); setGreen(); setOrange(); setRed(); setYellow();
    }
    //---------------------------------------------------------
    void setBlue(){
        if (blueBox.isSelected()) {
            b1.setFill( Color.BLUE );
            b2.setFill( Color.BLUE );
            b3.setFill( Color.BLUE );
        }
    }
    void setGreen(){
        if (greenBox.isSelected()) {
            g1.setFill( Color.GREEN );
            g2.setFill( Color.GREEN );
            g3.setFill( Color.GREEN );
        }
    }
    void setOrange(){
        if (orangeBox.isSelected()){
            o1.setFill( Color.ORANGE );
            o2.setFill( Color.ORANGE );
            o3.setFill( Color.ORANGE );
        }
    }
    void setRed(){
        if (redBox.isSelected()){
            r1.setFill( Color.RED );
            r2.setFill( Color.RED );
            r3.setFill( Color.RED );
        }
    }
    void setYellow(){
        if (yellowBox.isSelected() ){
            y1.setFill( Color.YELLOW );
            y2.setFill( Color.YELLOW );
            y3.setFill( Color.YELLOW );
        }
    }

    //---------------------------------------------------------
    void turnOffAll() {
        turnOffBlue();
        turnOffGreen();
        turnOffOrange();
        turnOffRed();
        turnOffYellow();
    }

    //---------------------------------------------------------
    void turnOffBlue(){
        b1.setFill( needleColor );
        b2.setFill( needleColor );
        b3.setFill( needleColor );
    }
    void turnOffGreen(){
        g1.setFill( needleColor );
        g2.setFill( needleColor );
        g3.setFill( noColor );
    }
    void turnOffOrange(){
        o1.setFill( noColor );
        o2.setFill( needleColor );
        o3.setFill( needleColor );
    }
    void turnOffRed(){
        r1.setFill( needleColor );
        r2.setFill( shadowColor );
        r3.setFill( needleColor );
    }
    void turnOffYellow(){
        y1.setFill( needleColor );
        y2.setFill( needleColor );
        y3.setFill( noColor );
    }
    //-------------------------------------------------------------------------
    class Power implements EventHandler<ActionEvent> {
        public void handle( ActionEvent e ){
            System.out.print ("Power button toggled: ");
            if( powerButton.isSelected() ) {
                setAll();
                powerButton.setText( "Turn off");
            }
            else {
                turnOffAll();
                powerButton.setText( "Turn on");
            }
        }
    }

    //--------------------------------------------------------------------------
    class RedLight implements EventHandler<ActionEvent> {
        public void handle( ActionEvent e ){
            System.out.print ("Red box toggled: ");
            if ( powerButton.isSelected() && redBox.isSelected() ) setRed();
            else turnOffRed();
        }
    }

    //--------------------------------------------------------------------------
    class GreenLight implements EventHandler<ActionEvent> {
        public void handle( ActionEvent e ){
            System.out.print ("Green box toggled: ");
            if ( powerButton.isSelected() && greenBox.isSelected() ) setGreen();
            else turnOffGreen();
        }
    }
    //--------------------------------------------------------------------------
    class BlueLight implements EventHandler<ActionEvent> {
        public void handle( ActionEvent e ){
            System.out.print ("Blue box toggled: ");
            if ( powerButton.isSelected() && blueBox.isSelected() ) setBlue();
            else turnOffBlue();
        }
    }
    //--------------------------------------------------------------------------
    class YellowLight implements EventHandler<ActionEvent> {
        public void handle( ActionEvent e ){
            System.out.print ("Yellow box toggled: ");
            if ( powerButton.isSelected() && yellowBox.isSelected()) setYellow();
            else turnOffYellow();
        }
    }

    //--------------------------------------------------------------------------
    class OrangeLight implements EventHandler<ActionEvent> {
        public void handle( ActionEvent e ){
            System.out.print ("Orange box toggled: ");
            if ( powerButton.isSelected() && orangeBox.isSelected()) setOrange();
            else turnOffOrange();
        }
    }
}
