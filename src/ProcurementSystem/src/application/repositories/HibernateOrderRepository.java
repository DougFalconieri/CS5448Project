package application.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import application.models.Order;
import application.models.User;

@Transactional
public class HibernateOrderRepository implements OrderRepository {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<Order> getOrdersForEmployee(User employee) {
		String hql = "FROM Order o JOIN FETCH o.item JOIN FETCH o.facility JOIN FETCH o.employee e WHERE e.id = :employeeId";
		@SuppressWarnings("unchecked")
		TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("employeeId", employee.getId());
	    return query.getResultList();
	}
}
