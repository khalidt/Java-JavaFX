package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;


/** An introduction to JavaFX applications.
 */
public class Main extends Application {
    Text title = new Text(1, 40, "Khalid");
    Text line1 = new Text(50, 70, "Piercing cold, stiffness;");
    Text line2 = new Text(50, 95, "Warmth of spring still far away,");
    Text line3 = new Text(50, 120, "Many weeks to wait.");

    Font f1 = Font.font ("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24);
    Font f2 = Font.font ("Courier", FontWeight.NORMAL, FontPosture.ITALIC, 20);
    Color c1 = new Color( 0, 0, 0, 1 );
    Pane pan = new Pane();

    // @Override
    public void start( Stage st1 ){
        pan.getChildren().addAll(title, line1, line2, line3);
        setProps(title, f1, Color.BLUE);
        setProps(line1, f2, c1);
        setProps(line2, f2, c1);
        setProps(line3, f2, c1);

        Scene sn = new Scene( pan, 400, 400 );        // Set window size.
        st1.setTitle("January 31th");
        st1.setScene(sn);
        st1.show();
        
    }

    void setProps ( Text t, Font f, Color c ){
        t.setFont(f);
        t.setFill(c);
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}