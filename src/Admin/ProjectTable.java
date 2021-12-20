package Admin;

public class ProjectTable {
public ProjectTable(String pro_name, String pro_stime, String pro_etime) {
    super();
    this.pro_name = pro_name;
    this.pro_stime = pro_stime;
    this.pro_etime = pro_etime;
  }

String pro_name,pro_stime,pro_etime;

public String getPro_name() {
  return pro_name;
}

public void setPro_name(String pro_name) {
  this.pro_name = pro_name;
}

public String getPro_stime() {
  return pro_stime;
}

public void setPro_stime(String pro_stime) {
  this.pro_stime = pro_stime;
}

public String getPro_etime() {
  return pro_etime;
}

public void setPro_etime(String pro_etime) {
  this.pro_etime = pro_etime;
}
}
