package group.service.iko.controller;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.dto.WorkOrderDTO;
import group.service.iko.entities.*;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/workOrder")
public class WorkOrderController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MachineService machineService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private ServiceMachineService serviceMachineService;
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private HistoryRecordService historyRecordService;
    @Autowired
    private DetailedLaborHourService detailedLaborHourService;
    @Autowired
    private PeriodicMaintenanceService periodicMaintenanceService;

    @RequestMapping("/{id}")
    public ModelAndView getWorkOrder(@PathVariable("id") int id){
                ModelAndView modelAndView = new ModelAndView("workOrder/workOrder");
                modelAndView.addObject("workOrder", new WorkOrderDTO(workOrderService.getWorkOrderById(id)));
                   return modelAndView;
    }

   @RequestMapping("/new")
    public ModelAndView getWorkOrderCreationPage() {
        ModelAndView modelAndView = new ModelAndView("workOrder/createWorkOrder");
        modelAndView.addObject("machineList", MachineDTO.convertIntoDTO(machineService.getAllMachines()));
        modelAndView.addObject("workerList", workerService.getAllWorkers() );
        modelAndView.addObject("serviceMachineList", serviceMachineService.getAllServiceMachines());
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        return modelAndView;
    }

    @RequestMapping(value = "/createdWorkOrder", method = RequestMethod.POST)
    public  ModelAndView createWorkOrder(@RequestParam("customer") String customerId,
                                         @RequestParam("machineId") int machineId,
                                         @RequestParam("periodicMaintenance") int maintenanceId,
                                         @RequestParam("smr") int smr,
                                         @RequestParam("date") String date,
                                         @RequestParam("location") String location,
                                         @RequestParam("worker") String worker,
                                         @RequestParam("serviceMachine") String serviceMachine
                                          ){
        WorkOrder workOrder = new WorkOrder();
        PeriodicMaintenance maintenance = periodicMaintenanceService.getMaintenanceById(machineId);
        workOrder.setPeriodicMaintenance(maintenance);
        workOrder.setMachine( machineService.getMachineById(machineId));
        workOrder.setOrderSmr(smr);
        workOrder.setOrderDate(CalendarAdapter.getGregCalendar(date));
        workOrder.setLocation(location);
        workOrder.setWorker(worker);
        workOrder.setServiceMachine(serviceMachine);
        workOrder.setCondition("completed");
        workOrderService.saveWorkOrder(workOrder);
                ModelAndView modelAndView = new ModelAndView("workOrder/workOrder");
        modelAndView.addObject("workOrder", new WorkOrderDTO(workOrderService.getLastSavedWorkOrder()));
        return  modelAndView;
    }

    @RequestMapping("/{id}/update")
    public  ModelAndView getUpdatePage(@PathVariable("id") int id){
        WorkOrder workOrder = workOrderService.getWorkOrderById(id);
        ModelAndView modelAndView = new ModelAndView("workOrder/updateWorkOrder");
        modelAndView.addObject("workOrder", new WorkOrderDTO(workOrder));
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject("serviceMachineList", serviceMachineService.getAllServiceMachines());
        modelAndView.addObject("workerList", workerService.getAllWorkers());
        modelAndView.addObject("machineList", MachineDTO.convertIntoDTO(machineService.getAllMachines()));
        return  modelAndView;
    }

    @RequestMapping(value = "/{id}/updated", method = RequestMethod.POST)
    public  ModelAndView updateWorkOrder(@PathVariable("id") int id,
                                         @RequestParam("machineId") int machineId,
                                         @RequestParam("smr") int smr,
                                         @RequestParam("date") String date,
                                         @RequestParam("location") String location,
                                         @RequestParam("worker") String worker,
                                         @RequestParam("serviceMachine") String serviceMachine
    ){
        WorkOrder workOrder = new WorkOrder();
        workOrder.setId(id);
        workOrder.setMachine( machineService.getMachineById(machineId));
        workOrder.setOrderSmr(smr);
        workOrder.setOrderDate(CalendarAdapter.getGregCalendar(date));
        workOrder.setLocation(location);
        workOrder.setWorker(worker);
        workOrder.setServiceMachine(serviceMachine);
        workOrderService.updateWorkOrder(workOrder);
        ModelAndView modelAndView = new ModelAndView("workOrder/workOrder");
        WorkOrderDTO workOrderDTO = new WorkOrderDTO(workOrderService.getWorkOrderById(id));
        modelAndView.addObject("workOrder", workOrderDTO);
        return  modelAndView;
    }

     @RequestMapping("/{id}/deleted")
    public ModelAndView deleteWorkOrder(@PathVariable("id") int id) {
         WorkOrder workOrder = new WorkOrder();
         workOrder.setId(id);
         workOrderService.deleteWorkOrder(workOrder);
         ModelAndView modelAndView = new ModelAndView("index");
         return modelAndView;
     }
     @RequestMapping("/{id}/completeWorkOrder")
    public  ModelAndView getOrderCompletePage(@PathVariable("id") int id) {
        WorkOrder workOrder = workOrderService.getWorkOrderById(id);
        ModelAndView modelAndView = new ModelAndView("workOrder/completeWorkOrder");
        modelAndView.addObject("workOrder", new WorkOrderDTO(workOrder));
        modelAndView.addObject("workerList", workerService.getAllWorkers() );
        return  modelAndView;
     }
     @RequestMapping("/{id}/completedWorkOrder")
    public ModelAndView completeWorkOrder(@PathVariable("id") int id,
                                          @RequestParam("title") String title,
                                          @RequestParam(name = "smr", defaultValue = "0") int smr,
                                          @RequestParam("date") String date,
                                          @RequestParam(name = "laborHour", defaultValue = "0") String laborhour,
                                          @RequestParam("recordInformation") String recordInformation,
                                          @RequestParam("otherInfo") String otherInfo,
                                          @RequestParam("workerName1") String workerName1,
                                          @RequestParam(name = "manHour1", defaultValue = "0") String manHour1,
                                          @RequestParam("workerName2") String workerName2,
                                          @RequestParam(name = "manHour2", defaultValue = "0") String manHour2,
                                          @RequestParam("workerName3") String workerName3,
                                          @RequestParam(name = "manHour3", defaultValue = "0") String manHour3,
                                          @RequestParam("workerName4") String workerName4,
                                          @RequestParam(name = "manHour4", defaultValue = "0") String manHour4) {
         WorkOrder workOrder = workOrderService.getWorkOrderById(id);
         workOrderService.updateWorkOrder(workOrder);
         Machine machine = workOrder.getMachine();
         HistoryRecord historyRecord = new HistoryRecord();
         historyRecord.setMachine(machine);
         historyRecord.setTitle(title);
         historyRecord.setSMR(smr);
         historyRecord.setLaborHour(Double.parseDouble(laborhour));
         historyRecord.setRecordInformation(recordInformation);
         historyRecord.setRecordDate(CalendarAdapter.getGregCalendar(date));
         historyRecord.setOtherInfo(otherInfo);
         historyRecordService.saveHistoryRecord(historyRecord);
         HistoryRecord savedHistoryRecord = historyRecordService.getLastRecord();
         int idOfSavedRecord = savedHistoryRecord.getId();
         List<DetailedLaborHour> detailedLaborList = new ArrayList<DetailedLaborHour>();
         detailedLaborList.add(new DetailedLaborHour(workerName1, manHour1));
         detailedLaborList.add(new DetailedLaborHour(workerName2, manHour2));
         detailedLaborList.add(new DetailedLaborHour(workerName3, manHour3));
         detailedLaborList.add(new DetailedLaborHour(workerName4, manHour4));
         for (DetailedLaborHour detailedLabor : detailedLaborList) {
             if (!detailedLabor.getWorkerName().equals("")) {
                 detailedLabor.setHistoryRecord(savedHistoryRecord);
                 detailedLaborHourService.saveDetailedLaborHour(detailedLabor);
             }
         }
         HistoryRecord recordWithLaborHour = historyRecordService.getHistoryRecordById(idOfSavedRecord);
         ModelAndView modelAndView = new ModelAndView("historyRecord/historyRecord");
         modelAndView.addObject("historyRecord", recordWithLaborHour);
         String recordDate = CalendarAdapter.getStringFormat(savedHistoryRecord.getRecordDate());
         modelAndView.addObject("recordDate", recordDate);
         return modelAndView;

     }

}
