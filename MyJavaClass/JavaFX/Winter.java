import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import javafx.stage.Stage;

/** An introduction to JavaFX applications.
 */
public class Winter extends Application {
    Text title = new Text(40, 40, "Winter");
    Text line1 = new Text(50, 70, "Piercing cold, stiffness;");
    Text line2 = new Text(50, 95, "Warmth of spring still far away,");
    Text line3 = new Text(50, 120, "Many weeks to wait.");

    Font f1 = Font.font ("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24);
    Font f2 = Font.font ("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 20);
    Color c1 = new Color( .3, .3, .8, 1 );
    Pane haiku = new Pane();

    // @Override
    public void start( Stage st1 ){
        haiku.getChildren().addAll(title, line1, line2, line3);
        setProps(title, f1, Color.BLUE);
        setProps(line1, f2, c1);
        setProps(line2, f2, c1);
        setProps(line3, f2, c1);

        Scene sn = new Scene( haiku, 380, 160 );        // Set window size.
        st1.setTitle("January 31th");
        st1.setScene(sn);
        st1.show();
    }

    void setProps ( Text t, Font f, Color c ){
        t.setFont(f);
        t.setFill(c);
    }
}
