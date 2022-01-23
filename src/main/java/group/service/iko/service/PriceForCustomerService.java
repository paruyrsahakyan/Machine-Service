package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.EntityDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.ui.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceForCustomerService {
    @Autowired
    private EntityDAO<PriceForCustomer> entityDAO;
    @Autowired
    private CustomerService customerService;
    Session session;

    public PriceForCustomerService() {
    }

    public void savePriceForCustomer(PriceForCustomer priceForCustomer) {
        String articleToSave = priceForCustomer.getArticle();
        Customer customer = priceForCustomer.getCustomer();
        Set<PriceForCustomer> priceForCustomerList = customer.getPriceForCustomerList();
        for (PriceForCustomer priceForCustomer1 : priceForCustomerList) {
            if (priceForCustomer1.getArticle().equals(articleToSave)) {
                priceForCustomer1.setPrice(priceForCustomer.getPrice());
                updatePriceForCustomer(priceForCustomer1);
                return;
            }
        }

        entityDAO.saveEntity(priceForCustomer);
    }

    public void updatePriceForCustomer(PriceForCustomer priceForCustomer) {
        entityDAO.updateEntity(priceForCustomer);
    }

    public void deletePriceForCustomer(PriceForCustomer priceForCustomer) {

        entityDAO.deleteEntity(priceForCustomer);

    }

    public PriceForCustomer getPriceForCustomerById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.PriceForCustomer WHERE id=" + id;
        Query query = session.createQuery(hql);
        PriceForCustomer t = (PriceForCustomer) query.uniqueResult();
        session.flush();
        session.close();
        return t;
    }

    public List<PriceForCustomer> getAllPriceForCustomer() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.PriceForCustomer";
        Query query = session.createQuery(hql);
        List<PriceForCustomer> EntityList = (List<PriceForCustomer>) query.list();
        session.flush();
        session.close();
        return EntityList;
    }

    public List<PriceForCustomer> getPriceForCustomerByArticle(String article) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.PriceForCustomer where article= :article";
        Query query = session.createQuery(hql);
        query.setParameter("article", article);
        List<PriceForCustomer> pricesByArticleForAllCustomers = (List<PriceForCustomer>) query.list();
        session.flush();
        session.close();
        return pricesByArticleForAllCustomers.stream().sorted(Comparator.comparingInt(PriceForCustomer::getPrice)).collect(Collectors.toList());

    }

    public List<String> getArticlesSet() {

        List<String> articleList = new ArrayList<>();
        List<PriceForCustomer> listOfPriceForCustomer = getAllPriceForCustomer();
        for (PriceForCustomer priceForCustomer : listOfPriceForCustomer) {
            articleList.add(priceForCustomer.getArticle());
        }
        return new HashSet<>(articleList).stream().sorted().collect(Collectors.toList());
    }

    public void setPriceListForCustomerFromFile(String customerName, MultipartFile uploadedFile) throws IOException {
        new StorageService().savePriceListFile(uploadedFile);
        Customer customer = customerService.getCustomerByName(customerName);
        Set<PriceForCustomer> set = new ExcelReaderWriter().getPriceListFromTheFile();
        set.forEach(priceForCustomer -> priceForCustomer.setCustomer(customer));
        set.forEach(this::savePriceForCustomer);


    }

}

