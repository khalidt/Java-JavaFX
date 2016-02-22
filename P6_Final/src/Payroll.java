/*
 * @author Khalid
 */


import java.io.*;
import java.util.*;
import java.security.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.geometry.*; 
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import java.net.URL;


public class Payroll extends Application {
	private Stage st; 
	private Scene loginScene; // login form
	private Scene bossScene; // Boss form
	private Scene createEmpScene; // createEmp form
	private Scene changEmpeScene; // change Emp data form
	private Scene payrollScene; // payroll form
	private Scene employeeScene; // Employee form
	private Scene terminateScene; // Terminate form
	private Scene createBossScene; // this form shows to create Boss for first running of the program 
	
	// the components of create Boss form
	private Label note_fcBoss;
	private Label lab_loginName_fcBoss;
	private TextField tf_loginName_fcBoss;
	private Label lab_Psw_fcBoss;
	private TextField tf_Psw_fcBoss;
	private Label lab_RePsw_fcBoss;
	private TextField tf_RePsw_fcBoss;
	private Label lab_EmpName_fcBoss;
	private TextField tf_EmpName_fcboss;
	private Label lab_Salary_fcBoss;
	private TextField tf_Salary_fcBoss;
	private Button btn_Create_fcBoss;
	
	// the components of login form
	private Label lab_Username_loginf;
	private TextField tf_Username_loginf;
	private Label lab_Psw_loginf;
	private TextField tf_Psw_loginf;
	private Button btn_Submit_loginf;
	private Button btn_Exit_loginf;
	
	// the components of Boss form
	private TextArea ta_ListEmp_Bossf; 
	private Button btn_createEmp_Bossf; 
	private Button btn_terminateEmp_Bossf;
	private Button btn_UpdatEmp_Bossf; 
	private Button btn_Payroll_Bossf; 
	private Button btn_exit_Bossf; 
	
	// the components of create Emp form
	private Label lb_note_CrEmpf; 
	private Label lab_Loginname_CrEmpf;
	private TextField tf_Login_CrEmpf; 
	private Label lab_Psw_CrEmpf; 
	private TextField tf_Psw_CrEmpf; 
	private Label lab_EmpName_CrEmpf;
	private TextField tf_EmpName_CrEmpf;
	private Label lab_Salary_CrEmpf; 
	private TextField tf_Salary_CrEmpf; 
	private Button btn_Create_CrEmpf; 
	private RadioButton rb_Salary_CrEmpf;
	private RadioButton rb_Hourly_CrEmpf;
	private ToggleGroup group;
	
	// the components of change Emp form
	private Label lab_EmpName_chEmpf; 
	private TextField tf_EmpName_chEmpf; 
	private Label lab_Salary_chEmpf;
	private TextField tf_Salary_chEmpf; 
	private Button btn_update_chEmpf; 
	private Label lab_ID_chEmpf;
	private TextField tf_ID_chEmpf;
	
	// the components of payroll form
	private TextArea ta_listEmp_payrollf; 
	private Button btn_Submit_payrollf;
	private Text txt_EmpID_payrollf; 
	private TextField tf_EmpID_payrollf; 
	private Label lab_Hourly_payrollf;
	private TextField tf_Hourly_payrollf;
	//private Button btPayHourly;
	
	// the components of terminate Emp form
	private Label lab_EmpID_termif;
	private TextField tf_EmpID_termif;  
	private Button btn_delet_termif;
	
	// the components of Emp form
	private Label note_empf;
	private Image image; 
	private ImageView img_view;
	private AudioClip audio1; // for welcome sound
	private AudioClip audio2; // for piano sound
	private TextArea taDataEmpf; // output data of employee who is logged in
	private Button btn_LogoutEmpf; 
	
