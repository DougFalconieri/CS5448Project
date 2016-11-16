package application.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.PurchaseOrder;
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
	private TableView<PurchaseOrder> orderTable;
	@FXML
	private TableColumn<PurchaseOrder, String> itemColumn;
	@FXML
	private TableColumn<PurchaseOrder, Integer> quantityColumn;
	@FXML
	private TableColumn<PurchaseOrder, String> statusColumn;
	@FXML
	private TableColumn<PurchaseOrder, String> deliveryColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PropertyValueFactory<PurchaseOrder, String> itemProperty = 
		          new PropertyValueFactory<PurchaseOrder, String>("item");
		PropertyValueFactory<PurchaseOrder, Integer> quantityProperty = 
		          new PropertyValueFactory<PurchaseOrder, Integer>("quantity");
		PropertyValueFactory<PurchaseOrder, String> statusProperty = 
		          new PropertyValueFactory<PurchaseOrder, String>("status");
		PropertyValueFactory<PurchaseOrder, String> deliveryProperty = 
		          new PropertyValueFactory<PurchaseOrder, String>("expectedDelivery");
		      
		itemColumn.setCellValueFactory(itemProperty);
		quantityColumn.setCellValueFactory(quantityProperty);
		statusColumn.setCellValueFactory(statusProperty);
		deliveryColumn.setCellValueFactory(deliveryProperty);
		
		OrderRepository repository = new OrderRepository();
		ObservableList<PurchaseOrder> tableItems = FXCollections.observableArrayList();
		tableItems.addAll(repository.getOrders());
		orderTable.setItems(tableItems);
	}
	
	
}
