package application.models;

import java.util.Date;

public class PurchaseOrder {
	private String item;
	private int quantity;
	private String status;
	private String requestor;
	private String expectedDelivery;
	
	
	public PurchaseOrder(String item, int quantity, String status, String requestor, String expectedDelivery) {
		this.item = item;
		this.quantity = quantity;
		this.status = status;
		this.requestor = requestor;
		this.expectedDelivery = expectedDelivery;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getExpectedDelivery() {
		return expectedDelivery;
	}
	public void setExpectedDelivery(String expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}
}