	private Scanner sc = new Scanner(System.in);
	private PrintWriter pwToTxt;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private ArrayList<Employee> arr = new ArrayList<Employee>();
	private ArrayList<Employee> arrFired = new ArrayList<Employee>();// store the fired employees
	private String tempPass; 
	private Employee currentUser; // store current user 

	
	public static void main(String[] args) throws IOException {
		System.out.println("**************\n* Khalid P6\n* \n**************");
		launch(args);
	}
	@SuppressWarnings("unchecked")
	public void start(Stage st) throws IOException, NoSuchAlgorithmException, FileNotFoundException {
		this.st = st;
			try{
				pwToTxt = new PrintWriter( new File( "payroll.txt") ); 
				// output to an object file
				fis = new FileInputStream("employee.txt");
				ois = new ObjectInputStream( fis );
				//read from obj file and set NextId
				arr = (ArrayList<Employee>) ois.readObject(); 
				Employee.setNextId( arr.get( arr.size() - 1 ).getEmployeeId() );  
				
				buildGUI();
				listEmployeesBoss();
				st.setTitle( "Employee Program [Khalid]" );
				st.setScene( loginScene );
				st.show();
			}catch(FileNotFoundException ex) {
				// create boss for first time running if employee file doesnt exist.
				GridPane gp_fcBoss = new GridPane();
				gp_fcBoss.setHgap( 10 );
				gp_fcBoss.setVgap( 10 );
				gp_fcBoss.setAlignment( Pos.CENTER );
				// components of create a boss 
				note_fcBoss = new Label( "First you should create a boss !" );
				lab_loginName_fcBoss = new Label( "Login name:" );
				tf_loginName_fcBoss = new TextField();
				lab_Psw_fcBoss = new Label( "Password:" );
				tf_Psw_fcBoss = new TextField();
				lab_RePsw_fcBoss = new Label( "Re-Password:" );
				tf_RePsw_fcBoss = new TextField();
				lab_EmpName_fcBoss = new Label( "Employee full name:" );
				tf_EmpName_fcboss = new TextField();
				lab_Salary_fcBoss = new Label( "Salary | Pay Rate:" );
				tf_Salary_fcBoss = new TextField();
				btn_Create_fcBoss = new Button( "Create" );
				btn_Create_fcBoss.setOnAction( new createBoss() );
				// add components to gride pane 
				gp_fcBoss.add( note_fcBoss, 0, 0 );
				gp_fcBoss.add( lab_loginName_fcBoss, 0, 1 );
				gp_fcBoss.add( tf_loginName_fcBoss, 1, 1 );
				gp_fcBoss.add( lab_Psw_fcBoss, 0, 2 );
				gp_fcBoss.add( tf_Psw_fcBoss, 1, 2 );
				gp_fcBoss.add( lab_RePsw_fcBoss, 0, 3 );
				gp_fcBoss.add( tf_RePsw_fcBoss, 1, 3 );
				gp_fcBoss.add( lab_EmpName_fcBoss, 0, 4 );
				gp_fcBoss.add( tf_EmpName_fcboss, 1, 4 );
				gp_fcBoss.add( lab_Salary_fcBoss, 0, 5 );
				gp_fcBoss.add( tf_Salary_fcBoss, 1, 5 );
				gp_fcBoss.add( btn_Create_fcBoss, 1, 6 );
				gp_fcBoss.setHalignment( btn_Create_fcBoss, HPos.CENTER );

				createBossScene = new Scene( gp_fcBoss , 360, 280 );
				
				buildGUI();
				listEmployeesBoss();
				st.setTitle( "Khalid [P6]" );
				st.setScene( createBossScene );
				st.show();
				
			}catch(Exception e) {
				System.err.println(e);
				System.exit(0);
			}
	}
	
