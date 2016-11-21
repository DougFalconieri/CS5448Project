package application.ui.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import application.Main;
import application.models.Employee;
import application.models.Person;
import application.models.QueryEmployeeDemo;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.Main;
import application.models.Person;

public class PersonOverviewController {
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

    // Reference to the main application.
    private Main main;

    public PersonOverviewController() {
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        //firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        //lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        idColumn.setCellValueFactory(
                cellData -> cellData.getValue().idProperty());
        FirstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        personTable.setItems(main.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            idLabel.setText(person.getid());
            reviewOrderLabel.setText(person.getReviewOrder());

            current_id = person.getid();
            System.out.println(person.getid());

            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
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


	@FXML
    private void Approved() {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			String employeeId = PersonOverviewController.getCurrent_id() ;

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Approved");

			//new code
			setMain(main);

			initialize();
			//
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
		factory.close();
		}

	}

	@FXML
    private void Rejected() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			String employeeId = PersonOverviewController.getCurrent_id() ;

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Rejected");

			// commit the transaction
			session.getTransaction().commit();


			System.out.println("Done!");
		}
		finally {
		factory.close();
		}

	}


}