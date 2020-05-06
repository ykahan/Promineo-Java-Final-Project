package employees;

import java.util.List;

public class Employee {
	
	private String id;
	private String firstName;
	private String lastName;
	private List<Project> projects;
	private Address address;
	private Compensation compensationPackage;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Compensation getCompensationPackage() {
		return compensationPackage;
	}
	
	public void setCompensationPackage(Compensation compensationPackage) {
		this.compensationPackage = compensationPackage;
	}
}
