package myEmployees;

import java.util.List;

public class Employee {
    private List<Project> projects;
    private String state;
    private String employeeID;

    public Employee(List<Project> projects, String state, String employeeID) {
        this.projects = projects;
        this.state = state;
        this.employeeID = employeeID;
    }

    public int getCumulativeTaskHours(){
        int taskHours = 0;
        for(Project project: projects){
            taskHours += project.getTaskHours();
        }
        return taskHours;
    }

    public List<Project> getAllProjects(){
        return projects;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public int getIncompleteTaskHours(){
        int hours = 0;
        for(Project project: getAllProjects()){
            if(!project.isComplete){
                hours += project.getTaskHours();
            }
        }
        return hours;
    }

    public String getEmployeeID() {
        return employeeID;
    }
}
