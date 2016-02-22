package application;

import java.awt.Insets;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;        // Generated when you click a button.
import javafx.event.EventHandler; 
import javafx.geometry.Orientation;
import javafx.geometry.Pos;


public class Main extends Application {
	Button btn_reg = new Button("Register");
	Button btn_exit = new Button("Exit");
	TextField fname = new TextField("Enter First name:");
	TextField lname = new TextField("Enter Last name:");
	Label lf = new Label("first name ");
	Label ll = new Label("last name ");
	Pane pane = new Pane();
	BorderPane bp = new BorderPane();
	FlowPane fb = new FlowPane();
	HBox hb = new HBox(1);// 1 is spacing 
	VBox vb = new VBox(5);// 5 is spacing
	BorderPane mainpane = new BorderPane();
	
	Scene scene = new Scene(vb,400,300);
	Color c1 = new Color (.0,.0,.0,1);
	
	
	@Override
	public void start(Stage primaryStage) {
		
		// ******* FlowPane pro *******
//		FlowPane fb = new FlowPane();
//		fb.setOrientation(Orientation.HORIZONTAL);// or Vertical 
//		fb.setAlignment(Pos.CENTER);
//		fb.setHgap(30);// Distance between Horizontal node 
//		fb.setVgap(50);// Distance between V node
//		fb.getChildren().addAll(lf,fname,ll,lname,btn_reg);
//		fb.setMargin(gPane, new Insets(0,3,0,3));
		
		
		
		
		// ******* BorderPane pro *******
//		BorderPane bp = new BorderPane();
//		bp.setCenter(btn_reg); only one node pear area.
//		bp.setRight(btn_exit);
//		bp.setLeft(fname);
//		bp.setBottom(lname);
//		btn_exit.setOnAction(new EventHandler<ActionEvent>(){
//            public void handle(ActionEvent e) {
//                System.out.println("Time to quit.");
//                Platform.exit();
//            }
//		});
		
		
		// ******* HBox pro *******
		// HBox hb = new HBox(5) // this is spacing 
		//hb.setSpacing(2);
		//hb.getChildren().addAll(btn_reg,btn_exit);
		
		// ******* GridPane *******
		//GridPane gPane = new GridPane();
//		  gPane.setAlignment(Pos.CENTER);
//		  gPane.setHgap(5);
//        gPane.setVgap(2);
//        gPane.setPadding( new Insets( 3, 3, 3, 3));
//        gPane.add( buttonPane, 1,1 );
//        gPane.add( stopButton, 3,1 );
		
		
		// ******* checkBox pro *******
//		CheckBox blueBox = new CheckBox("Blue");
//		 blueBox.setOnAction( new BlueLight() );
//		if (blueBox.isSelected()) {.....};
//		blueBox.setSelected( false );
		
		// *******TextField pro *******
//		TextField fname = new TextField("Enter First name:");
//		fname.setAlignment(Pos.BOTTOM_RIGHT);
//		double rd;
//		rd = Double.parseDouble(red.getText());
		

		// ********* Button **********/
		//Button btn_exit = new Button("Register");
//		btn_exit.setOnAction(new EventHandler<ActionEvent>(){
//            public void handle(ActionEvent e) {
//                System.out.println("Time to quit.");
//                Platform.exit();
//            }
//        });
		
		
		/* ********Handler ******** */
//		 class SubmitAction implements EventHandler<ActionEvent> {
//		        @Override
//		        public void handle( ActionEvent e ){
//		            rd = Double.parseDouble(red.getText());
//		            if (bl<0. || bl>1. || rd<0. || rd>1. || gr<0. || gr>1.)
//		                System.out.println("....");
//		            else {
//		                Color c = Color.color(rd, gr, bl, 1);
//		                face.setFill( c ); }  }  }
//		btn_exit.setOnAction(new EventHandler<ActionEvent>(){
//      public void handle(ActionEvent e) {
//      System.out.println("Time to quit.");
//      Platform.exit();
//  }
//});
		
		// ********* RadioButton ************
//		RadioButton dayButton = new RadioButton("Day    ");
//		dayButton.setOnAction( Handler);
//		dayButton.setSelected(true);
//		ToggleGroup timeGroup = new ToggleGroup();
//		dayButton.setToggleGroup( timeGroup );
		
		primaryStage.setTitle("FX Pane");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}
