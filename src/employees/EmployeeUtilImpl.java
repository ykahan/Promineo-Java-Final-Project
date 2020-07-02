package employees;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EmployeeUtilImpl implements EmployeeUtil{

	@Override
	public int calculateTotalTaskHours(Project project) {
		int totalTaskHours = 0;
		for (Task task : project.getTasks()) {
			totalTaskHours += task.getEstimatedHours();
		}
		return totalTaskHours;
	}

	@Override
	public int calculateEmployeeTaskHours(Employee employee) {
		int totalTaskHours = 0;
		for (Project project : employee.getProjects()) {
			totalTaskHours += calculateTotalTaskHours(project);
		}
		return totalTaskHours;
	}

	@Override
	public List<Project> findPastDueProjects(Employee employee) {
		List<Project> pastDueProjects = new ArrayList<>();
		for (Project project : employee.getProjects()) {
			if (!project.isComplete() && project.getExpectedCompletionDate().compareTo(new Date()) < 0) {
				pastDueProjects.add(project);
			}
		}
		return pastDueProjects;
	}

	@Override
	public List<Project> findProjectsDueInXDays(Employee employee, int numOfDays) {
		List<Project> dueProjects = new ArrayList<>();
		for (Project project : employee.getProjects()) {
			Long diffInDays = TimeUnit.DAYS.convert(project.getExpectedCompletionDate().getTime() 
					- new Date().getTime(), TimeUnit.MILLISECONDS);
			if (!project.isComplete() && diffInDays <= numOfDays) {
				dueProjects.add(project);
			}
		}
		return dueProjects;
	}

	@Override
	public int calculateTotalCompletedHours(Employee employee) {
		int completedHours = 0;
		for (Project project : employee.getProjects()) {
			if (project.isComplete()) {
				completedHours += calculateTotalTaskHours(project);
			}
		}
		return completedHours;
	}

	@Override
	public Employee findEmployeeWithMostIncompleteTaskHours(List<Employee> employees) {
		Employee employee = null;
		int greatestIncompleteTaskHours = 0;
		//iterate over each employee
		for (Employee e : employees) {
			//start the count of incomplete task hours for each employee
			int incompleteTaskHours = 0;
			
			//find which project are incomplete and add the task hours if so
			for (Project project : e.getProjects()) {
				if (!project.isComplete()) {
					incompleteTaskHours += calculateTotalTaskHours(project);
				}
			}
			
			//see if we have a new winner for most incomplete task hours
			if (incompleteTaskHours > greatestIncompleteTaskHours) {
				employee = e;
			}
		}
		return employee;
	}

	@Override
	public int calculateTotalTaskHoursByState(List<Employee> employees, String stateName) {
		int totalTaskHours = 0;
		for (Employee employee : employees) {
			if (employee.getAddress().getState().equals(stateName)) {
				totalTaskHours += calculateEmployeeTaskHours(employee);
			}
		}
		return totalTaskHours;
	}

	@Override
	public String findStateWithMostIncompleteTaskHours(List<Employee> employees) {
		//StateName, Total
		Map<String, Integer> stateTotals = new HashMap<String, Integer>();
		for (Employee e : employees) {
			String stateName = e.getAddress().getState();
			
			int incompleteTaskHours = 0;
			for (Project project : e.getProjects()) {
				incompleteTaskHours += calculateTotalTaskHours(project);
			}
			
			if (stateTotals.get(stateName) == null) {
				stateTotals.put(stateName, incompleteTaskHours);
			} else {
				stateTotals.put(stateName, stateTotals.get(stateName) + incompleteTaskHours);
			}
		}
		
		String stateWithMostIncompleteTaskHours = "";
		int greatestIncompleteTaskHours = 0;
		
		for (String stateName : stateTotals.keySet()) {
			if (stateTotals.get(stateName) > greatestIncompleteTaskHours) {
				greatestIncompleteTaskHours = stateTotals.get(stateName);
				stateWithMostIncompleteTaskHours = stateName;
			}
		}
		
		return stateWithMostIncompleteTaskHours;
	}

	@Override
	public Project markProjectAsComplete(List<Employee> employees, String employeeId, String projectId) {
		Project project = null;
		for (Employee e : employees) {
			if (e.getId().equals(employeeId)) {
				for (Project p : e.getProjects()) {
					if (p.getId().equals(projectId)) {
						p.setComplete(true);
						p.setCompletedDate(new Date());
						project = p;
					}
				}
			}
		}
		return project;
	}

}
