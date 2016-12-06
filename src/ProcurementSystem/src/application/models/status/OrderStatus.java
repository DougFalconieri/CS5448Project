package application.models.status;

import application.models.Order;
import application.models.User;

public abstract class OrderStatus {
	public static final String IN_WORK = "In Work";
	public static final String SUBMITTED = "Submitted";
	public static final String CANCELED = "Canceled";
	public static final String APPROVED = "Approved";
	public static final String PROCESSED = "Processed";
	
	private static OrderStatus inWorkStatus = new InWorkStatus();
	private static OrderStatus submittedStatus = new SubmittedStatus();
	private static OrderStatus canceledStatus = new CanceledStatus();
	private static OrderStatus approvedStatus = new ApprovedStatus();
	private static OrderStatus processedStatus = new ProcessedStatus();
	
	public static OrderStatus getStatusObject(String statusName) {
		if (statusName.equals(IN_WORK)) return inWorkStatus;
		if (statusName.equals(SUBMITTED)) return submittedStatus;
		if (statusName.equals(CANCELED)) return canceledStatus;
		if (statusName.equals(APPROVED)) return approvedStatus;
		if (statusName.equals(PROCESSED)) return processedStatus;
		throw new IllegalArgumentException("Unknown status: " + statusName);
	}
	
	public abstract boolean canBeEditedByUser(Order order, User user);
	public abstract boolean canBeCanceledByUser(Order order, User user);
}
