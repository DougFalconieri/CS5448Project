package application.ui.controllers;

import application.ui.views.ViewLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainMenuController {	
	private ViewLoader viewLoader = new ViewLoader();	
	private Stage stage;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private void loadCreateOrderScreen() {
		viewLoader.loadMenuedScreen("/application/ui/views/CreateOrder.fxml", stage);
	}
	
	@FXML
	private void loadMyOrdersScreen() {
		viewLoader.loadMenuedScreen("/application/ui/views/MyOrders.fxml", stage);
	}
	
	@FXML
	private void loadReviewOrdersScreen() {
		viewLoader.loadMenuedScreen("/application/ui/views/ReviewOrder.fxml", stage);
	}
}
