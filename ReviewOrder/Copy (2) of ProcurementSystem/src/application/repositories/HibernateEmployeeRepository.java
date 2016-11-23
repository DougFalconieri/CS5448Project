package application.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import application.models.Employee;
import application.ui.controllers.PersonOverviewController;



@Transactional
public class HibernateEmployeeRepository implements EmployeeRepository {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private List<Employee> theEmployees;


	@Override
	public List<Employee> getTheEmployees() {
		return theEmployees;
	}

	@Override

	public void queryEmployee() {
		Session session = sessionFactory.getCurrentSession();
			theEmployees = session.createQuery("from Employee e where e.status = 'none'").list();
			for (Employee tempEmployee : theEmployees){

				System.out.println(tempEmployee);
			}

			System.out.println("Done!");
	}

	@Override
	public void Approved() {
		Session session = sessionFactory.getCurrentSession();

			String employeeId = PersonOverviewController.getCurrent_id() ;

			session = sessionFactory.getCurrentSession();


			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Approved");

	}

	@Override
	public void Rejected() {
		Session session = sessionFactory.getCurrentSession();

			String employeeId = PersonOverviewController.getCurrent_id() ;

			session = sessionFactory.getCurrentSession();


			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Rejected");



	}

}
