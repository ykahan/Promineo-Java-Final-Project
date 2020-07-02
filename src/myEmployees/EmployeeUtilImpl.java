package myEmployees;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class EmployeeUtilImpl implements EmployeeUtil{
    @Override
    public int calculateTotalTaskHours(Project project) {
        return project.getTaskHours();
    }

    @Override
    public int calculateEmployeeTaskHours(Employee employee) {
        return employee.getCumulativeTaskHours();
    }

    @Override
    public List<Project> findPastDueProjects(Employee employee) {
        List<Project> listOverdueProjects = new ArrayList<>();
        for(Project project: employee.getAllProjects()){
            Date dueDate = project.getDueDate();
            Date currentDate = new java.util.Date();
            if(dueDate.before(currentDate)){
                if(!project.isComplete()){
                    listOverdueProjects.add(project);
                }
            }
        }
        return listOverdueProjects;
    }

    @Override
    public List<Project> findProjectsDueInXDays(Employee employee, int xDays) {
        List<Project> projectsDueWithinXDays = new ArrayList<>();
        for(Project project: employee.getAllProjects()){
            if(!project.isComplete) {
                Date expectedCompletionDate = project.getExpectedCompletionDate();
                Date current = new java.util.Date();
                if (!expectedCompletionDate.before(current)) {
                    long diffInMillies = expectedCompletionDate.getTime() - current.getTime();
                    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    if (diffInDays <= xDays) projectsDueWithinXDays.add(project);
                }
            }
        }
        return projectsDueWithinXDays;
    }

    @Override
    public int calculateTotalCompletedHours(Employee employee) {
        int completedHours = 0;
        for(Project project: employee.getAllProjects()){
            if(project.isComplete()){
                completedHours += project.getTaskHours();
            }
        }
        return completedHours;
    }

    @Override
    public Employee findEmployeeWithMostIncompleteTaskHours(List<Employee> employeeList) {
        Employee empWithMostIncompleteTaskHours = new Employee();
        int mostIncompleteTaskHours = 0;
        for(Employee employee: employeeList){
            int localIncompleteTaskHours = 0;
            localIncompleteTaskHours = employee.getIncompleteTaskHours();
            if(localIncompleteTaskHours > mostIncompleteTaskHours){
                mostIncompleteTaskHours = localIncompleteTaskHours;
                empWithMostIncompleteTaskHours = employee;
            }
        }
        return empWithMostIncompleteTaskHours;
    }

    @Override
    public int calculateTotalTaskHoursByState(List<Employee> employeeList, String state) {
        int totalIncompleteTaskHours = 0;
        for(Employee employee: employeeList){
            if(state.toLowerCase().contentEquals(employee.getState().toLowerCase())){
                totalIncompleteTaskHours += employee.getIncompleteTaskHours();
            }
        }
        return totalIncompleteTaskHours;
    }

    @Override
    public String findStateWithMostIncompleteTaskHours(List<Employee> employeeList) {
        Map<String, Integer> states = new HashMap<>();
        for(Employee employee: employeeList){
            String state = employee.getState().toLowerCase();
            int hours = employee.getIncompleteTaskHours();
            if(!states.containsKey(state)) states.put(state, hours);
            else states.put(state, states.get(state) + hours);
        }
        return getKeyWithMaxValue(states);
    }

    public String getKeyWithMaxValue(Map<String, Integer> map){
        int maxScoreFound = 0;
        String maxKeyFound = "";
        for(String key: map.keySet()){
            int scoreFound = map.get(key);
            if(scoreFound > maxScoreFound) {
                maxScoreFound = scoreFound;
                maxKeyFound = key;
            }
        }
        return maxKeyFound;
    }

    @Override
    public void markProjectAsComplete(List<Employee> employees, String employeeID, String projectID) {
        for(Employee employee: employees){
            if(employee.getEmployeeID().contentEquals(employeeID)){
                for(Project project: employee.getAllProjects()){
                    if(project.getProjectID().contentEquals(projectID)){
                        project.setIsComplete(true);
                        project.setCompletedDate(new java.util.Date());
                    }
                }
            }
        }
    }
}
