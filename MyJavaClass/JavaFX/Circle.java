import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.paint.*;
import javafx.scene.shape.Circle;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.animation.*;
import javafx.util.Duration;

/** This is a very simple JavaFX application.
 */
public class TimerDemo extends Application {
    Color grayBlue = new Color( .4, .4, .8, 1 );
    Pane demo = new Pane();

    Circle c1 = new Circle(100, 100, 50, grayBlue);
    Circle c2 = new Circle(100, 125, 25, Color.WHITE);
    Circle c3 = new Circle(92, 120, 5, Color.BLUE);
    Circle c4 = new Circle(111, 120, 5, Color.BLUE);

    //@Override
    public void start( Stage st )  throws InterruptedException {
        Timeline timeline =
            new Timeline(
                new KeyFrame( Duration.millis(1500), new Tick())
        );
        timeline.setCycleCount(5);
        
        c1.setStroke(Color.BLACK);
        demo.getChildren().addAll( c1, c2, c3, c4 );

        Scene sn = new Scene( demo, 200, 200 ); // Set window size.
        st.setTitle("Timer Demo");
        st.setScene(sn);
        st.show();
        Thread.sleep(5000);
        //Thread.sleep(5000);
    }

    class Tick implements EventHandler<ActionEvent> {
        @Override
        public void handle( ActionEvent e ){
            System.out.println("Timer ticked");
            c1.setFill(Color.color(Math.random(),Math.random(),Math.random() ));
        }
    }
}
