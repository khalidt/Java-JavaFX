package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonDem extends Application {
	
	Color grayBlue = new Color( .4, .4, .8, 1 );
	Pane demo = new Pane();
	HBox p1 = new HBox();// box for button then put Hbox in Pane

	Button bt1 = new Button( "  Random  " );
	Button bt2 = new Button( "  Reset   " );

	Circle c1 = new Circle(100, 100, 50, grayBlue);
	Circle c2 = new Circle(100, 125, 25, Color.WHITE);
	Circle c3 = new Circle(92, 120, 5, Color.BLUE);
	Circle c4 = new Circle(111, 120, 5, Color.BLUE);
	
	//Ellipse e1 = new Ellipse(50,80,50,80);
	
	/*Rectangle r1 = new Rectangle(double width, double height)
	 * Rectangle(double width, double height, Paint fill)
	 * Creates a new instance of Rectangle with the given size and fill.
	 */
	@Override
	public void start(Stage st) {
		bt1.setOnAction( new RandomColor() );
		bt2.setOnAction( new ResetColor() );

		p1.setSpacing( 40 );
		p1.getChildren().addAll(bt1, bt2);
		c1.setStroke(Color.BLACK);
		demo.getChildren().addAll( c1, c2, c3, c4,/*e1,*/ p1);

		Scene sn = new Scene( demo, 200, 200 ); // Set window size.
		st.setTitle("Button Demo");
		st.setScene(sn);
		st.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class RandomColor implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent e ){
			System.out.println("Random button clicked");
			c1.setFill(Color.color(Math.random(),Math.random(),Math.random() ));
		}
	}

	class ResetColor implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent e ){
			System.out.println("Reset button clicked");
			c1.setFill( grayBlue );
		}
	}
}
