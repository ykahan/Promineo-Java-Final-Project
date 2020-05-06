package employees;

public class Task {
	
	private String id;
	private int estimatedHours;
	private String description;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getEstimatedHours() {
		return estimatedHours;
	}
	
	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
