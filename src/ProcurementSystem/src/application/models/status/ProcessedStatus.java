package application.models.status;

import application.models.Order;
import application.models.User;

public class ProcessedStatus extends OrderStatus {

	@Override
	public boolean canBeEditedByUser(Order order, User user) {
		return false;
	}

	@Override
	public boolean canBeCanceledByUser(Order order, User user) {
		return false;
	}

}