	//build GUI	
	public void buildGUI() {
		//********************Login Scene********************************* 
		GridPane p_loginf = new GridPane(); 
		// set hgap and vgap and the position 
		p_loginf.setHgap( 5 );
		p_loginf.setVgap( 5 );
		p_loginf.setAlignment( Pos.CENTER ); 
		
		lab_Username_loginf = new Label("Username:");
		tf_Username_loginf = new TextField();
		lab_Psw_loginf = new Label("Password:");
		tf_Psw_loginf = new TextField();
		btn_Submit_loginf = new Button("Submit");
		btn_Exit_loginf= new Button("Exit");
		btn_Submit_loginf.setOnAction( new SubmitLogin() );
		btn_Exit_loginf.setOnAction( e->{ 
			try{
				doSave(); 
				clearLoginTF();
				Platform.exit();
				}catch(IOException ex) {ex.printStackTrace(); System.exit(0);}});
		
		p_loginf.add( lab_Username_loginf, 0, 0 );
		p_loginf.add( tf_Username_loginf, 1, 0 );
		p_loginf.add( lab_Psw_loginf, 0, 1 );
		p_loginf.add( tf_Psw_loginf, 1, 1 );
		p_loginf.add( btn_Submit_loginf, 1, 2);
		p_loginf.add( btn_Exit_loginf, 2, 2);
		p_loginf.setHalignment( btn_Submit_loginf, HPos.CENTER );
		loginScene = new Scene( p_loginf , 400, 200 );
		
		//********************Employee Scene*********************************
				VBox p_empf = new VBox( 10 );
				p_empf.setPadding( new Insets( 5 ));
				note_empf = new Label ("Your Information:");
				btn_LogoutEmpf = new Button("Exit");
				btn_LogoutEmpf.setOnAction(e->{ 
											try{
												doSave(); 
												clearLoginTF();
												st.setScene( loginScene );
												}catch(IOException ex) {ex.printStackTrace(); System.exit(0);}
				});
				image = new Image("empimg.jpg");
				img_view = new ImageView( image );
				img_view.setFitHeight( 400 );//544
				img_view.setFitWidth( 500 );//800
				taDataEmpf = new TextArea();
				taDataEmpf.setMaxHeight(20);
				URL url_audio1 = getClass().getResource("welcome.mp3");
				audio2 = new AudioClip( url_audio1.toString() );
				URL url_audio2 = getClass().getResource("piano.mp3");
				audio1 = new AudioClip( url_audio2.toString() );
			
				p_empf.getChildren().addAll(note_empf,taDataEmpf, btn_LogoutEmpf,img_view);
				p_empf.setAlignment(Pos.CENTER);
				employeeScene = new Scene(p_empf);
		
		// ********************Boss Scene*********************************
		HBox hb_mainpane_Bossf = new HBox( 30 );
		hb_mainpane_Bossf.setPadding( new Insets(10));
		VBox vb_btns_Bossf = new VBox( 15 );
		vb_btns_Bossf.setAlignment( Pos.TOP_CENTER );
		
		ta_ListEmp_Bossf = new TextArea();
		ta_ListEmp_Bossf.setEditable( false );
		btn_createEmp_Bossf = new Button( "Create a new employee" );
		btn_createEmp_Bossf.setOnAction( new Show_CreateEmpScene() );
		//
		btn_terminateEmp_Bossf = new Button( "Delete the employee" );
		btn_terminateEmp_Bossf.setOnAction( new Show_TerminateEmpf() );
		
		btn_UpdatEmp_Bossf = new Button( "Update Employee Data" );
		btn_UpdatEmp_Bossf.setOnAction( new Show_ChangeEmpScene() );
		
		btn_Payroll_Bossf = new Button( "Payroll" );
		btn_Payroll_Bossf.setOnAction( new Show_PayrollScene() );
		
		btn_exit_Bossf = new Button( "Exit" );
		btn_exit_Bossf.setOnAction( e->{ 
									try{
										doSave(); 
										clearLoginTF();
										st.setScene( loginScene );
										}catch(IOException ex) {ex.printStackTrace(); System.exit(0);}
		});
		
		vb_btns_Bossf.getChildren().addAll( btn_createEmp_Bossf, btn_UpdatEmp_Bossf,btn_terminateEmp_Bossf , btn_Payroll_Bossf, btn_exit_Bossf );
		hb_mainpane_Bossf.getChildren().addAll( ta_ListEmp_Bossf,vb_btns_Bossf );
		bossScene = new Scene( hb_mainpane_Bossf , 700, 500 );
	
		
		// ********************Create new Emp Scene*********************************
		GridPane gp_mainf = new GridPane();
		gp_mainf.setHgap( 10 );
		gp_mainf.setVgap( 10 );
		gp_mainf.setAlignment( Pos.CENTER );
		
		lb_note_CrEmpf = new Label( "Please type the data of new Employee:" );
		lab_Loginname_CrEmpf = new Label( "Login name:" );
		tf_Login_CrEmpf = new TextField();
		lab_Psw_CrEmpf = new Label( "Password:" );
		tf_Psw_CrEmpf = new TextField();
		lab_EmpName_CrEmpf = new Label( "Employee full name:" );
		tf_EmpName_CrEmpf = new TextField();
		lab_Salary_CrEmpf = new Label( "Salary | Pay Rate:" );
		tf_Salary_CrEmpf = new TextField();
		btn_Create_CrEmpf = new Button( "Create" );
		btn_Create_CrEmpf.setOnAction( new Create() );
		rb_Salary_CrEmpf = new RadioButton( "Salary " );
		rb_Hourly_CrEmpf = new RadioButton( "Hourly" );
		group = new ToggleGroup();
		rb_Salary_CrEmpf.setToggleGroup( group );
		rb_Hourly_CrEmpf.setToggleGroup( group );
		
		gp_mainf.add( lb_note_CrEmpf, 0, 0 );
		gp_mainf.add( lab_Loginname_CrEmpf, 0, 1 );
		gp_mainf.add( tf_Login_CrEmpf, 1, 1 );
		gp_mainf.add( lab_Psw_CrEmpf, 0, 2 );
		gp_mainf.add( tf_Psw_CrEmpf, 1, 2 );
		gp_mainf.add( lab_EmpName_CrEmpf, 0, 3 );
		gp_mainf.add( tf_EmpName_CrEmpf, 1, 3 );
		gp_mainf.add( lab_Salary_CrEmpf, 0, 4 );
		gp_mainf.add( tf_Salary_CrEmpf, 1, 4 );
		gp_mainf.add( rb_Salary_CrEmpf, 1, 5 );
		gp_mainf.setHalignment( rb_Salary_CrEmpf, HPos.LEFT );
		gp_mainf.add( rb_Hourly_CrEmpf, 1, 6 );
		gp_mainf.setHalignment( rb_Hourly_CrEmpf, HPos.LEFT );
		gp_mainf.add( btn_Create_CrEmpf, 1, 7);
		gp_mainf.setHalignment( btn_Create_CrEmpf, HPos.RIGHT);
		createEmpScene = new Scene( gp_mainf , 400, 300 );
		
		// ********************Change Emp Scene*********************************
		GridPane gp_main_chEmpf = new GridPane();
		gp_main_chEmpf.setHgap( 10 );
		gp_main_chEmpf.setVgap( 10 );
		gp_main_chEmpf.setAlignment( Pos.CENTER );
		
		lab_ID_chEmpf = new Label( "Enter an employee ID:" );
		tf_ID_chEmpf = new TextField();
		lab_EmpName_chEmpf = new Label( "Enter a new name: " );
		tf_EmpName_chEmpf = new TextField();
		lab_Salary_chEmpf = new Label( "Enter a new salary:" );
		tf_Salary_chEmpf = new TextField();
		btn_update_chEmpf = new Button( "Update" );
		btn_update_chEmpf.setOnAction( new Update_chEmp() );
		
		gp_main_chEmpf.add( lab_ID_chEmpf, 0, 0 );
		gp_main_chEmpf.add( tf_ID_chEmpf, 1, 0 );
		gp_main_chEmpf.add( lab_EmpName_chEmpf, 0, 1 );
		gp_main_chEmpf.add( tf_EmpName_chEmpf, 1, 1 );
		gp_main_chEmpf.add( lab_Salary_chEmpf, 0, 2 );
		gp_main_chEmpf.add( tf_Salary_chEmpf, 1, 2 );
		gp_main_chEmpf.add( btn_update_chEmpf, 1, 3 );
		gp_main_chEmpf.setHalignment( btn_update_chEmpf, HPos.RIGHT );
		changEmpeScene = new Scene( gp_main_chEmpf, 400, 300 );
		
		// ********************Terminate Scene*********************************
		GridPane gp_main_Termif = new GridPane();
		gp_main_Termif.setHgap( 10 );
		gp_main_Termif.setVgap( 15 );
		gp_main_Termif.setAlignment( Pos.CENTER );
		
		lab_EmpID_termif = new Label( "Enter the Employ ID:" );
		tf_EmpID_termif = new TextField();
		btn_delet_termif = new Button( "Delete" );
		btn_delet_termif.setOnAction( new Delet_emp() );
		
		gp_main_Termif.add( lab_EmpID_termif, 0, 0 );
		gp_main_Termif.add( tf_EmpID_termif, 0, 1 );
		gp_main_Termif.setHalignment( tf_EmpID_termif, HPos.LEFT );
		gp_main_Termif.add( btn_delet_termif, 0, 2 );
		gp_main_Termif.setHalignment( btn_delet_termif, HPos.RIGHT );
		terminateScene = new Scene( gp_main_Termif, 300, 200 );
		
		// ********************Payroll Scene*********************************
		GridPane gp_main_payrollf = new GridPane();
		gp_main_payrollf.setHgap( 10 );
		gp_main_payrollf.setVgap( 15 );
		gp_main_payrollf.setAlignment( Pos.CENTER );
		HBox hb_EmpId = new HBox(5);// to put labl and txtfield for Emp ID
		HBox hb_hourly = new HBox(5);// to put label and txtfield for Hourly
		
		txt_EmpID_payrollf = new Text(" Choose Employee ID:");
		tf_EmpID_payrollf = new TextField();
		tf_EmpID_payrollf.setPrefColumnCount(3);
		hb_EmpId.getChildren().addAll( txt_EmpID_payrollf, tf_EmpID_payrollf );

		lab_Hourly_payrollf = new Label("Enter hours work:");
		tf_Hourly_payrollf = new TextField();
		tf_Hourly_payrollf.setPrefColumnCount(5);
		hb_hourly.getChildren().addAll( lab_Hourly_payrollf, tf_Hourly_payrollf );
		
		ta_listEmp_payrollf = new TextArea();
		ta_listEmp_payrollf.setEditable( false );
		btn_Submit_payrollf = new Button( "Submit" );
		btn_Submit_payrollf.setOnAction( new Submit_payroll() );
		
		gp_main_payrollf.add( ta_listEmp_payrollf, 0, 0 );
		gp_main_payrollf.add( hb_EmpId, 0, 1 );
		gp_main_payrollf.add( hb_hourly, 0, 2 );
		gp_main_payrollf.add( btn_Submit_payrollf, 0, 3 );
		gp_main_payrollf.setHalignment( btn_Submit_payrollf, HPos.CENTER );
		payrollScene = new Scene( gp_main_payrollf, 500, 300 );
	}

