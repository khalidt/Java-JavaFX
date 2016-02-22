package application;
/*
 * First FX
 * By Khalid
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class Main extends Application {
	// Text dec
	Text title = new Text(1,40,"Welcome to JavaFX");
	Text txt1 = new Text(50,100,"Khalid");
	Text txt2 = new Text(50,150,"FX is easy...");
	// Font pro
	Font f1 = Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,24);
	Font f2 = Font.font ("Courier", FontWeight.NORMAL, FontPosture.ITALIC, 20);
	Font f3 = Font.font("Atial",40);
	// Color pro
	Color c1= new Color (0,0,1,1);
	Color black = new Color (0,0,0,1);
	Color c2= new Color (.7,0,.7,1);
	// Circle dec & pro
	Circle cir= new Circle(200,100,30,Color.BLACK);
	//Line shape
	Line l1 = new Line(0,0, 400, 400);
	//Polugone
	Polygon pol = new Polygon(200,140,40,20,.10,.40);
	//Rectangle
	Rectangle r1= new Rectangle(80,200,50,80);
	//Pane
	Pane pan = new Pane();
	//@Override
	public void start(Stage primaryStage) {
		title.setFont(f1);// set font f1 to text title
		title.setFill(c1);// set color c1 to text title
		txt1.setFont(f2);
		txt1.setFill(c2);
		r1.setFill(Color.AQUAMARINE);
	
		// use fun setpro to set Font and Color to text txt2.
		setpro(txt2,f2,black);
		
		cir.setStroke(Color.RED);// Line around the Circle ÇØÇÑ
		
		pan.getChildren().addAll(title,txt1,txt2,cir,l1,pol,r1);// put the texts in Pane

		Scene sc = new Scene(pan,400,400);// put Pane in Scene
		primaryStage.setScene(sc);	 // put Scene in a Stage(primaryStage)
		primaryStage.setTitle("Java FX new Java ^_^");
		primaryStage.show();	// show the Stage

	}
	public static void main(String[] args) {
		launch(args);
	}
	// function to put Font and Color for a Text
	Text setpro(Text t, Font f, Color c){
		t.setFont(f);
		t.setFill(c);
		return  t ;
	}
}