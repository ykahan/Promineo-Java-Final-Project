package myEmployees;

import myEmployees.Task;

import java.util.Date;
import java.util.List;

public class Project {
    private int taskHours;
    private List<Task> tasks;
    private Date dueDate;
    boolean isComplete;
    private Date expectedCompletionDate;
    private Date completedDate;
    private String projectId;

    public Project(int taskHours, List<Task> tasks, Date dueDate, boolean isComplete, Date expectedCompletionDate, String projectId) {
        this.taskHours = taskHours;
        this.tasks = tasks;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
        this.expectedCompletionDate = expectedCompletionDate;
        this.projectId = projectId;
        this.completedDate = null;
    }

    public Date getCompletedDate(){
        return this.completedDate;
    }

    public void setCompletedDate(Date date){
        this.completedDate = date;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setIsComplete(boolean isComplete){
        this.isComplete = isComplete;
    }

    public int getTaskHours(){
        return taskHours;
    }

    public void setTaskHours(){
        int localTaskHours = 0;
        for(Task task: tasks){
            localTaskHours += task.getTaskHours();
        }
        taskHours = localTaskHours;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public String getProjectID() {
        return projectId;
    }
}
