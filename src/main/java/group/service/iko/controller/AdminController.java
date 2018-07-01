package group.service.iko.controller;
import group.service.iko.entities.Worker;
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
}
