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


    public Main() {
    	QueryEmployeeDemo employeelist = new QueryEmployeeDemo();
    	employeelist.queryEmployee();
    	for (Employee tempEmployee : employeelist.getTheEmployees()){
    		personData.add(new Person(tempEmployee.getFirstName(), tempEmployee.getLastName(), tempEmployee.getId(), tempEmployee.getreviewOrder()));
    		System.out.println(personData);
    	}
    }

   public ObservableList<Person> getPersonData() {
        return personData;
   }

    public void setPersonData(ObservableList<Person> personData) {
		this.personData = personData;
	}

	//Yi's code<finished>



	@Override
	public void start(Stage primaryStage) {
		//Yi's code<start>
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ReviewOrderApp");

        //initRootLayout();
        //showPersonOverview();
        //Yi's code<finished>

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
	//Yi's code<start>
	   public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/application/ui/views/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


	    public void showPersonOverview() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/application/ui/views/PersonOverview.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            PersonOverviewController controller = loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	    //Yi's code<finished>

	@Override
	public void stop() throws Exception {
		super.stop();
		context.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}