package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

@Service
public class OfferService {

    @Autowired
    private EntityDAO<Offer> entityDAO;
    @Autowired
    private StorageService storageService;
    @Autowired
    private  CustomerService customerService;
    @Autowired
    private ExcelReaderWriter excelReaderWriter;
    @Autowired
    OfferService offerService;
    @Autowired
    OfferLineService offerLineService;
    Session session;



    public OfferService() {
    }


    public Offer getOfferById(int id) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Offer WHERE id=" + id;
        Query query = session.createQuery(hql);
        Offer offer = (Offer) query.uniqueResult();
        session.flush();
        session.close();
        return offer;
    }

    public void saveOffer(Offer offer) {
        entityDAO.saveEntity(offer);

    }

    public void upDateOffer(Offer offer) {
        entityDAO.updateEntity(offer);
    }

    public void deleteOffer(Offer offer) {
        entityDAO.deleteEntity(offer);
    }

    public List<Offer> getCurrentOffers() {

        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Offer WHERE offerCondition = 'open' ";
        Query query = session.createQuery(hql);
        List<Offer> offerList = (List<Offer>) query.list();
        session.flush();
        session.close();
        return offerList;
    }

    public  Request getRequestFromFile(String customerName, MultipartFile uploadedFile) throws IOException {

        storageService.saveRequestFile(uploadedFile);
        Customer customer = customerService.getCustomerByName(customerName);
        List<RequestLine> requestLines = excelReaderWriter.getRequestFromStoredFile(customer);
        Request request = new Request();
        request.setCustomer(customer);
        request.setRequestLines(requestLines);
        return request;
    }


    public Offer makeOfferFromRequestedFile(String customerName, MultipartFile uploadedFile) throws IOException {
        Offer offer = new Offer();
        offer.setCustomer(customerService.getCustomerByName(customerName));
        Request request = getRequestFromFile(customerName, uploadedFile);
        offer.setCustomer(request.getCustomer());
        offer.setOfferLines(offerLineService.makeOfferLinesFromRequest(request));
//        offerService.saveOffer(offer);
//        Offer savedOffer = offerService.getLastSavedOffer();
        return  offer;

    }

    public Offer getLastSavedOffer() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Offer" +
                " order by id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        Offer offer = (Offer) query.uniqueResult();
        session.flush();
        session.close();
        return offer;
    }



}



