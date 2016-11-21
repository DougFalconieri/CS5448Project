package application.repositories;

import java.util.ArrayList;
import java.util.List;
import application.models.Order;

public class OrderRepository {
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<Order>();
		//orders.add(new Order("Staples", 200, "Approved", "Jane Smith", "10/20/2016"));
		//orders.add(new Order("Tablet", 1, "Shipped", "Jane Smith", "10/23/2016"));
		return orders;
	}
}
