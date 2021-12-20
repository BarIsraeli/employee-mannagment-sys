package Startup;

public class Worker {
    private final String role;
    private String project;
    private final String name;

    public Worker(String name, String role, String project) {
        this.name = name;
        this.role = role;
        this.project = project;
    }

    public String getName() {
        return this.name;
    }


    public String getProject() {
        return this.project;
    }
    public void setProject(String project){
        this.project=project;
    }

    public String getRole() {
        return this.role;
    }

    public void Login() {
        System.out.println(this.getName() + " is logged in");
    }

    public void Work() {
        System.out.println(this.getName() + "is working on" + this.getProject());
    }
}
