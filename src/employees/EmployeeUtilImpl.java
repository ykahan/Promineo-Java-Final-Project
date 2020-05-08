package employees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
		List<Project> pastDueProjects = new ArrayList<Project>();
		for (Project project : employee.getProjects()) {
			if (!project.isComplete() && project.getExpectedCompletionDate().compareTo(new Date()) < 0) {
				pastDueProjects.add(project);
			}
		}
		return pastDueProjects;
	}

	@Override
	public List<Project> findProjectsDueInXDays(Employee employee, int numOfDays) {
		List<Project> dueProjects = new ArrayList<Project>();
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

}
