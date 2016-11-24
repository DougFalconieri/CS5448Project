package application.ui.controllers;

import java.sql.Date;
import java.util.List;

import application.models.Facility;
import application.models.Item;
import application.models.ItemCategory;
import application.models.Order;
import application.models.status.OrderStatus;
import application.repositories.ItemRepository;
import application.repositories.OrderRepository;
import application.repositories.UserRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateOrderController extends BaseController {
	private static final String ERROR_HEADER = "Save Failed";
	private static final String INT_REGEX = "\\d+";
	
	private ItemRepository itemRepository;
	private OrderRepository orderRepository;
	private UserRepository userRepository;
	
	@FXML
	private ChoiceBox<ItemCategory> categoryChoiceBox;
	@FXML
	private ChoiceBox<Item> itemChoiceBox;
	@FXML
	private ChoiceBox<Facility> facilityChoiceBox;
	@FXML
	private TextArea descriptionTextArea;
	@FXML
	private TextField quantityTextBox;
	@FXML
	private TextField roomTextBox;
	@FXML
	private TextArea justificationTextArea;
	
	private List<ItemCategory> categories;
	private List<Facility> facilities;
	
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private void copyDataToControls() {
		Order order = applicationController.getCurrentOrder();
		itemChoiceBox.setValue(order.getItem());
		descriptionTextArea.setText(order.getDescription());;
		quantityTextBox.setText(Integer.toString(order.getQuantity()));
		facilityChoiceBox.setValue(order.getFacility());
		roomTextBox.setText(order.getRoom());
		justificationTextArea.setText(order.getJustification());
	}
	
	@Override
	public void onLoad() {
		categories = itemRepository.getCategories();
		categoryChoiceBox.setItems(FXCollections.observableArrayList(categories));
		//categoryChoiceBox.setValue(categories.get(0));
		
		facilities = userRepository.getFacilities();
		facilityChoiceBox.setItems(FXCollections.observableArrayList(facilities));
		
		if (applicationController.getCurrentOrder() != null) {
			copyDataToControls();
		}
	}
	
	@FXML
	private void categorySelected() {
		ItemCategory selectedCategory = categoryChoiceBox.valueProperty().get();
		itemChoiceBox.setItems(FXCollections.observableArrayList(selectedCategory.getItems()));
	}
	
	private boolean copyDataToOrder(Order order) {
		order.setCreatedDate(getCurrentDate());
		order.setEmployee(getCurrentUser());
		
		Item selectedItem = itemChoiceBox.valueProperty().get();
		if (selectedItem == null) {
			showError(ERROR_HEADER, "Item is required");
			return false;
		}
		order.setItem(selectedItem);
		
		if (descriptionTextArea.getText().length() == 0) {
			showError(ERROR_HEADER, "Description is required");
			return false;
		}
		order.setDescription(descriptionTextArea.getText());
		
		if (quantityTextBox.getText().length() == 0) {
			showError(ERROR_HEADER, "Quantity is required");
			return false;
		}
		if (!quantityTextBox.getText().matches(INT_REGEX)) {
			showError(ERROR_HEADER, "Quantity must be an integer");
			return false;
		}
		order.setQuantity(Integer.parseInt(quantityTextBox.getText()));
		
		Facility selectedFacility = facilityChoiceBox.valueProperty().get();
		if (selectedFacility == null) {
			showError(ERROR_HEADER, "Facility is required");
			return false;
		}
		order.setFacility(selectedFacility);
		
		if (roomTextBox.getText().length() == 0) {
			showError(ERROR_HEADER, "Room is required");
			return false;
		}
		order.setRoom(roomTextBox.getText());
		
		if (justificationTextArea.getText().length() == 0) {
			showError(ERROR_HEADER, "Justification is required");
			return false;
		}
		order.setJustification(justificationTextArea.getText());
		
		return true;
	}
	
	private Order getCurrentOrder() {
		return applicationController.getCurrentOrder() == null ? new Order() : applicationController.getCurrentOrder();
	}
	
	private void saveOrder(String status) {
		Order order = getCurrentOrder();
		if (copyDataToOrder(order)) {
			order.setStatus(status);
			orderRepository.saveOrder(order);
			applicationController.loadMenuedScreen("/application/ui/views/MyOrders.fxml");
		}
	}
	
	@FXML
	private void saveInWorkOrder() {
		saveOrder(OrderStatus.IN_WORK);
	}
	
	@FXML
	private void submitOrder() {
		saveOrder(OrderStatus.SUBMITTED);
	}
	
	@FXML
	private void goToListScreen() {
		applicationController.loadMenuedScreen("/application/ui/views/MyOrders.fxml");
	}
}
