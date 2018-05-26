package test;


import group.service.iko.db.MachineHibernateDAO;
import group.service.iko.entities.*;
import group.service.iko.service.CustomerService;
import group.service.iko.service.DetailedLaborHourService;
import group.service.iko.service.HistoryRecordService;
import group.service.iko.service.MachineService;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
        DetailedLaborHourService detailedLaborHourService =  new DetailedLaborHourService();

        HistoryRecordService historyRecordService = new HistoryRecordService();
        HistoryRecord historyRecord1 = historyRecordService.getLastRecord();

        List<DetailedLaborHour> detailedLaborHourList =
                detailedLaborHourService.getDetailedLaborByRecordId(historyRecord.getId());
        System.out.println(Arrays.asList(detailedLaborHourList));

    }

}
