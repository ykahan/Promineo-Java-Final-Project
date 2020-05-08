package employees;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeUtilImplTest {
	
	private EmployeeUtil util = new EmployeeUtilImpl();
	private Project project;
	private Employee employee;
	private Project pastDueProject;
	private Project pastDueProject2;

	@Test
	void testCalculateTotalTaskHours() {
		generateObjects();
		assertEquals(15, util.calculateTotalTaskHours(project));
	}
	
	@Test
	void testFindPastDueProjects() {
		generateObjects();
		assertEquals(pastDueProject, util.findPastDueProjects(employee).get(0));
		assertEquals(pastDueProject2, util.findPastDueProjects(employee).get(1));
	}
	
	private void generateObjects() {
		project = new Project();
		
		Task task1 = new Task();
		task1.setEstimatedHours(3);
		Task task2 = new Task();
		task2.setEstimatedHours(10);
		Task task3 = new Task();
		task3.setEstimatedHours(2);
		
		LocalDateTime ldtToday = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		project.setExpectedCompletionDate(Date.from(ldtToday.plusDays(5).atZone(ZoneId.systemDefault()).toInstant()));
		
		project.setTasks(new ArrayList<Task>());
		project.getTasks().add(task1);
		project.getTasks().add(task2);
		project.getTasks().add(task3);
		
		pastDueProject = new Project();
		pastDueProject.setExpectedCompletionDate(Date.from(ldtToday.minusDays(5).atZone(ZoneId.systemDefault()).toInstant()));
		
		pastDueProject2 = new Project();
		pastDueProject2.setExpectedCompletionDate(Date.from(ldtToday.minusDays(5).atZone(ZoneId.systemDefault()).toInstant()));
		
		employee = new Employee();
		List<Project> projects = new ArrayList<Project>();
		projects.add(project);
		projects.add(pastDueProject);
		projects.add(pastDueProject2);
		employee.setProjects(projects);
		
	}

}
