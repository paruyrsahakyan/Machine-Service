package test;


import group.service.iko.Filters.HistoryRecordFilter;
import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.service.DetailedLaborHourService;
import group.service.iko.service.HistoryRecordService;
import group.service.iko.service.MachineService;
import group.service.iko.service.StorageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {

        MachineService machineService = new MachineService();
         Machine machine = machineService.getMachineById(20);
                 List<HistoryRecord> historyRecordList = machine.getHistoryRecordList();
        System.out.println(Arrays.asList(historyRecordList));
        System.out.println("-----------------------------------------------------------");
       List<HistoryRecord> filtered = HistoryRecordFilter.filterRecordsByDate(historyRecordList, "2018-04-10","2018-04-02");
        System.out.println(Arrays.asList(filtered));

        }
          }


