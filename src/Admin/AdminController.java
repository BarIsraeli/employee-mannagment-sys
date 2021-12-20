package Admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AdminController implements Initializable {
    @FXML
    private TextField Name;
    @FXML
    private ComboBox<String> Role;
    @FXML
    private ComboBox<String> Project;
    @FXML
    private TextField Email;

    @FXML
    private TableView<WorkerData> workerstable;
    @FXML
    private TableColumn<WorkerData, String> Namecol;
    @FXML
    private TableColumn<WorkerData, String> Rolecol;
    @FXML
    private TableColumn<WorkerData, String> Projectcol;
    @FXML
    private TableColumn<WorkerData, String> Emailcol;
    @FXML
    private TableColumn<WorkerData, String> usercol;

    @FXML
    private TableColumn<WorkerData, String> passcol;
    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;
    @FXML
    private Button loadbutton;
    @FXML
    private TableView<ProjectTable> projectTable;

    @FXML
    private TableColumn<ProjectTable, String> projectNameColumn;

    @FXML
    private TableColumn<ProjectTable, String> projectStarttimeColumn;

    @FXML
    private TableColumn<ProjectTable, String> projectEndtimrColumn;
    @FXML
    private TextField txt_projectName;

    @FXML
    private TextField txt_starttime;

    @FXML
    private TextField txt_endtime;
    
    @FXML
    private TableView<ProgressTable> progressTable;

    @FXML
    private TableColumn<ProgressTable, String> name_column;

    @FXML
    private TableColumn<ProgressTable, String> role_column;

    @FXML
    private TableColumn<ProgressTable, String> project_column;

    @FXML
    private TableColumn<ProgressTable, String> starttime_column;

    @FXML
    private TableColumn<ProgressTable, String> endtime_column;

    @FXML
    private TableColumn<ProgressTable, String> opinion_column;
    
    private ObservableList<WorkerData> data;
    private ObservableList<ProjectTable> data1;
    private ObservableList<ProgressTable> dataProgress;
    
    private dbConnection dc;
    Alert alert=new Alert(AlertType.CONFIRMATION);
    
    
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
        populate();
        populateProductsTable();
        getAllProducts();
        Project.setItems(allProjects);
        AllRoles();
        Role.setItems(allRoles);
        populateProgressTable();
    }
    
    ObservableList<String> allProjects= FXCollections.observableArrayList();
    ObservableList<String> allRoles= FXCollections.observableArrayList();


    void AllRoles() {
      allRoles.add("Admin");
      allRoles.add("Developer");
      allRoles.add("Consultant");
      allRoles.add("Worker");
    }
    void getAllProducts() {
    try {
      allProjects.clear();
    Connection conn=dbConnection.getConnection();
    String sql="Select NAME FROM PROJECT";
    PreparedStatement pst=conn.prepareStatement(sql);
    ResultSet rs=pst.executeQuery();
    while(rs.next()) {
      allProjects.add(rs.getString(1));
    }
    rs.close();
    conn.close();
    } catch(Exception e) {
    alert.setContentText(e.toString()); 
    alert.show();
    } 
    
    }
    
    void addProduct() {
      
    try {
      Connection conn=dbConnection.getConnection();
      String sql="INSERT INTO PROJECT VALUES(?,?,?)";
      PreparedStatement pst=conn.prepareStatement(sql);
      pst.setString(1, txt_projectName.getText());
      pst.setString(2, txt_starttime.getText());
      pst.setString(3, txt_endtime.getText());
      pst.execute();
      pst.close();
      conn.close();
    } catch(Exception e) {
      alert.setContentText(e.toString());
      alert.show();
    } 
    }
    
    void populateProductsTable() {
      try {
        Connection conn = dbConnection.getConnection();
        this.data1 = FXCollections.observableArrayList();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM PROJECT");
        while (rs.next()) {
            this.data1.add(new ProjectTable(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        
        this.projectNameColumn.setCellValueFactory(new PropertyValueFactory("pro_name"));
        this.projectStarttimeColumn.setCellValueFactory(new PropertyValueFactory("pro_stime"));
        this.projectEndtimrColumn.setCellValueFactory(new PropertyValueFactory("pro_etime"));

        this.projectTable.setItems(null);
        this.projectTable.setItems(this.data1);
        
      }catch(Exception e) {
        alert.setContentText(e.toString());
        alert.show();
      } 
    }
    
    void populateProgressTable() {
      try {
        Connection conn = dbConnection.getConnection();
        this.dataProgress = FXCollections.observableArrayList();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM PROGRESS");
        while (rs.next()) {
            this.dataProgress.add(new ProgressTable(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        
        this.name_column.setCellValueFactory(new PropertyValueFactory("name"));
        this.role_column.setCellValueFactory(new PropertyValueFactory("role"));
        this.project_column.setCellValueFactory(new PropertyValueFactory("project"));
        this.starttime_column.setCellValueFactory(new PropertyValueFactory("stime"));
        this.endtime_column.setCellValueFactory(new PropertyValueFactory("etime"));
        this.opinion_column.setCellValueFactory(new PropertyValueFactory("opinion"));

        this.progressTable.setItems(null);
        this.progressTable.setItems(this.dataProgress);
        
      }catch(Exception e) {
        alert.setContentText(e.toString());
        alert.show();
      } 
    }
    
    
    void populate() {
      try {
        Connection conn = dbConnection.getConnection();
        this.data = FXCollections.observableArrayList();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Workers");
        while (rs.next()) {
            this.data.add(new WorkerData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
        }
    } catch (SQLException e) {
        System.err.println("Error " + e);
    }
    this.Namecol.setCellValueFactory(new PropertyValueFactory("Name"));
    this.Rolecol.setCellValueFactory(new PropertyValueFactory("Role"));
    this.Projectcol.setCellValueFactory(new PropertyValueFactory("Project"));
    this.Emailcol.setCellValueFactory(new PropertyValueFactory("Email"));
    this.usercol.setCellValueFactory(new PropertyValueFactory("UserName"));
    this.passcol.setCellValueFactory(new PropertyValueFactory("Password"));


    this.workerstable.setItems(null);
    this.workerstable.setItems(this.data);
    }

    @FXML
    private void loadWorkerData(ActionEvent event) {
      populate();
    }
    @FXML
    void deleteWorker(ActionEvent event) {
      deleteRecord();
      deleteLoginRecord();
      populate();
    }
    
    void addWorker() {
      String sql = "INSERT INTO `Workers`(`Name`, `Role`, `Project`,`Email`,'UserName','Password') VALUES (? , ?, ?, ?,?,?)";
      try {
          Connection conn = dbConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, this.Name.getText());
          stmt.setString(2, this.Role.getValue());
          stmt.setString(3, this.Project.getValue().toString());
          stmt.setString(4, this.Email.getText());
          stmt.setString(5, this.txt_username.getText());
          stmt.setString(6, this.txt_password.getText());
          

          stmt.execute();
          conn.close();
      } catch (SQLException e) {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
      }
    }
    
    void addLoginDetails() {
      try {
        Connection conn=dbConnection.getConnection();
        String sql="INSERT INTO LOGIN VALUES(?,?,?)";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.setString(1,txt_username.getText());
        pst.setString(2,txt_password.getText());
        pst.setString(3, Role.getValue());
        pst.execute();
        conn.close();
      }catch(Exception e) {
        alert.setContentText(e.toString());
        alert.show();
      }
    }
    @FXML
    private void addWorker(ActionEvent event) {
        addWorker();
        populate();
        addLoginDetails();
    }


    @FXML
    void addProductAction(ActionEvent event) {
      addProduct();
      populateProductsTable();
      getAllProducts();
      Project.setItems(allProjects);
    }
    
    String EmployeeName,EmployeeUserName,EmployeeDivision;
    @FXML
    void tableCickAction(MouseEvent event) {
      EmployeeName=workerstable.getSelectionModel().getSelectedItem().getName();
      EmployeeUserName=workerstable.getSelectionModel().getSelectedItem().getUserName();
      EmployeeDivision=workerstable.getSelectionModel().getSelectedItem().getRole();
    }
    
    void deleteRecord() {
      try {
        Connection conn=dbConnection.getConnection();
        String sql="DELETE FROM Workers WHERE NAME='"+EmployeeName+"' AND UserName='"+EmployeeUserName+"'";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.execute();
        pst.close();
        conn.close();
      }catch(Exception e) {
        alert.setContentText(e.toString());
        alert.show();
      }
    }
    
    
    void deleteLoginRecord() {
      try {
        Connection conn=dbConnection.getConnection();
        String sql="DELETE FROM login WHERE username='"+EmployeeUserName+"' and division='"+EmployeeDivision+"'";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.execute();
        pst.close();
        conn.close();
      }catch(Exception e) {
        alert.setContentText(e.toString());
        alert.show();
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
        primaryStage.setTitle("My Great Workplace!");
        primaryStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();

    } catch (Exception e) {
        alert.setContentText(e.toString());
        alert.show();
    }
    }
    
    @FXML
    private void clearFields(ActionEvent event) {
        this.Name.setText("");
        this.Role.setValue("");
        this.Project.setValue("");;
        this.Email.setText("");

    }
}
