package customers;
import java.util.List;

public class Customer {
	
	private String id;
	private List<Bill> statement;
	private Address address;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Bill> getStatement() {
		return statement;
	}
	
	public void setStatement(List<Bill> statement) {
		this.statement = statement;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

}
