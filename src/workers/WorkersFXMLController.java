package workers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Login.LoginController;
import dbUtil.dbConnection;

public class WorkersFXMLController extends LoginController implements Initializable{

    @FXML
    private Pane studentpane;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_starttime;

    @FXML
    private TextField txt_endtime;

    @FXML
    private TextField txt_project;

    @FXML
    private TextField txt_role;

    @FXML
    private TextArea txt_opinion;
    
    Alert alert=new Alert(AlertType.CONFIRMATION);
    
    String name,s_time,e_time,project,role;
    @FXML
    void addAction(ActionEvent event) {
      if(role.equals("Consultant")) {
        Consult consult=new Consult(name, role, project);
        consult.addOpinion(txt_opinion.getText());
      }
      if(role.equals("Developer")) {
        s_time=txt_starttime.getText();
        e_time=txt_endtime.getText();
        Developer developer=new Developer(name, role, project);
        if(CheckDeveloperTimeInput()==true) {
        developer.DoShift(s_time, e_time);
        }else {
          alert.setContentText("This Project has Limit \nStart Time : "+startTimeLimit+"\nEnd Time : "+endTimeLimit);
          alert.show();
        }
      }
    }
  
    @FXML
    void logoutAction(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/Login.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FoodieStore");
        primaryStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();

    } catch (Exception e) {
        alert.setContentText(e.toString());
        alert.show();
    }
    }
    
   
    public void initialize(URL url, ResourceBundle rb) {
      if(LoginAs.equals("Consultant")) {
        txt_starttime.setDisable(true);
        txt_endtime.setDisable(true);
      }
      else {
        txt_opinion.setDisable(true);
      }
      getInfo();
      setInfo();
  }

   void getInfo() {
     try {
       Connection conn=dbConnection.getConnection();
       String sql="Select NAME,ROLE,PROJECT from Workers where UserName='"+LoginUser+"' AND Password ='"+LoginPassword+"' AND ROLE='"+LoginAs+"'";
       PreparedStatement pst=conn.prepareStatement(sql);
       ResultSet rs=pst.executeQuery();
       name=rs.getString(1);
       role=rs.getString(2);
       project=rs.getString(3);
       rs.close();
       pst.close();
       conn.close();
     }catch(Exception e) {
       alert.setContentText(e.toString());
       alert.show();
     }
   }
   
   void setInfo() {
     txt_name.setText(name);
     txt_role.setText(role);
     txt_project.setText(project);
   }
   
   int startTimeLimit,endTimeLimit;
   
   void FindStartAndEndtimeLimit() {
     
     try {
       Connection conn=dbConnection.getConnection();
       String sql="Select START_TIME,END_TIME FROM PROJECT where NAME='"+project+"' ";
       PreparedStatement pst=conn.prepareStatement(sql);
       ResultSet rs=pst.executeQuery();
       startTimeLimit=Integer.valueOf(rs.getString(1));
       endTimeLimit=Integer.valueOf(rs.getString(2));
       rs.close();
       pst.close();
       conn.close();
     }catch(Exception e) {
       alert.setContentText(e.toString());
       alert.show();
     }
   }
   
   boolean CheckDeveloperTimeInput() {
     FindStartAndEndtimeLimit();
     try {
   int input_s_time=Integer.valueOf(txt_starttime.getText());
   int input_e_time=Integer.valueOf(txt_endtime.getText());
   if(input_s_time>=startTimeLimit&&input_e_time<=endTimeLimit) {
    return true; 
   }else {
     return false;
   }
     }catch(NumberFormatException e) {
       alert.setContentText("Enter time in Integer Only");
       alert.show();
     }
    return false;
   }
   
   
}
