package group.service.iko.service;

import group.service.iko.entityDao.CustomerDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import group.service.iko.entities.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    private Session session;

    public CustomerService() {

    }


    public Customer getCustomerByName(String name) {
        String hql = "from group.service.iko.entities.Customer where name='"+ name+"'";
         session = SessionFactoryImpl.getSessionFactory().openSession();
         Query query = session.createQuery(hql);
         Customer customer = (Customer) query.uniqueResult();
         session.close();
         return  customer;
    }
    public void saveCustomer(Customer customer){
        customerDAO.saveCustomer(customer);
    }
    public void updateCustomer(Customer customer){
        customerDAO.updateCustomer(customer);
     }

     public List<Customer> getAllCustomers(){
        return customerDAO.getAllCustomers();
             }

             public  Customer getCustomerById(int id) {
         return customerDAO.getCustomerById(id);

             }

    public void deleteCustomerById(int customerId) {
         Customer customer = new Customer();
         customer.setId(customerId);
         customerDAO.deleteCustomer(customer);
    }

    public List<Customer> getCustomersFiltered(String customerName) {
         if(customerName.equals("")){
             return  getAllCustomers();
         }
         session = SessionFactoryImpl.getSessionFactory().openSession();
         String hql = "from group.service.iko.entities.Customer where " +
                 "name like :N";
         Query query = session.createQuery(hql);
         query.setParameter("N", "%"+ customerName +"%");
         List<Customer> customerList = (List<Customer>) query.list();
         session.close();
         return  customerList;

    }
}
