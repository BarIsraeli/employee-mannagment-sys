package workers;

public class Worker {
String name,role,project;

public String getName() {
  return name;
}

public Worker(String name, String role, String project) {
  super();
  this.name = name;
  this.role = role;
  this.project = project;
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
}
