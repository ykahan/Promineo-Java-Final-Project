package myEmployees;

public class Task {
    private int taskHours;

    public Task(int taskHours) {
        this.taskHours = taskHours;
    }

    public int getTaskHours(){
        return taskHours;
    }

    public void incrementTaskHours(){
        taskHours++;
    }

    public void decrementTaskHours(){
        if(taskHours - 1 >= 0) taskHours--;
    }

    public void adjustTaskHours(int adjustment){
        if(taskHours + adjustment >= 0) taskHours += adjustment;
    }

    public void setTaskHours(int taskHours){
        this.taskHours = taskHours;
    }
}
