package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;            // For rectangles, circles, etc.
import javafx.geometry.*;               // For centering

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Main extends Application {
	
    // First, make the boxes for entering data
    TextField red = new TextField("0");
    TextField green = new TextField("0");
    TextField blue = new TextField("0");

    Label rLabel = new Label("   red");          // text to display.
    Label gLabel = new Label(" green");
    Label bLabel = new Label("  blue");

    Button submit = new Button ("Submit");
    Button reset = new Button ("Reset");

    // Here are the parts of the image. ----------------------------------------
    Circle face = new Circle(150, 130, 110, Color.BLACK);
    Circle leftEye = new Circle(115, 115, 10, Color.BLACK);
    Circle rightEye = new Circle(180, 115, 10, Color.BLACK);
    Arc smile = new Arc(150, 85, 120, 120, 233, 72);

    Pane cartoonPane = new Pane();                   // Holds the cartoon.
    BorderPane mainPane = new BorderPane();
    Scene sn = new Scene( mainPane, 300,300 );
    // -----------------------------------------------------------------
    // This is the MODEL that stores the state of the application.
    // -----------------------------------------------------------------
    // Variable for storing input values.   These must be class members because
    // they are set by one function and used by another function.
    private double rd, gr, bl, op;     // Codes for a color in the RGB-O.

    // -------------------------------------------------------------------
    //  This constructor creates the GUI and activates its buttons.
	
	
    
    public void start(Stage st) {
	
        buildGui();					// Assemble the parts of the window.

        // Make the action parts active. Use "this" to make the Smile object
        // listen for events on the submit and reset buttons.
        submit.setOnAction( new SubmitAction() );
        reset.setOnAction( new ResetAction() );

        st.setTitle("Smile");
        st.setScene(sn);
        st.show();

    	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 //----------------------------------------------------------------------
    class SubmitAction implements EventHandler<ActionEvent> {
        @Override
        public void handle( ActionEvent e ){
            rd = Double.parseDouble(red.getText());
            gr = Double.parseDouble(green.getText());
            bl = Double.parseDouble(blue.getText());
            if (bl<0. || bl>1. || rd<0. || rd>1. || gr<0. || gr>1.)
                System.out.println("Color values must be in the range 0. to 1.");
            else { // Use the current color settings for the face.
                Color c = Color.color(rd, gr, bl, 1);
                face.setFill( c );
                face.setStroke( Color.GREEN );
            }
       }
    }

    class ResetAction implements EventHandler<ActionEvent> {
        @Override
        public void handle( ActionEvent e ){
            rd = 0;  red.setText("0");
            gr = 0;  green.setText("0");
            bl = 0;  blue.setText("0");
            face.setFill( Color.BLACK );
      }
    }

    // -----
    public void buildGui(){
        // Build the image panel ----------------------------------------
        smile.setType( ArcType.OPEN );
        //smile.setFill(Color.GREEN);
        smile.setStroke(Color.BLACK);
        cartoonPane.getChildren().addAll( face, leftEye, rightEye, smile );

        // Build the control panel-----------------------------------------
        red.setPrefColumnCount(3);// this for TextFiled length 
        green.setPrefColumnCount(3);
        blue.setPrefColumnCount(3);

        GridPane gPane = new GridPane();   // The input panel.
        //gPane.setAlignment(Pos.CENTER);
        gPane.setHgap(5);
        gPane.setVgap(2);

        // Put the input boxes and their labels into the grid.
        gPane.add(red, 0, 0);
        gPane.add(rLabel, 0, 1);
        gPane.add(green, 1, 0);
        gPane.add(gLabel, 1, 1);
        gPane.add(blue, 2, 0);
        gPane.add(bLabel, 2, 1);
        gPane.add(submit,3,0);
        gPane.add(reset,4,0);

        // Put the grid pane and the button pane into the main pane.
        FlowPane controlPane = new FlowPane();   // The button panel.
        controlPane.setHgap(5);
        controlPane.getChildren().addAll( gPane );
        controlPane.setMargin(gPane, new Insets(3,3,0,3));
        // Put the grid pane and the button pane into the main pane.
        mainPane.setTop( controlPane );
        mainPane.setCenter( cartoonPane );
    }
	
	
}
