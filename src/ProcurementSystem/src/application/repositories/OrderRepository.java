package application.repositories;

import java.util.ArrayList;
import java.util.List;
import application.models.PurchaseOrder;

public class OrderRepository {
	public List<PurchaseOrder> getOrders() {
		List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
		orders.add(new PurchaseOrder("Staples", 200, "Approved", "Jane Smith", "10/20/2016"));
		orders.add(new PurchaseOrder("Tablet", 1, "Shipped", "Jane Smith", "10/23/2016"));
		return orders;
	}
}
