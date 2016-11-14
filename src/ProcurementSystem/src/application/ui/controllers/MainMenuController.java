package application.ui.controllers;

import application.ui.views.ViewLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainMenuController extends BaseController {
	
	@FXML
	private void loadCreateOrderScreen() {
		viewLoader.loadMenuedScreen("/application/ui/views/CreateOrder.fxml");
	}
	
	@FXML
	private void loadMyOrdersScreen() {
		viewLoader.loadMenuedScreen("/application/ui/views/MyOrders.fxml");
	}
	
	@FXML
	private void loadReviewOrdersScreen() {
		viewLoader.loadMenuedScreen("/application/ui/views/ReviewOrder.fxml");
	}
}
