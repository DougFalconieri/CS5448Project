package application.ui.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.models.Order;
import application.repositories.ItemRepository;
import application.repositories.OrderRepository;
import application.repositories.HibernateOrderRepository;
import application.repositories.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyOrdersController extends BaseController implements Initializable {
	private OrderRepository orderRepository;
	
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

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
		          new PropertyValueFactory<Order, String>("description");
		PropertyValueFactory<Order, Integer> quantityProperty = 
		          new PropertyValueFactory<Order, Integer>("quantity");
		PropertyValueFactory<Order, String> statusProperty = 
		          new PropertyValueFactory<Order, String>("status");
		      
		itemColumn.setCellValueFactory(itemProperty);
		quantityColumn.setCellValueFactory(quantityProperty);
		statusColumn.setCellValueFactory(statusProperty);
	}

	
	@Override
	public void onLoad() {
		List<Order> orders = orderRepository.getOrdersForEmployee(getCurrentUser());
		System.out.println(orders.size());
		orderTable.setItems(FXCollections.observableList(orders));
		orderTable.refresh();
	}
	
	
}
