#Java Final Exam Practice#
Using the classes provided in this package, create an interface named EmployeeUtil, and an implementing class named EmployeeUtilImpl.

EmployeeUtil should contain the following methods:

- calculateTotalTaskHours - should take a Project and return the total of the estimatedHours for all tasks in the project.
- calculateEmployeeTaskHours - should take an Employee and return the total of all the task estimatedHours for all projects the employee is assigned to.
- findPastDueProjects - should take an Employee and return a list of all the projects where the expectedCompletionDate is in the past and the project is not yet marked completed.
- findProjectsDueInXDays - should take an Employee and an integer that represents a number of days. Return all projects assigned to the Employee that have an expectedCompletion date within the number of days specified.
- calculateTotalCompletedHours - should take an Employee and return the total amount of task hours on all the employee's completed projects.
- findEmployeeWithMostIncompleteTaskHours - should take a list of employees and return the employee with the most task hours for projects that are not yet complete.
- calculateTotalTaskHoursByState - should take a list of employees and a String that represents the name of a state and return the total amount of task hours (complete or incomplete) for employees who live in that state.
- findStateWithMostIncompleteTaskHours - should take a list of employees and return a string that represents the state name that has the most incomplete tasks hours.
- markProjectAsComplete - should take an employee id and a project id and mark the project as completed with the current date. Return the project that was marked completed.