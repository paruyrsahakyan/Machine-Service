package group.service.iko.service;

import group.service.iko.entities.MachineType;
import group.service.iko.entities.MaintenancePart;
import group.service.iko.entityDao.MaintenancePartDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MaintenancePartService {
    @Autowired
    MaintenancePartDAO maintenancePartDAO;
    Session session;

    public void saveMaintenancePart(MaintenancePart maintenancePart){
        maintenancePartDAO.saveMaintenancePart(maintenancePart);
    }
    public void updateMaintenancePart(MaintenancePart maintenancePart){
        maintenancePartDAO.updateMaintenancePart(maintenancePart);
    }

    public void deleteMaintenancePart(MaintenancePart maintenancePart){
        maintenancePartDAO.deleteMaintenancePart(maintenancePart);
    }

    public MaintenancePart getMaintenancePartById(int id){
        return  maintenancePartDAO.getMaintenancePartById(id);
    }

   }
