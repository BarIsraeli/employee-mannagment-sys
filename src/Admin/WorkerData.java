package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkerData
{
   String Name;
  String Role;
  String Project;
  String Email;
 public String getName() {
    return Name;
  }
  public void setName(String name) {
    Name = name;
  }
  public String getRole() {
    return Role;
  }
  public void setRole(String role) {
    Role = role;
  }
  public String getProject() {
    return Project;
  }
  public void setProject(String project) {
    Project = project;
  }
  public String getEmail() {
    return Email;
  }
  public void setEmail(String email) {
    Email = email;
  }
  public String getUserName() {
    return UserName;
  }
  public void setUserName(String userName) {
    UserName = userName;
  }
  public String getPassword() {
    return Password;
  }
  public void setPassword(String password) {
    Password = password;
  }
public WorkerData(String name, String role, String project, String email, String userName,
      String password) {
    super();
    Name = name;
    Role = role;
    Project = project;
    Email = email;
    UserName = userName;
    Password = password;
  }
String UserName;
    String Password;



 
}
