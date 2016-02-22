package application;
/**
 * @author Siham
 */

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;



public class Main extends Application {
	
	Stage st;
	Pane pane = new Pane();
	Scene scene = new Scene( pane, 800, 600 );
	Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 22);
	Circle l_eye = new Circle();
	Circle r_eye = new Circle();
	Circle up = new Circle();
	Circle mid = new Circle();
	Circle lower = new Circle();
	Circle moth = new Circle();
	Text lable_msg = new Text( 3 , scene.getHeight() - 10, "Pleas click to put the center of the head");
	Line arm1 = new Line();
	Line arm2 = new Line();
	private String option; // store the option of color
	private double click_x, click_y;
	private int clic_count = 0;
	//---------------------------------------------------------------------------------------
	Background color1 = new Background( 
			new BackgroundFill( Color.YELLOW,null,null));//new Color( .3, .5, .5, 1), null, null) );
	Background color2 = new Background(
			new BackgroundFill( Color.PINK,null,null));//new Color( .8, .1, .2, 1 ), null, null) );

	
	//---------------------------------------------------------------------------------------
	// let user choose the background
	private void set_background() {
		// get input from user
		Scanner sc = new Scanner( System.in );
		System.out.println("Please choose a color for background:\n" + 
							"Y = Yellow\n" +
							"P = Pink");
		option = sc.next();
		

		boolean status = true;
		while( status ) {
			switch(option) {
				case "Y": pane.setBackground( color1 ); status = false; break;
				case "P": pane.setBackground( color2 ); status = false; break;
				default : System.out.println(" Wrong value! Please enter a correct value:"); option = sc.next();
			}	
		}
		
		sc.close();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	// build GUI
	private void buildGui() {
		lable_msg.setFont(font );
		set_background();
		
		up.setFill( Color.WHITE );
		up.setStroke(Color.BLACK);
		
		mid.setFill( Color.WHITE );
		mid.setStroke(Color.BLACK);

		lower.setFill( Color.WHITE );
		lower.setStroke(Color.BLACK);

		l_eye.setFill( Color.WHITE );
		l_eye.setStroke(Color.BLACK);
		r_eye.setFill( Color.WHITE );
		r_eye.setStroke(Color.BLACK);

		moth.setFill( Color.WHITE );
		moth.setStroke(Color.BLACK);
		
		l_eye.setRadius(8);
		r_eye.setRadius(8);
		moth.setRadius(10);
		
		pane.getChildren().addAll( up, mid, lower, l_eye, r_eye, moth, lable_msg, arm1, arm2 );
	}
	

	public void start(Stage st) {
		this.st = st;
		
		try {
		buildGui();
		
		scene.setOnMouseClicked( new mous_events() );
		
		arm1.setOnMouseDragged( e->{
			arm1.setEndX( e.getSceneX() );
			arm1.setEndY( e.getSceneY() );
		});
		
		arm2.setOnMouseDragged( e->{
			arm2.setEndX( e.getSceneX() );
			arm2.setEndY( e.getSceneY() );
		});
		}catch(Exception e){ e.printStackTrace(); }
		
		st.setTitle("Snowman by Siham");
		st.setScene( scene );
		st.show();
	}
	
	class mous_events implements EventHandler<MouseEvent> {
		public void handle( MouseEvent m ) {
			clic_count++;
			switch( clic_count ) {
				case 1:
					up.setCenterX( m.getSceneX() );
					up.setCenterY( m.getSceneY() );
					up.setRadius( scene.getHeight() * .15);
					lable_msg.setText("click again below the first click.");
					click_x = m.getSceneX();
					click_y = m.getSceneY();
					break;
				case 2:
					mid.setCenterX( click_x );
					mid.setCenterY( m.getSceneY() );
					mid.setRadius( m.getSceneY() - click_y - up.getRadius() );
					lable_msg.setText("click again below the second click.");
					click_y = m.getSceneY();
					break;
				case 3:
					lower.setCenterX( click_x );
					lower.setCenterY( m.getSceneY() );
					lower.setRadius( m.getSceneY() - click_y - mid.getRadius() );
					lable_msg.setText("Pleas click  on the upper circle for putting left eye.");
					click_y = m.getSceneY();
					break;
				case 4:
					l_eye.setCenterX( m.getSceneX() );
					l_eye.setCenterY( m.getSceneY() );
					lable_msg.setText("Pleas click on the upper circle for putting right eye.");
					break;
				case 5:
					r_eye.setCenterX( m.getSceneX() );
					r_eye.setCenterY( m.getSceneY() );
					lable_msg.setText("Pleas click on the upper circle for putting mouth. ");
					break;
				case 6:
					moth.setCenterX( m.getSceneX() );
					moth.setCenterY( m.getSceneY() );
					lable_msg.setText("Pleas click to draw an arm");
					break;
				case 7:
					arm1.setStartX( m.getSceneX() );
					arm1.setStartY( m.getSceneY() );
					arm1.setEndX( m.getSceneX() );
					arm1.setEndY( m.getSceneY() );
					lable_msg.setText("dragged until you are finished then click to draw full arm.");
					break;
				case 8:
					arm1.setEndX( m.getSceneX() );
					arm1.setEndY( m.getSceneY() );
					lable_msg.setText("Pleas click for drawing an other arm.");
					break;
				case 9:
					arm2.setStartX( m.getSceneX() );
					arm2.setStartY( m.getSceneY() );
					arm2.setEndX( m.getSceneX() );
					arm2.setEndY( m.getSceneY() );
					lable_msg.setText("dragged until you are finished then click to draw full arm.");
					break;
				case 10:
					arm2.setEndX( m.getSceneX() );
					arm2.setEndY( m.getSceneY() );
					lable_msg.setText("Hello Snow man ^_^.");
					break;
			}
		}
	}

}