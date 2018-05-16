package test;


import group.service.iko.db.CustomerHibernateDAO;
import group.service.iko.db.HistoryRecordHibernateDAO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.entities.RecordFile;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class Test {
    public static void main(String[] args) throws SQLException {

        Customer customer = new Customer();
        customer.setName("aaa");
        customer.setContacts("077 9345678 Artak");
        customer.setOtherInfo("какая то информация");
        Machine newMachine = new Machine();
        newMachine.setModel("D275-5A");
        newMachine.setSerialNumber("112");
        newMachine.setOtherInfo("some info");
        newMachine.setCustomer(customer);
        Customer customer1= new Customer();
        customer1.setName("aaa");
        newMachine.setCustomer(customer1);
        newMachine.setId(1);
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setRecordDate(new Date(2018-1900, 05-1, 25));
        historyRecord.setTitle("Engine oil change");
        historyRecord.setSMR(1232);
        historyRecord.setMachine(newMachine);
        historyRecord.setId(2);

        RecordFile recordFile = new RecordFile();
        recordFile.setFilename("1imapge.jpg");
        recordFile.setHistoryRecord(historyRecord);
//
//        MachineHibernateDAO machineHibernateDAO = new MachineHibernateDAO();
//        machineHibernateDAO.addMachine(newMachine);
//        machineHibernateDAO.updateMachine(newMachine);
//        machineHibernateDAO.addMachine(newMachine);
//        List<Machine> list= machineHibernateDAO.getMachinesByCustomer("Vahagn & Samvel7");
//        machineHibernateDAO.deleteMachine(newMachine);
//        machineHibernateDAO.updateMachine(newMachine);
////
        CustomerHibernateDAO customerHibernateDAO = new CustomerHibernateDAO();
        customerHibernateDAO.saveCustomer(customer);
//        List<Customer> list = customerHibernateDAO.getAllCustomers();
//        System.out.println(list);
//              System.out.println(customerHibernateDAO.getCustomer("GTS"));
//////
//        HistoryRecordHibernateDAO historyRecordHibernateDAO = new HistoryRecordHibernateDAO();
//        HistoryRecord historyRecord1 = historyRecordHibernateDAO.getRecord(2);
//        List<RecordFile> recordFiles = historyRecord1.getRecordFiles();
//        for(RecordFile recordFile1: recordFiles){
//            System.out.println(recordFile);
//        }

////
//        RecordFileHibernateDAO recordFileHibernateDAO = new RecordFileHibernateDAO();
//        recordFileHibernateDAO.saveRecordFile(recordFile);
//        RecordFile recordFile1 = recordFileHibernateDAO.getRecorFilById(1);
//        HistoryRecord historyRecord1 = recordFile1.getHistoryRecord();
//        System.out.println("||||");
//        System.out.println("||||");
//        System.out.println("||||");
//        System.out.println("||||");
////        System.out.println(historyRecord);
//        for(RecordFile recordFile2: historyRecord.getRecordFiles()){
//            System.out.println(recordFile2);


        


           

    }
}
