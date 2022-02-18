package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;


@Service
public class OfferLineService {

    private Session session;
    @Autowired
    private EntityDAO<OfferLine> entityDAO;
    @Autowired
    PriceForCustomerService priceForCustomerService;
    @Autowired
    OfferService offerService;

    public OfferLineService() {
    }

    public OfferLine getOfferLineById(int id) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.OfferLine WHERE id=" + id;
        Query query = session.createQuery(hql);
        OfferLine offerLine = (OfferLine) query.uniqueResult();
        session.flush();
        session.close();
        return offerLine;

    }

    public void saveOfferLine(OfferLine offerLine) {
        entityDAO.saveEntity(offerLine);
    }

    public void updateOfferLine(OfferLine offerLine) {
        entityDAO.updateEntity(offerLine);
    }

    public void deleteOfferLine(OfferLine offerLine) {

        entityDAO.deleteEntity(offerLine);

    }

    public  Set<OfferLine> getOfferLinesFromRequest (Request request) {
        String customerName = request.getCustomer().getName();
        List<PriceForCustomer> priceListForCustomer = priceForCustomerService.getPriceListByCustomerName(customerName);
        Map<String, PriceForCustomer> priceListMap = priceListForCustomer.stream()
                .collect(Collectors.toMap(PriceForCustomer::getArticle, Function.identity()));
        List<OfferLine> offerLines = new ArrayList<>();
        for (RequestLine requestLine : request.getRequestLines()) {

            String partNumber = requestLine.getPartNumber();
            OfferLine offerLine = new OfferLine();
            offerLine.setRequestedPartNumber(partNumber);
            offerLine.setRequestedPartName(requestLine.getPartName());
            int quantity = requestLine.getQuantity();
            offerLine.setQuantity(quantity);

            PriceForCustomer priceForCustomer = priceListMap.get(partNumber);
            if (priceForCustomer != null) {
                int price = priceForCustomer.getPrice();
                offerLine.setPrice(price);
                offerLine.setSum(price * quantity);
            }
            offerLines.add(offerLine);

        }
        return new HashSet<OfferLine>(offerLines);
    }

}


