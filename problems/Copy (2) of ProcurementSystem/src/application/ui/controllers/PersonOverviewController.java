package application.ui.controllers;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import application.Main;
import application.models.Employee;
import application.models.Person;
import application.models.QueryEmployeeDemo;
import application.repositories.HibernateUserRepository;
import application.ui.controllers.PersonOverviewController;
import application.ui.controllers.PersonOverviewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.Main;
import application.models.Person;
@Controller
public class PersonOverviewController extends BaseController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> idColumn;
    @FXML
    private TableColumn<Person, String> FirstNameColumn;


    @FXML
    private Label idLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label reviewOrderLabel;


    private static String current_id;
    private PersonOverviewController main;
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    public PersonOverviewController() {
    }
    @FXML
    public void addPerson() {
    	personData.removeAll(personData);
    	QueryEmployeeDemo employeelist = new QueryEmployeeDemo();
    	employeelist.queryEmployee();
    	for (Employee tempEmployee : employeelist.getTheEmployees()){
    		personData.add(new Person(tempEmployee.getFirstName(), tempEmployee.getLastName(), tempEmployee.getId(), tempEmployee.getreviewOrder()));
    	}
    	idColumn.setCellValueFactory(
                cellData -> cellData.getValue().idProperty());
        FirstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
    }


    @FXML
    private void initialize() {
    	addPerson();
        showPersonDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public void setPersonData(ObservableList<Person> personData) {
		this.personData = personData;
	}


    /*public void setMain(Main main) {
        this.main = main;

        personTable.setItems(getPersonData());
    }*/

    public void setMain(PersonOverviewController personOverviewController) {
        this.main = personOverviewController;

        personTable.setItems(getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            idLabel.setText(person.getid());
            reviewOrderLabel.setText(person.getReviewOrder());

            current_id = person.getid();
            System.out.println(person.getid());

        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            idLabel.setText("");
            reviewOrderLabel.setText("");
        }
    }

	public static String getCurrent_id() {
		return current_id;
	}

	public void setCurrent_id(String current_id) {
		this.current_id = current_id;
	}

	//new code
	@FXML
	private void Approved() {
		QueryEmployeeDemo qe = null;
		qe.Approved();
		initialize();
		System.out.println("Done!");
	}

	@FXML
	private void Rejected() {
		QueryEmployeeDemo qe = null;
		qe.Rejected();
		initialize();
		System.out.println("Done!");
	}
	/*@Repository
    private void Approved() {

		/*SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();*/

		/*private SessionFactory sessionFactory;

		public void setSessionFactory(SessionFactory sf){
			this.sessionFactory = sf;
		}

		Session session = factory.getCurrentSession();

		try {
			String employeeId = PersonOverviewController.getCurrent_id() ;

			session = factory.getCurrentSession();
			//session.beginTransaction();

			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Approved");
			//session.getTransaction().commit();


	    	initialize();
			System.out.println("Done!");
		}
		finally {
		factory.close();
		}

	}*/

	/*@FXML
    private void Rejected() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			String employeeId = PersonOverviewController.getCurrent_id() ;

			session = factory.getCurrentSession();
			session.beginTransaction();

			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Rejected");


			session.getTransaction().commit();

			initialize();
			System.out.println("Done!");
		}
		finally {
		factory.close();
		}
	}*/



}