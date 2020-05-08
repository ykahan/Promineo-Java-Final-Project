package employees;

import java.util.List;

public interface EmployeeUtil {

	public int calculateTotalTaskHours(Project project);
	
	public int calculateEmployeeTaskHours(Employee employee);
	
	public List<Project> findPastDueProjects(Employee employee);
	
	public List<Project> findProjectsDueInXDays(Employee employee, int numOfDays);
	
	public int calculateTotalCompletedHours(Employee employee);
	
}
