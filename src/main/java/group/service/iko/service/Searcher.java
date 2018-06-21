package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.JobDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Searcher {
    @Autowired
    private DetailedLaborHourService detailedLaborHourService;

    private double totalWorkTime;


    public List<JobDTO> findJobsByWorker(String worker, String startDate, String endDate) {
        List<DetailedLaborHour> jobs = detailedLaborHourService.getJobListByWorkerName(worker, startDate, endDate);
        totalWorkTime = 0;
        List<JobDTO> jobDTOList = new ArrayList<JobDTO>();
        JobDTO current = null;
        for (DetailedLaborHour detailedLaborHour : jobs) {
            HistoryRecord historyRecord = detailedLaborHour.getHistoryRecord();
            Machine machine = null;
            machine = historyRecord.getMachine();
            Customer customer = null;
            if (machine != null) {
                customer = machine.getCustomer();
            }
            current = new JobDTO();
            current.setId(detailedLaborHour.getId());
            current.setJobDuration(detailedLaborHour.getJobDuration());
            current.setWorkerName(detailedLaborHour.getWorkerName());
            current.setHistoryRecordId(historyRecord.getId());
            current.setHistoryRecordTitle(historyRecord.getTitle());
            current.setDate(CalendarAdapter.getStringFormat(historyRecord.getRecordDate()));
            totalWorkTime+=detailedLaborHour.getJobDuration();
            if (machine != null) {
                current.setMachineId(machine.getId());
                current.setMachine(machine.getModel() + " sn" + machine.getSerialNumber());
                if (customer != null) {
                    current.setCustomerId(customer.getId());
                    current.setCustomer(customer.getName());
                }
            }
            jobDTOList.add(current);
        }
        return jobDTOList;
    }

    public double getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(double totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
    }
}
