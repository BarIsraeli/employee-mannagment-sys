package Login;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import workers.WorkersFXMLController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML
    private Label db;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label dateTime;

protected static String LoginAs="";
protected static String LoginUser="";
protected static String LoginPassword="";


    public void initialize(URL url, ResourceBundle rb) {
        initClock();
        if (this.loginModel.isDatabaseConnected()) {
            this.db.setText("Connected");
        } else {
            this.db.setText("not connected");
        }
        this.comboBox.setItems(FXCollections.observableArrayList("Admin","Developer","Consultant"));
    }

    public void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm:ss  dd-MM-yyyy");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void Login() {

      LoginAs=this.comboBox.getValue();
      LoginUser=this.user.getText();
      LoginPassword=this.password.getText();
      
        try {
            if (this.loginModel.isLogin(this.user.getText(), this.password.getText(), this.comboBox.getValue().toString())) {
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (this.comboBox.getValue().toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Developer":
                        studentLogin();
                        break;
                    case "Consultant":
                      studentLogin();    
                      break;
                }
            } else {
                this.db.setText("Wrong Credentials");
            }
        } catch (Exception localException) {
        }
    }

    public void studentLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/workers/WorkersFXML.fxml").openStream());
            WorkersFXMLController studentController = loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Worker Dashboard");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            adminLoader.getController();

            Scene adminscene = new Scene(adminroot);

            adminStage.setScene(adminscene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(true);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
