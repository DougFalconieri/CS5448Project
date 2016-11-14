package application.ui.views;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import application.ui.controllers.BaseController;
import application.ui.controllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewLoader {
	private ApplicationContext context;
	private Stage stage;
	
	public ViewLoader(ApplicationContext context, Stage stage) {
		this.context = context;
		this.stage = stage;
	}
	
	public void loadMenuedScreen(String viewFilename) {
		try {
			Scene scene = loadFXMLComponent(viewFilename);
			MenuBar menu = loadFXMLComponent("/application/ui/views/MainMenu.fxml");
		    BorderPane pane = (BorderPane) scene.getRoot();
		    pane.setTop(menu);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private <T> T loadFXMLComponent(String fxmlFilename) throws IOException {
		FXMLLoader componentLoader = new FXMLLoader(getClass().getResource(fxmlFilename));
		componentLoader.setControllerFactory(new Callback<Class<?>, Object>() {
			
			@Override
			public Object call(Class<?> param) {
				return context.getBean(param.getSimpleName());
			}
		});
	    T component = componentLoader.load();
	    BaseController controller = componentLoader.getController();
	    controller.setViewLoader(this);
	    return component;
	}
}
