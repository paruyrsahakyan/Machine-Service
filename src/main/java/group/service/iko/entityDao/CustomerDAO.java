package group.service.iko.entityDao;

import group.service.iko.entities.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public CustomerDAO() {

    }

    public void saveCustomer(Customer customer) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(customer);
        session.flush();
        session.close();
    }

    public void updateCustomer(Customer customer) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(customer);
        session.flush();
        session.close();
    }

    public void deleteCustomer(Customer customer) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(customer);
        session.flush();
        session.close();
    }

    public Customer getCustomerById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Customer WHERE id=" + id;
        Query query = session.createQuery(hql);
        Customer customer = (Customer) query.uniqueResult();
        session.flush();
        session.close();
        return customer;

    }

    public List<Customer> getAllCustomers() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Customer";
        Query query = session.createQuery(hql);
        List<Customer> customerList= (List<Customer>) query.list();
        session.flush();
        session.close();
        return customerList;
    }
}
