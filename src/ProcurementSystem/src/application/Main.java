package application;
	
import application.ui.views.ViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	private ViewLoader viewLoader = new ViewLoader();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			viewLoader.loadMenuedScreen("/application/ui/views/Welcome.fxml", primaryStage);
			primaryStage.setTitle("EZProcure");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