	/**
	 * Events Handler
	 */
	//create Boss for first running 
	class createBoss implements EventHandler<ActionEvent>  {
			@Override
			public void handle( ActionEvent t ) {
				try {
					if( tf_loginName_fcBoss.getText().isEmpty() || tf_Salary_fcBoss.getText().isEmpty() ||
						tf_EmpName_fcboss.getText().isEmpty() || tf_Psw_fcBoss.getText().isEmpty() ||
						tf_RePsw_fcBoss.getText().isEmpty() ) {
						st.setScene( createBossScene );
					}else {
						byte[] tempPass = getNewPassword();
						while( tempPass.length == 0) {
							tempPass = getNewPassword();
						}
						double salary = Double.parseDouble( tf_Salary_fcBoss.getText() );				
						arr.add( new Salaried( tf_loginName_fcBoss.getText(), salary, tf_EmpName_fcboss.getText(), tf_Psw_fcBoss.getText() ) );
						buildGUI();
						listEmployeesBoss();
						st.setScene( loginScene );
					}
				}catch(NoSuchAlgorithmException e) {
					System.err.println(e);
					System.exit(0);
				}
			}
		}
	// Submit in login Scene if emp is boss show boss form or show emp form. 
	class SubmitLogin implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t) {
			//  if textFileds are empty show loginScene
			if( tf_Username_loginf.getText().isEmpty() || tf_Psw_loginf.getText().isEmpty() ) {
				clearLoginTF();
				st.setScene( loginScene );
			}else {//if its not empty find the emp data if emp is boss show bossScene if not show empScene
				boolean exist = false; //  store emp state if emp is exist 
				for(Employee e: arr) {
					// compare the data with each elements of arr
					if( e.getLoginName().equals( tf_Username_loginf.getText() ) && 
						e.getPassword().equals( tf_Psw_loginf.getText() ) ) {
						clearLoginTF();
						currentUser = e; // store user data
						// display Boss form if employID = 0
						if( e.getEmployeeId() == 0) { 
							st.setScene( bossScene ); 
							exist = true;
							break; 
						}else { // else display emp form 
							exist = true;
							st.setScene( employeeScene ); 
							taDataEmpf.setText( e.toString() );
							audio1.play();
							audio2.play();
							break; 
						}
						
					}
				}// if emp dosent exist printout msg
				if(!exist) System.out.println("This Employee is not existed."); 

			}
		}
	}
	
	// to create emp show new empScene
	class Show_CreateEmpScene implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t ) {
			st.setScene( createEmpScene );
		}
	}
	//ceate new Emp in createEmpScene
	class Create implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t ) { // if txtfields are empty show createScene to  fill all txtfields
			if( tf_Login_CrEmpf.getText().isEmpty() || tf_Psw_CrEmpf.getText().isEmpty() ||
				tf_EmpName_CrEmpf.getText().isEmpty() || tf_Salary_CrEmpf.getText().isEmpty() ) {
				st.setScene( createEmpScene );
				// clear txtfield 
				tf_Login_CrEmpf.clear();
				tf_Psw_CrEmpf.clear();
				tf_EmpName_CrEmpf.clear();
				tf_Salary_CrEmpf.clear();
			}
			else {// else add new Emp, go to bossScene
				newEmployee();
				listEmployeesBoss();
				st.setScene( bossScene );
				tf_Login_CrEmpf.clear();
				tf_Psw_CrEmpf.clear();
				tf_EmpName_CrEmpf.clear();
				tf_Salary_CrEmpf.clear();
			}
		}
	}
	
	class Show_ChangeEmpScene implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t ){
			st.setScene( changEmpeScene );
		}
	}
	//btn update Emp data
	class Update_chEmp implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t ) {
			// if txtfield is not empty change Emp data and go to bossScene
			if( !tf_ID_chEmpf.getText().isEmpty() || !tf_EmpName_chEmpf.getText().isEmpty() || 
				!tf_Salary_chEmpf.getText().isEmpty()  ) {
				
					changeEmployeeData();
					listEmployeesBoss();
					st.setScene(bossScene);
					// clear the textfield
					tf_ID_chEmpf.clear();
					tf_EmpName_chEmpf.clear();
					tf_Salary_chEmpf.clear();
			}else {
				// clear the textfields and go to changeEmpScene
				tf_ID_chEmpf.clear();
				tf_EmpName_chEmpf.clear();
				tf_Salary_chEmpf.clear();
				st.setScene( changEmpeScene );
			}
		}
	}
	
    // show Terminate form
	class Show_TerminateEmpf implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t ) {
			st.setScene( terminateScene );
		}
	}
	//delete emp 
	class Delet_emp implements EventHandler<ActionEvent> {
			public void handle( ActionEvent t ) {
				if( !tf_EmpID_termif.getText().isEmpty() ) {
					terminateEmployee();
					listEmployeesBoss();
					st.setScene( bossScene );
					tf_EmpID_termif.clear();
				}
			}
		}

	// show Payroll Scene
	class Show_PayrollScene implements EventHandler<ActionEvent> {
		@Override
		public void handle( ActionEvent t ) {
			tf_EmpID_payrollf.clear();
			tf_Hourly_payrollf.clear();
			st.setScene( payrollScene );
			listEmpPayroll();
		}
	}
	//submit Payroll then fill txtArean for Boss Scene then show it	
	class Submit_payroll implements EventHandler<ActionEvent> {
		@Override// pay for Emp and return to BossScene
		public void handle( ActionEvent t ) {
				payEmployees();
				listEmployeesBoss();// to fill TxtArea and print list of Emp in Boss form
				st.setScene( bossScene );
		}
	}


