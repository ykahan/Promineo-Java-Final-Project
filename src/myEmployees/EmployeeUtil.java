package myEmployees;

import java.util.List;

public interface EmployeeUtil {
    public int calculateTotalTaskHours(Project project);
    public int calculateEmployeeTaskHours(Employee employee);
    public List<Project> findPastDueProjects(Employee employee);
    public List<Project> findProjectsDueInXDays(Employee employee, int day);
    public int calculateTotalCompletedHours(Employee employee);
    public Employee findEmployeeWithMostIncompleteTaskHours(List<Employee> employeeList);
    public int calculateTotalTaskHoursByState(List<Employee> employeeList, String state);
    public String findStateWithMostIncompleteTaskHours(List<Employee> employeeList);
    public void markProjectAsComplete(List<Employee> employees, String employeeID, String projectID);
}
