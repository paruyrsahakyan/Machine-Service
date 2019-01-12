package group.service.iko.service;

import group.service.iko.entities.MaintenancePart;
import group.service.iko.entities.PeriodicMaintenance;
import group.service.iko.entityDao.MaintenancePartDAO;
import group.service.iko.entityDao.PeriodicMaintenanceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class PeriodicMaintenanceService {
    @Autowired
    PeriodicMaintenanceDAO periodicMaintenanceDAO;
    @Autowired
    MaintenancePartDAO maintenancePartDAO;

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

    public PeriodicMaintenance makePeriodicMaintenance(String[] partNumber,
                                                       int smr,
                                                       String[] description,
                                                       String unit[],
                                                       int[] quantity

    ) {
        PeriodicMaintenance periodicMaintenance = new PeriodicMaintenance();
        periodicMaintenance.setSmr(smr);
        Set<MaintenancePart> maintenancePartSet = new HashSet<>();
        for (int i = 0; i < partNumber.length; i++) {
            MaintenancePart maintenancePart = new MaintenancePart();
            maintenancePart.setPartNumber(partNumber[i]);
            maintenancePart.setPartType(description[i]);
            maintenancePart.setUnit(unit[i]);
            maintenancePart.setQuantity(quantity[i]);
            maintenancePartSet.add(maintenancePart);

            periodicMaintenance.setMaintenanceParts(maintenancePartSet);
            periodicMaintenance.setSmr(smr);
            periodicMaintenance.setMaintenanceParts(maintenancePartSet);
        }
        periodicMaintenance.setMaintenanceParts(maintenancePartSet);
        return periodicMaintenance;
    }
}


