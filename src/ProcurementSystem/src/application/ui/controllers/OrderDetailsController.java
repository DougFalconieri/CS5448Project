package application.ui.controllers;

import application.models.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderDetailsController extends BaseController {
	@FXML
	private Label itemLabel;
	@FXML
	private Label createdDateLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label quantityLabel;
	@FXML
	private Label facilityLabel;
	@FXML
	private Label roomLabel;
	@FXML
	private Label justificationLabel;
	
	@Override
	public void onLoad() {
		Order order = applicationController.getCurrentOrder();
		itemLabel.setText(order.getItem().toString());
		createdDateLabel.setText(order.getCreatedDate().toString());
		descriptionLabel.setText(order.getDescription());
		quantityLabel.setText(Integer.toString(order.getQuantity()));
		facilityLabel.setText(order.getFacility().toString());
		roomLabel.setText(order.getRoom());
		justificationLabel.setText(order.getJustification());
	}

	@FXML
	private void goToListScreen() {
		applicationController.loadMenuedScreen("/application/ui/views/MyOrders.fxml");
	}
}
