package group.service.iko.controller;
import group.service.iko.entities.ServiceMachine;
import group.service.iko.entities.Worker;
import group.service.iko.service.ServiceMachineService;
import group.service.iko.service.WorkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private ServiceMachineService serviceMachineService;



    @RequestMapping("/workers")
    public  ModelAndView showAllWorkers() {
        ModelAndView modelAndView = new ModelAndView("admin/workersPage");
        List<Worker> allWorkers = workerService.getAllWorkers();
                System.out.println(Arrays.asList(allWorkers));
              modelAndView.addObject("allWorkers", allWorkers);
                return  modelAndView;
            }

    @RequestMapping("/addWorker")
    public ModelAndView addWorker() {
        ModelAndView modelAndView  = new ModelAndView( "admin/addWorker");
    return  modelAndView;
    }


    @RequestMapping(value = "/workers/workerAdded", method = RequestMethod.POST)
    public ModelAndView addedWorker(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("admin/workersPage");
        Worker worker = new Worker();
        worker.setName(name);
        workerService.saveWorker(worker);
        List<Worker> allWorkers = workerService.getAllWorkers();
        modelAndView.addObject("allWorkers", allWorkers);
        return  modelAndView;
    }

    @RequestMapping("/workers/workerDeleted")
    public ModelAndView deleteWorker(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView("admin/workersPage");
        Worker worker = new Worker();
        worker.setId(Integer.parseInt(id));
        workerService.deleteWorker(worker);
         modelAndView.addObject("allWorkers", workerService.getAllWorkers());
         return  modelAndView;
    }
    @RequestMapping("/allServiceMachines")
    public  ModelAndView showAllServiceMachines(){
        ModelAndView modelAndView = new ModelAndView("admin/serviceMachines");
        modelAndView.addObject("allServiceMachines", serviceMachineService.getAllServiceMachines());
        return  modelAndView;
    }

    @RequestMapping("/addServiceMachine")
    public ModelAndView addServiceMachine() {
        ModelAndView modelAndView  = new ModelAndView( "admin/addServiceMachine");
        return  modelAndView;
    }

    @RequestMapping(value = "/serviceMachine/serviceMachineAdded", method = RequestMethod.POST)
    public ModelAndView ServiceMachineAdded(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("admin/ServiceMachines");
        ServiceMachine serviceMachine = new ServiceMachine();
        serviceMachine.setName(name);
        serviceMachineService.saveServiceMachine(serviceMachine);
        List<ServiceMachine> allServiceMachines = serviceMachineService.getAllServiceMachines();
        modelAndView.addObject("allServiceMachines", allServiceMachines);
        return  modelAndView;
    }
    @RequestMapping(value = "/serviceMachine/serviceMachineDeleted", method = RequestMethod.POST)
    public ModelAndView deleteServiceWorker(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("admin/serviceMachines");
        ServiceMachine serviceMachine = new ServiceMachine();
        serviceMachine.setId(id);
        serviceMachineService.deleteServiceMachine(serviceMachine);
        List<ServiceMachine> allServiceMachines = serviceMachineService.getAllServiceMachines();
        modelAndView.addObject("allServiceMachines", allServiceMachines);
        return  modelAndView;
    }
}
