package group.service.iko.controller;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.dto.WorkOrderDTO;
import group.service.iko.entities.ServiceMachine;
import group.service.iko.entities.WorkOrder;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        workOrder.setServiceMachine(serviceMachine);
        workOrderService.saveWorkOrder(workOrder);
        ModelAndView modelAndView = new ModelAndView("workOrder/workOrder");
        modelAndView.addObject("workOrder", new WorkOrderDTO(workOrderService.getLastSavedWorkOrder()));
        return  modelAndView;
    }

     @RequestMapping("/workOrder/{id}/deleted")
    public ModelAndView deleteWorkOrder(@PathVariable("id") int id){
        WorkOrder workOrder = new WorkOrder();
        workOrder.setId(id);
        workOrderService.deleteWorkOrder(workOrder);
         ModelAndView modelAndView = new ModelAndView("index");
          return modelAndView;
              }

     @RequestMapping("/workOrder/{id}/update")
    public  ModelAndView getUpdatePage(@PathVariable("id") int id){
        WorkOrder workOrder = workOrderService.getWorkOrderById(id);
        ModelAndView modelAndView = new ModelAndView("workOrder/updateWorkOrder");
        modelAndView.addObject("workOrder", new WorkOrderDTO(workOrder));
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject("serviceMachineList", serviceMachineService.getAllServiceMachines());
        return  modelAndView;
             }



}
