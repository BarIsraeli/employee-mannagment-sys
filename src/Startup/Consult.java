package Startup;

public class Consult extends Developer {


    public Consult(String name, String role, String project) {
        super(name, role, project);
    }

    public void ProvideOpinion() {
        System.out.println(this.getName() + " thinks you can do better on " + this.getProject() + " project");
    }
}

