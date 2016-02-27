import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.paint.*;
import javafx.scene.text.*;


/** This is a very simple JavaFX application.
 */
public class Spring extends Application {
    Font f1 = Font.font ("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24);
    Font f2 = Font.font ("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 20);
    Color c1 = new Color( 0, .5, .4, 1 );

    Text title = makeText(40, 40, "Spring", f1, Color.GREEN);
    Text line1 = makeText(50, 70, "Damp earth, rain, gray sky. ", f2, c1);
    Text line2 = makeText(50, 95, "Promise of spring soon to come,", f2, c1);
    Text line3 = makeText(50, 120, "Daffodil blooming.", f2, c1);
    Pane haiku = new Pane();

    //@Override
    public void start( Stage st1 ){
        haiku.getChildren().addAll(title, line1, line2, line3);
        Scene s1 = new Scene( haiku, 380, 160 );        // Set window size.
        st1.setTitle("April 3");
        st1.setScene(s1);
        st1.show();
    }

    Text makeText( int x, int y, String s, Font f, Color c ){
        Text t = new Text( x, y, s );
        t.setFont(f);
        t.setFill(c);
        return t;
    }
}
