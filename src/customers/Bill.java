package customers;
import java.util.Date;
import java.util.List;

public class Bill {
	
	private String id;
	private List<LineItem> lineItems;
	private Date dueDate;
	private Date datePaid;
	private boolean isPaid;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
}
