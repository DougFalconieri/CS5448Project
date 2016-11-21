package application.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Order;
import application.repositories.OrderRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyOrdersController implements Initializable {
	@FXML
	private TableView<Order> orderTable;
	@FXML
	private TableColumn<Order, String> itemColumn;
	@FXML
	private TableColumn<Order, Integer> quantityColumn;
	@FXML
	private TableColumn<Order, String> statusColumn;
	@FXML
	private TableColumn<Order, String> deliveryColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PropertyValueFactory<Order, String> itemProperty = 
		          new PropertyValueFactory<Order, String>("item");
		PropertyValueFactory<Order, Integer> quantityProperty = 
		          new PropertyValueFactory<Order, Integer>("quantity");
		PropertyValueFactory<Order, String> statusProperty = 
		          new PropertyValueFactory<Order, String>("status");
		PropertyValueFactory<Order, String> deliveryProperty = 
		          new PropertyValueFactory<Order, String>("expectedDelivery");
		      
		itemColumn.setCellValueFactory(itemProperty);
		quantityColumn.setCellValueFactory(quantityProperty);
		statusColumn.setCellValueFactory(statusProperty);
		deliveryColumn.setCellValueFactory(deliveryProperty);
		
		OrderRepository repository = new OrderRepository();
		ObservableList<Order> tableItems = FXCollections.observableArrayList();
		tableItems.addAll(repository.getOrders());
		orderTable.setItems(tableItems);
	}
	
	
}
