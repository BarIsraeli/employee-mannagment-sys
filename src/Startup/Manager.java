package Startup;



public class Manager extends Consult {


    public Manager(String name, String role, String project) {
        super(name, role, project);
    }

    public void ChangeProject(Worker W, String project)
    {
        W.setProject(project);
    }
    public void DeleteWorker(Worker W){
        if (W.getName()!=null){
            //remove worker from db
        }

        }
    public void Work() {
        System.out.println(this.getName() + "is managing " + this.getProject());
    }
    public void VerifyLogin(Worker w){
        // check if this worker should work now
    }
    public void SetWorkingHours(){
        // set the working hours for this worker
    }
}
