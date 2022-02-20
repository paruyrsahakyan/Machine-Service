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

    public  Set<OfferLine> makeOfferLinesFromRequest (Request request) throws Throwable {
        String customerName = request.getCustomer().getName();
        Map<String, Double> supplierPriceMap = WareHouseService.getSupplierPriceList();
        Map<String, String> interchangeabilityMap = WareHouseService.getInterchangeabilityMap();
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

            String offeredPartNumber = interchangeabilityMap.get(partNumber);
            if (offeredPartNumber != null) {
                offerLine.setOfferedPartNumber(offeredPartNumber);
            } else offeredPartNumber = partNumber;
            offerLine.setOfferedPartNumber(offeredPartNumber);
            offerLines.add(offerLine);

            PriceForCustomer priceForCustomer = priceListMap.get(offeredPartNumber);
            if (priceForCustomer != null) {
                int price = priceForCustomer.getPrice();
                offerLine.setLastOfferedPrice(price);

            }
            Double supplierPrice = supplierPriceMap.get(offeredPartNumber);
            if (supplierPrice != null) {
                offerLine.setSupplierPrice(supplierPrice);
            }


            Part availablePart = WareHouseService.availablePartList.get(offeredPartNumber);
            if(availablePart !=null){
                      offerLine.setAvailability( (int) availablePart.getQuantity());
            }
        }
        return new HashSet<OfferLine>(offerLines);
    }

}


