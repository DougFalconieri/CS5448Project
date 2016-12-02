package application.ui.controllers;

import java.util.List;

import application.models.Order;
import application.models.Vendor;
import application.repositories.OrderRepository;
import application.repositories.VendorRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditOrderController extends BaseController {
	private static final String ERROR_HEADER = "Save Failed";

	private OrderRepository orderRepository;
	private VendorRepository vendorRepository;

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public void setVendorRepository(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}

	@FXML
	private Label item;
	@FXML
	private Label quantity;
	@FXML
	private TextField total;
	@FXML
	private Label status;
	@FXML
	private TextField trackingNumber;
	@FXML
	private DatePicker expectedDeliveryDate;
	@FXML
	private ChoiceBox<Vendor> vendorList;

	private List<Vendor> vendors;

	@Override
	public void onLoad() {
		Order currentOrder = applicationController.getCurrentOrder();

		item.setText(currentOrder.getItem().toString());
		quantity.setText(Integer.toString(currentOrder.getQuantity()));
		status.setText(currentOrder.getStatus());

		if (currentOrder.getTotal() != 0.0f) {
			total.setText(Float.toString(currentOrder.getTotal()));			
		}

		if (currentOrder.getTrackingNumber() != null) {
			trackingNumber.setText(currentOrder.getTrackingNumber());
		}
		if (currentOrder.getExpectedDeliveryDate() != null) {
			expectedDeliveryDate.setValue(currentOrder.getExpectedDeliveryDate().toLocalDate());
		}

		vendors = vendorRepository.getVendors();
		vendorList.setItems(FXCollections.observableArrayList(vendors));

		if (currentOrder.getVendor() != null) {
			vendorList.getSelectionModel().select(currentOrder.getVendor());
		}
	}

    private boolean isDouble(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
	private boolean copyDataToOrder(Order order) {

		if (!isDouble(total.getText())) {
			showError(ERROR_HEADER, "Please enter valid total");
			return false;
		}
		order.setTotal(Float.parseFloat(total.getText()));

		Vendor selectedVendor = vendorList.valueProperty().get();
		if (selectedVendor == null) {
			showError(ERROR_HEADER, "Vendor is required");
			return false;
		}
		order.setVendor(selectedVendor);

		if (trackingNumber.getText().length() == 0) {
			showError(ERROR_HEADER, "Tracking number is required");
			return false;
		}
		order.setTrackingNumber(trackingNumber.getText());

		if (expectedDeliveryDate.getValue() == null) {
			showError(ERROR_HEADER, "Expected Delivery Date is required");
			return false;
		}
		order.setExpectedDeliveryDate(java.sql.Date.valueOf(expectedDeliveryDate.getValue()));

		return true;
	}

	@FXML
	private void saveOrder() {
		Order order = applicationController.getCurrentOrder();
		if (copyDataToOrder(order)) {
			order.setStatus("Processed");
			order.setExecutedDate(getCurrentDate());
			orderRepository.saveOrder(order);
			applicationController.loadMenuedScreen("/application/ui/views/ProcessOrder.fxml");
		}
	}
}
