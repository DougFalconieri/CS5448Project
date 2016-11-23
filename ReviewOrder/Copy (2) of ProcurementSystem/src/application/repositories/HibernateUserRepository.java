package application.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import application.models.Employee;
import application.models.User;
import application.ui.controllers.PersonOverviewController;

@Transactional
public class HibernateUserRepository implements UserRepository {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


	@Override
	public User findUser(String email, String password) {
		String hql = "FROM User u JOIN FETCH u.roles WHERE u.email = :email AND u.password = :password";
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("password", password);
	    List<User> results = query.getResultList();
	    if (results.size() == 0) return null;
	    return results.get(0);
	}

	/*//Yi'code
	private List<Employee> theEmployees;

	public List<Employee> getTheEmployees() {
		return theEmployees;
	}

	public void queryEmployee() {

		Session session = sessionFactory.getCurrentSession();

		try{
			theEmployees = session.createQuery("from Employee e where e.status = 'none'").list();
			for (Employee tempEmployee : theEmployees){

				System.out.println(tempEmployee);
			}

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


			System.out.println("\nGetting employee with id: " + employeeId);

			Employee myEmployee = session.get(Employee.class, Integer.valueOf(employeeId));

			System.out.println("Updating employee...");
			myEmployee.setStatus("Approved");
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
		}
		finally {
			sessionFactory.close();
		}

	}*/

}
