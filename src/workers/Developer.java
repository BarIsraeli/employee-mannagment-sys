package workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import dbUtil.dbConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Developer extends Worker{

  public Developer(String name, String role, String project) {
    super(name, role, project);

  }
Alert alert=new Alert(AlertType.CONFIRMATION);

  void DoShift(String s_time,String e_time) {
      try {
        Connection conn=dbConnection.getConnection();
        String sql="INSERT INTO PROGRESS VALUES(?,?,?,?,?,?)";
        PreparedStatement pst=conn.prepareStatement(sql);
       pst.setString(1,name);
       pst.setString(2,role);
       pst.setString(3,project);
       pst.setString(4,s_time);
       pst.setString(5,e_time);
       pst.setString(6,"");
       pst.execute();
       pst.close();
       conn.close();
       alert.setContentText("Developer Info Added Successfully!!!!");
       alert.show();
      }catch(Exception e) {
       alert.setContentText(e.toString());
       alert.show();
      }
  
  }
  
}
