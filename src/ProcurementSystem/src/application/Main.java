package application;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import application.models.Employee;
import application.models.Person;
import application.models.QueryEmployeeDemo;
import application.ui.controllers.ApplicationController;
import application.ui.controllers.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	private ApplicationController applicationController;
	private ClassPathXmlApplicationContext context;


	//Yi's code<start>
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();


	//Yi's code<finished>



	@Override
	public void start(Stage primaryStage) {

		try {
			context = new ClassPathXmlApplicationContext("spring.xml");
			applicationController = new ApplicationController(context, primaryStage);
			applicationController.loadScreen("/application/ui/views/Login.fxml");
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