package group.service.iko.service;

import group.service.iko.db.CustomerHibernateDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private SessionFactoryImpl sessionFactoryImpl;
    @Autowired
    private CustomerHibernateDAO customerHibernateDAO;
    public CustomerService() {
customerHibernateDAO = new CustomerHibernateDAO();
    }


    public Customer getCustomerByName(String name) {
        String hql = "from group.service.iko.entities.Customer where name='"+ name+"'";
        Session session = sessionFactoryImpl.getSessionFactory().openSession();
         Query query = session.createQuery(hql);
         Customer customer = (Customer) query.uniqueResult();
         session.close();
         return  customer;
    }
    public void saveCustomer(Customer customer){
        customerHibernateDAO.saveCustomer(customer);
    }
    public void updateCustomer(Customer customer){
        customerHibernateDAO.updateCustomer(customer);
     }

     public List<Customer> getAllCustomers(){
        return customerHibernateDAO.getAllCustomers();
             }
             public  Customer getCustomerById(int id) {
         return customerHibernateDAO.getCustomerById(id);

             }

    public void deleteCustomerById(int customerId) {
         Customer customer = new Customer();
         customer.setId(customerId);
         customerHibernateDAO.deleteCustomer(customer);
    }
}
