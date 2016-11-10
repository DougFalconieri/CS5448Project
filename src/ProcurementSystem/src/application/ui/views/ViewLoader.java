package application.ui.views;

import application.ui.controllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewLoader {
	public void loadMenuedScreen(String viewFilename, Stage stage) {
		try {
			FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(viewFilename));
		    Scene scene = sceneLoader.load();
		    FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/application/ui/views/MainMenu.fxml"));
		    MenuBar menu = menuLoader.load();
		    MainMenuController menuController = menuLoader.getController();
		    menuController.setStage(stage);
		    BorderPane pane = (BorderPane) scene.getRoot();
		    pane.setTop(menu);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
