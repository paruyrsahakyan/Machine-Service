package group.service.iko.service;

import group.service.iko.entities.Worker;
import group.service.iko.entityDao.SessionFactoryImpl;
import group.service.iko.entityDao.WorkerDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerDAO workerDAO;
    Session session;
    static private List<Worker> allWorkers =null;

    public WorkerService(){

    }

    public  void deleteWorker(Worker worker){
        workerDAO.deleteWorker(worker);
        allWorkers = null;

    }

    public   Worker getWorkerById(int id){
        return workerDAO.getWorkerById(id);
    }

    public  void updateWorker(Worker worker){
        workerDAO.updateWorker(worker);
        allWorkers = null;
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

    public void saveWorker(Worker worker) {
        workerDAO.saveWorker(worker);
        allWorkers = null;
    }
}
