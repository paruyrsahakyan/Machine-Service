package group.service.iko.entityDao;


import group.service.iko.entities.Worker;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class WorkerDAO {
    private Session session;


    public void saveWorker(Worker worker) {
        session =SessionFactoryImpl.getSessionFactory().openSession();
        session.save(worker);
        session.flush();
        session.close();
            }
    public  Worker getWorkerById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Worker where id = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("ID", id);
        Worker worker = (Worker) query.uniqueResult();
        session.close();
        return worker;
    }
    public void updateWorker(Worker worker){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(worker);
        session.flush();
        session.close();
    }
    public  void  deleteWorker(Worker worker){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(worker);
        session.flush();
        session.close();

    }

}
