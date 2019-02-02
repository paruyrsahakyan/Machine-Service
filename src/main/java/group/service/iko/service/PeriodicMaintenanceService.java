package group.service.iko.service;

import group.service.iko.entities.Machine;
import group.service.iko.entities.MachineType;
import group.service.iko.entities.MaintenancePart;
import group.service.iko.entities.PeriodicMaintenance;
import group.service.iko.entityDao.MaintenancePartDAO;
import group.service.iko.entityDao.PeriodicMaintenanceDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PeriodicMaintenanceService {
    @Autowired
    PeriodicMaintenanceDAO periodicMaintenanceDAO;
    @Autowired
    MaintenancePartDAO maintenancePartDAO;
    @Autowired
    MachineTypeService machineTypeService;
    Session session;

    public void savePeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        periodicMaintenanceDAO.savePeriodicMaintenance(periodicMaintenance);
    }

    public void updatePeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        periodicMaintenanceDAO.updatePeriodicMaintenance(periodicMaintenance);
    }

    public void deletePeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        periodicMaintenanceDAO.deletePeriodicMaintenance(periodicMaintenance);
    }

    public PeriodicMaintenance getMaintenanceById(int id) {
        return periodicMaintenanceDAO.getPeriodicMaintenanceById(id);
    }

    public PeriodicMaintenance getLastSavedMaintenance() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.PeriodicMaintenance" +
                " order by id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        PeriodicMaintenance periodicMaintenance = (PeriodicMaintenance) query.uniqueResult();
        session.flush();
        session.close();
        return periodicMaintenance;

    }

    public void savePeriodicMaintenance(int machineId,
                                          String[] partNumber,
                                          int smr,
                                          String[] description,
                                          String unit[],
                                          int[] quantity

    ) {
        PeriodicMaintenance periodicMaintenance = new PeriodicMaintenance();
        periodicMaintenance.setSmr(smr);
        MachineType machineType = machineTypeService.getMachineTypeById(machineId);
        periodicMaintenance.setMachineType(machineType);
        periodicMaintenanceDAO.savePeriodicMaintenance(periodicMaintenance);
        PeriodicMaintenance savedMaintenance = getLastSavedMaintenance();
       createAndSetMaintenanceParts(savedMaintenance,partNumber,description,unit,quantity);
    }

    public void updatePeriodicMaintenance(int maintenanceId,
                                        String[] partNumber,
                                        int smr,
                                        String[] description,
                                        String unit[],
                                        int[] quantity
    ) {
       PeriodicMaintenance periodicMaintenance = getMaintenanceById(maintenanceId);
        periodicMaintenance.setSmr(smr);
         for(MaintenancePart maintenancePart : periodicMaintenance.getMaintenanceParts())
        maintenancePartDAO.deleteMaintenancePart(maintenancePart);
         periodicMaintenance.setMaintenanceParts(null);
        periodicMaintenanceDAO.updatePeriodicMaintenance(periodicMaintenance);
         PeriodicMaintenance updatedPeriodicMaintenance = getMaintenanceById(maintenanceId );
     createAndSetMaintenanceParts(updatedPeriodicMaintenance, partNumber, description, unit, quantity);
    }

    private void createAndSetMaintenanceParts(  PeriodicMaintenance periodicMaintenance,
                                                String[] partNumber,
                                                String[] description,
                                              String unit[],
                                              int[] quantity) {
        for (int i = 0; i < partNumber.length; i++) {
            MaintenancePart maintenancePart = new MaintenancePart();
            maintenancePart.setPartNumber(partNumber[i]);
            maintenancePart.setPartType(description[i]);
            maintenancePart.setUnit(unit[i]);
            maintenancePart.setQuantity(quantity[i]);
            maintenancePart.setPeriodicMaintenance(periodicMaintenance);
            maintenancePartDAO.saveMaintenancePart(maintenancePart);
        }
    }

    }


