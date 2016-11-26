package application.repositories;

import java.util.List;

import application.models.Order;
import application.models.User;

public interface OrderRepository {
	List<Order> getOrdersForEmployee(User employee);
	void saveOrder(Order order);
	void cancelOrder(int orderId);
}
