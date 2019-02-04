package group.service.iko.controller;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.ServiceMachine;
import group.service.iko.entities.WorkOrder;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    public ModelAndView initialPage(){
        ModelAndView modelAndView = new ModelAndView("workOrder");
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
    public  ModelAndView createWorkOrder(@RequestParam("customer") int customerId,
                                         @RequestParam("machineId") int machineId,
                                         @RequestParam("smr") int smr,
                                         @RequestParam("date") String date,
                                         @RequestParam("location") String location,
                                         @RequestParam("worker") String worker,
                                         @RequestParam("serviceMachine") String serviceMachine
                                          ){
        WorkOrder workOrder = new WorkOrder();
        workOrder.setMachine( machineService.getMachineById(machineId));
        workOrder.setOrderSmr(smr);
        workOrder.setOrderDate(CalendarAdapter.getGregCalendar(date));
        workOrder.setLocation(location);
        workOrder.setWorker(worker);
        workOrder.setWorker(serviceMachine);
        workOrderService.saveWorkOrder(workOrder);
        ModelAndView modelAndView = new ModelAndView("workOrder/workOrder");
        modelAndView.addObject("workOrder", workOrderService.getLastSavedWorkOrder());
        return  modelAndView;
    }

     @RequestMapping("/machineType/Creation")
    public ModelAndView machineTypeCreation() {
        ModelAndView modelAndView = new ModelAndView("machineType/machineTypeCreation");
           return modelAndView;
    }

}
