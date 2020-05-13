package employees;

import java.util.List;

public interface EmployeeUtil {

	public int calculateTotalTaskHours(Project project);
	
	public int calculateEmployeeTaskHours(Employee employee);
	
	public List<Project> findPastDueProjects(Employee employee);
	
	public List<Project> findProjectsDueInXDays(Employee employee, int numOfDays);
	
	public int calculateTotalCompletedHours(Employee employee);
	
	public Employee findEmployeeWithMostIncompleteTaskHours(List<Employee> employees);
	
	public int calculateTotalTaskHoursByState  (List<Employee> employees, String stateName);
	
	public String findStateWithMostIncompleteTaskHours(List<Employee> employees);
	
	public Project markProjectAsComplete(List<Employee> employees, String employeeId, String taskId);
	
}
