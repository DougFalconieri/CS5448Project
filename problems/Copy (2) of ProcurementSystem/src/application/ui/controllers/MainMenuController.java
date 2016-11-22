package application.ui.controllers;


import java.io.IOException;

import application.Main;
import application.ui.controllers.PersonOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainMenuController extends BaseController {
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu managerMenu;
	@FXML
	private Menu procurementOfficerMenu;
	@FXML
	private Menu procurementManagerMenu;

	Main main;

	@Override
	public void onLoad() {
		if (!getCurrentUser().hasRole("Manager")) {
			menuBar.getMenus().remove(managerMenu);
		}

		if (!getCurrentUser().hasRole("Procurement Officer")) {
			menuBar.getMenus().remove(procurementOfficerMenu);
		}

		if (!getCurrentUser().hasRole("Procurement Manager")) {
			menuBar.getMenus().remove(procurementManagerMenu);
		}
	}

	@FXML
	private void loadCreateOrderScreen() {
		applicationController.loadMenuedScreen("/application/ui/views/CreateOrder.fxml");
	}

	@FXML
	private void loadMyOrdersScreen() {
		applicationController.loadMenuedScreen("/application/ui/views/MyOrders.fxml");
	}

	/*@FXML
	private void loadReviewOrdersScreen() {
		applicationController.loadMenuedScreen("/application/ui/views/ReviewOrder.fxml");
	}*/
	//Yi's code
	@FXML
	private void loadReviewOrdersScreen() throws IOException {
		//applicationController.loadMenuedScreen("/application/ui/views/PersonOverview.fxml");
		applicationController.loadScreen("/application/ui/views/PersonOverview.fxml");
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenuController.class.getResource("/application/ui/views/PersonOverview.fxml"));
		//BorderPane ReviewOrder = loader.load();
		//main.rootLayout.setCenter(ReviewOrder);
	    PersonOverviewController controller = loader.getController();
	    controller.setMain(controller);*/

	}
}
