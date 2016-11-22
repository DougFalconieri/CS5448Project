package application.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import application.models.Employee;

public class QueryEmployeeDemo {
	private List<Employee> theEmployees;

	public List<Employee> getTheEmployees() {
		return theEmployees;
	}

	public void queryEmployee() {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Employee.class)
									.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try{
			session.beginTransaction();
			theEmployees = session.createQuery("from Employee e where e.status = 'none'").list();
			for (Employee tempEmployee : theEmployees){

				System.out.println(tempEmployee);
			}
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			factory.close();
		}
	}
}
