package workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbUtil.dbConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

public class Consult extends Worker{

  public Consult(String name, String role, String project) {
    super(name, role, project);

  }
   Alert alert=new Alert(AlertType.CONFIRMATION);
   
  void addOpinion(String opinio) {
  try {
    Connection conn=dbConnection.getConnection();
    String sql="INSERT INTO PROGRESS VALUES(?,?,?,?,?,?)";
    PreparedStatement pst=conn.prepareStatement(sql);
   pst.setString(1,name);
   pst.setString(2,role);
   pst.setString(3,project);
   pst.setString(4,"");
   pst.setString(5,"");
   pst.setString(6,opinio);
   pst.execute();
   pst.close();
   conn.close();
   alert.setContentText("Opnion Added Successfully!!!!");
   alert.show();
  }catch(Exception e) {
   alert.setContentText(e.toString());
   alert.show();
  }
  
  }
}
