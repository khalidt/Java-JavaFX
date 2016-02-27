import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;

// This demo introduces shapes.
public class Binding extends Application {
    Color grayBlue = new Color( .4, .4, .8, 1 );

    Circle c1 = new Circle(100, 100, 50, grayBlue);		// large circle
    Circle c2 = new Circle(100, 125, 25, Color.WHITE);	// medium circle
    Circle c3 = new Circle(92, 120, 5, Color.BLUE);		// eye
    Circle c4 = new Circle(111, 120, 5, Color.BLUE);	// eye

    Pane demo = new Pane();

    public void start( Stage st ){
        st.setTitle("Binding Demo");
        c1.setStroke(Color.BLACK);
		
		// this will overwrite any previous coordinates set
		// binds both the x and y cordinates to the x/2 and y/2 of the pane size
		c1.centerXProperty().bind(demo.widthProperty().divide(2));
		c1.centerYProperty().bind(demo.heightProperty().divide(2));
		
		// bind the x & y coordinates to the bigger circle
		// so they will both move as the screen does
		c2.centerXProperty().bind(c1.centerXProperty());
		c2.centerYProperty().bind(c1.centerYProperty().add(25));
		
		// bind one of the eyes to to the medium circle
		c3.centerXProperty().bind(c2.centerXProperty().subtract(8));
		c3.centerYProperty().bind(c2.centerYProperty().subtract(5));

        // c4 is not bound -- it stays in its original position.

        demo.getChildren().addAll( c1, c2, c3, c4 );

        Scene sn = new Scene( demo, 200, 200 );        // Set window size.
        st.setScene(sn);
        st.show();
    }
}
