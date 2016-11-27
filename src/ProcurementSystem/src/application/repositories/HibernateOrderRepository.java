package application.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import application.models.Order;
import application.models.User;
import application.models.status.OrderStatus;

@Transactional
public class HibernateOrderRepository implements OrderRepository {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<Order> getOrdersForEmployee(User employee) {
		String hql = "FROM Order o JOIN FETCH o.item i JOIN FETCH i.category JOIN FETCH o.facility JOIN FETCH o.employee e WHERE e.id = :employeeId";
		@SuppressWarnings("unchecked")
		TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("employeeId", employee.getId());
	    return query.getResultList();
	}
	
	@Override
	public void saveOrder(Order order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
	}
	
	@Override
	public void cancelOrder(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order)session.get(Order.class, orderId);
		order.setStatus(OrderStatus.CANCELED);
		session.update(order);
	}
	
	@Override
	public List<Order> getOrderForReview(User manager) {
		String hql = "FROM Order o JOIN FETCH o.item i JOIN FETCH i.category JOIN FETCH o.facility JOIN FETCH o.employee e JOIN FETCH e.manager m WHERE m.id = :managerId AND o.status = :status";
		@SuppressWarnings("unchecked")
		TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("managerId", manager.getId());
		query.setParameter("status", OrderStatus.SUBMITTED);
	    return query.getResultList();
	}
}
