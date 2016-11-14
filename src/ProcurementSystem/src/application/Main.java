package application;
	
import org.springframework.context.support.ClassPathXmlApplicationContext;

import application.ui.views.ViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	private ViewLoader viewLoader;
	private ClassPathXmlApplicationContext context;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			context = new ClassPathXmlApplicationContext("spring.xml");
			viewLoader = new ViewLoader(context, primaryStage);
			viewLoader.loadMenuedScreen("/application/ui/views/Welcome.fxml");
			primaryStage.setTitle("EZProcure");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		context.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}