package application.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import application.models.Employee;
import application.repositories.HibernateUserRepository;
import application.ui.controllers.PersonOverviewController;
@Repository
public class QueryEmployeeDemo {
	//new code
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	//new code end

	private List<Employee> theEmployees;

	public List<Employee> getTheEmployees() {
		return theEmployees;
	}
	//new code


	/*public void queryEmployee() {
		/*SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Employee.class)
									.buildSessionFactory();*/

		/*Session session = factory.getCurrentSession();

		try{
			//session.beginTransaction();
			theEmployees = session.createQuery("from Employee e where e.status = 'none'").list();
			for (Employee tempEmployee : theEmployees){

				System.out.println(tempEmployee);
			}
			//session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			factory.close();
		}
	}*/

	//Yi'code<start>
	@SuppressWarnings("unchecked")
	public void queryEmployee() {
		/*SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Employee.class)
									.buildSessionFactory();*/

		Session session = sessionFactory.getCurrentSession();

		try{
			//session.beginTransaction();
			theEmployees = session.createQuery("from Employee e where e.status = 'none'").list();
			for (Employee tempEmployee : theEmployees){

				System.out.println(tempEmployee);
			}
			//session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			sessionFactory.close();
		}
	}



	public void Approved() {

		Session session = sessionFactory.getCurrentSession();
		try {
			String employeeId = PersonOverviewController.getCurrent_id() ;

			session = sessionFactory.getCurrentSession();
			//session.beginTransaction();

			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Approved");
			//session.getTransaction().commit();


	    	//initialize();
			//System.out.println("Done!");
		}
		finally {
			sessionFactory.close();
		}

	}


	public void Rejected() {

		Session session = sessionFactory.getCurrentSession();
		try {
			String employeeId = PersonOverviewController.getCurrent_id() ;

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Rejected");


			session.getTransaction().commit();


	    	//initialize();
			//System.out.println("Done!");
		}
		finally {
			sessionFactory.close();
		}

	}
	//Yi'code<finished>

}
