package application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="employeeinfo")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="review_order")
	private String reviewOrder;

	@Column(name="status")
	private String status;


	public Employee(){

	}

	public Employee(String firstName, String lastName, String reviewOrder) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.reviewOrder = reviewOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getreviewOrder() {
		return reviewOrder;
	}

	public void setreviewOrder(String reviewOrder) {
		this.reviewOrder = reviewOrder;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", reviewOrder=" + reviewOrder + ", status=" + status +"]";
	}


}