/**functions that I need to get,update, and save Data from and to the file
 * @NewEmployee: to add new on
 * @ChangeEmployeeData: to update the exist data for any emp
 * @PayEmployee: to pay for emp who has hours work
 * @doSave: to save any changes or updated to the file
 * @clearloginTF: clear all txtfield in loginScene
 * @listEmployee: to print in console and in TextArea in bossScene
 * @listEmpPayroll: to print in console and in TextArea in PayrollScene
 */
	
	public byte[] getNewPassword() throws NoSuchAlgorithmException {
		// Hash a string using SHA-256
		MessageDigest msg_digest = MessageDigest.getInstance("SHA-256"); 
		// get password from txtfield and hash it
		msg_digest.update( tf_Psw_fcBoss.getText().getBytes() );
		byte[] psw = msg_digest.digest();
		// get re-password from txtfield and hash it
		msg_digest.update( tf_RePsw_fcBoss.getText().getBytes() );
		byte[] rePsw = msg_digest.digest();
		// check if the password and re-password are the same
		if( Arrays.equals( psw, rePsw ) ) return psw;
		else {
				System.out.println("The Passwords dont match !");
				return null;
		}	
	}
	private void newEmployee(){
		boolean loginValid = true;
			while( true ) 
			{// check if the login name is existed return to CreateScene otherwise create one
				for( Employee e: arr ) 
				{
					if( tf_Login_CrEmpf.getText().equals( e.getLoginName() ) ) loginValid = false;
				}
				
				if( loginValid ) break;
				else {
					System.out.println( "login name is used by another user.\n" +"Please try new one" );
					st.setScene(createEmpScene);
				}
			}
			
			// create employee depend on Salary or Hourly options
			if( rb_Salary_CrEmpf.isSelected() ) 
			{ 
				double salary = Double.parseDouble( tf_Salary_CrEmpf.getText() ); 
				arr.add( new Salaried( tf_Login_CrEmpf.getText(), salary, tf_EmpName_CrEmpf.getText(), tf_Psw_CrEmpf.getText() ) );
			}
			else if( rb_Hourly_CrEmpf.isSelected() )
			{ 
				double payRate = Double.parseDouble( tf_Salary_CrEmpf.getText() ); // get pay rate
				arr.add ( new Hourly(tf_Login_CrEmpf.getText(), payRate, tf_EmpName_CrEmpf.getText(), tf_Psw_CrEmpf.getText() ) );
			}
	}
	public void changeEmployeeData() {

		int indexOfEmployee = 0;
		boolean status = false;
		// check whether ID is valid 
		for( Employee e: arr ) {
			if( Integer.parseInt( tf_ID_chEmpf.getText() ) == e.getEmployeeId() ) {
				indexOfEmployee = arr.indexOf( e );
				if( !tf_EmpName_chEmpf.getText().isEmpty() )  arr.get( indexOfEmployee ).setEmployeeName( tf_EmpName_chEmpf.getText() );
				if( !tf_Salary_chEmpf.getText().isEmpty() )  arr.get( indexOfEmployee ).setSalary( Double.parseDouble( tf_Salary_chEmpf.getText() ) );
				status = true;
				break;
			}
		}
		if( !status ) {
			System.out.println( "This ID is invalid. Please try again." );
			System.out.println("Enter an ID of employee whose information will be changed");
			st.setScene( changEmpeScene );
		}
}
	public void terminateEmployee() {
		boolean status = true;
		int number = Integer.parseInt( tf_EmpID_termif.getText() );
		// find emp data then delet it and add it to arrFired
		for( Employee e: arr ) {
			if( e.getEmployeeId() == number ) {
				System.out.println("The employee is Deleted!");
				arrFired.add( e );
				arr.remove( e );
				status = false;
				break;
			}
		}
		// if ID doesn't exist 
		if( status ) {
			System.out.print( "ID number Doesn't  existed! Please try again." );
			st.setScene( terminateScene );
		}
}
	public Payroll() {	}
	public void payEmployees() {
		int i = Integer.parseInt( tf_EmpID_payrollf.getText() );
		for( Employee e: arr )
		{
			if( i == e.getEmployeeId() )
			{//  i used "instanceof" keyword to know if an object is of a Hourly type
				if( e instanceof Hourly) 
				{
					if( !tf_Hourly_payrollf.getText().isEmpty() ) // if txtfield not empty do payment
					{// pay for emp
					 ( (Hourly) e ).setWorkHours( Double.parseDouble(tf_Hourly_payrollf.getText()) );
					}else// msg to fill txtfield 
					{
						System.out.println("Please type work hours!");
					}
				}// print out and writ to file
				System.out.printf( "%13.2f\t%05d\t%s\n", e.getPay(), e.getEmployeeId(), e.getEmployeeName() ); 
				pwToTxt.printf( "%05d\t%s%13.2f\n", e.getEmployeeId(), e.getEmployeeName(), e.getPay() ); 
			}
		}
	
	
}
	public void doSave() throws IOException {
		try {
			fos = new FileOutputStream( "employee.txt" ); 
			oos = new ObjectOutputStream( fos );
			oos.writeObject( arr );
			
		}catch(FileNotFoundException e) {
			System.err.println(e);
			System.exit( 0 );
		}catch(IOException e) {
			System.err.println(e);
			System.exit( 0 );
		}catch (Exception e) {
			System.err.println(e);
			System.exit(0);
		}finally {

			System.out.println( "The list of employees who are fired in this Transaction:" );
			for( Employee emp: arrFired )
				System.out.println( emp.toString() );
			
			sc.close();
			pwToTxt.close();
			fos.close(); 
		}	
		System.out.println("Save|Update all changes to the file.");
	}
	private void clearLoginTF() {
		tf_Username_loginf.clear();
		tf_Psw_loginf.clear();
	}

	// output the list of employees that we have in BossScene
	public void listEmployeesBoss() {
		// print listof emp and fill sb to show it in TextArea in BossScene
		StringBuilder sb_EmpList = new StringBuilder(); 
		ta_ListEmp_Bossf.clear();
		for(Employee emp: arr) {
			System.out.println( emp.toString() );
			sb_EmpList.append( emp.toString() + '\n' );
		}
		ta_ListEmp_Bossf.setText( sb_EmpList.toString() );
	}
	//output the list of employees that we have in payrollScene
	public void listEmpPayroll() {
		//print listof emp and fill sb to show it in TextArea in payrollScene
		StringBuilder sbList = new StringBuilder(); 
		ta_listEmp_payrollf.clear();
		for(Employee emp: arr) {
			sbList.append( emp.toString() + '\n' );
		}
		ta_listEmp_payrollf.setText( sbList.toString() );
	}

	//Crrate password for boss and hash it
	
	
	
}