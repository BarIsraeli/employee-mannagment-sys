package Startup;

public class Developer extends Worker {
    public Developer(String name, String role, String project) {
        super(name, role, project);
    }


    public void Develop() {
        System.out.println(this.getName() + "is developing right now");
    }
}
