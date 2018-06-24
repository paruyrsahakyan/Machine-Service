package group.service.iko.service;

import group.service.iko.entities.Worker;
import group.service.iko.entityDao.SessionFactoryImpl;
import group.service.iko.entityDao.WorkerDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private WorkerDAO workerDAO;
    Session session;
    private List<Worker> allWorkers =null;

    public  void deleteWorker(Worker worker){
        workerDAO.deleteWorker(worker);
    }
    public   Worker getWorkerById(int id){
        return workerDAO.getWorkerById(id);
    }
    public  void updateWorker(Worker worker){
        workerDAO.updateWorker(worker);
    }
    public  List<Worker> getAllWorkers(){
        if(allWorkers==null) {
            session = SessionFactoryImpl.getSessionFactory().openSession();
            String hql = "from group.service.iko.entities.Worker";
            Query query = session.createQuery(hql);
            List<Worker> workers = (List<Worker>) query.list();
            session.close();
            allWorkers = workers;
        }
        return allWorkers;
                    }
}
