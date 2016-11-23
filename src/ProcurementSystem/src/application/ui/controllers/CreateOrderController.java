package application.ui.controllers;

import java.sql.Date;
import java.util.List;

import application.models.Facility;
import application.models.Item;
import application.models.ItemCategory;
import application.models.Order;
import application.models.OrderStatus;
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
	
	@Override
	public void onLoad() {
		categories = itemRepository.getCategories();
		categoryChoiceBox.setItems(FXCollections.observableArrayList(categories));
		//categoryChoiceBox.setValue(categories.get(0));
		
		facilities = userRepository.getFacilities();
		facilityChoiceBox.setItems(FXCollections.observableArrayList(facilities));
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
	
	@FXML
	private void saveOrder() {
		Order order = new Order();
		if (copyDataToOrder(order)) {
			order.setStatus(OrderStatus.IN_WORK);
			orderRepository.saveOrder(order);
		}
	}
	
	@FXML
	private void submitOrder() {
		Order order = new Order();
		if (copyDataToOrder(order)) {
			order.setStatus(OrderStatus.SUBMITTED);
			orderRepository.saveOrder(order);
		}
	}
}
