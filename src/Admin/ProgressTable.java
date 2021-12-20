package Admin;

public class ProgressTable {
  
  String name,role,project,stime,etime,opinion;

  public ProgressTable(String name, String role, String project, String stime, String etime,
      String opinion) {
    super();
    this.name = name;
    this.role = role;
    this.project = project;
    this.stime = stime;
    this.etime = etime;
    this.opinion = opinion;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getStime() {
    return stime;
  }

  public void setStime(String stime) {
    this.stime = stime;
  }

  public String getEtime() {
    return etime;
  }

  public void setEtime(String etime) {
    this.etime = etime;
  }

  public String getOpinion() {
    return opinion;
  }

  public void setOpinion(String opinion) {
    this.opinion = opinion;
  }

}
