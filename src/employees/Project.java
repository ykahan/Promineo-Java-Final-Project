package employees;

import java.util.Date;
import java.util.List;

public class Project {
	
	private String id;
	private List<Task> tasks;
	private Date expectedCompletionDate;
	private int size;
	private boolean isComplete;
	private Date completedDate;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public Date getExpectedCompletionDate() {
		return expectedCompletionDate;
	}
	
	public void setExpectedCompletionDate(Date expectedCompletionDate) {
		this.expectedCompletionDate = expectedCompletionDate;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
}
