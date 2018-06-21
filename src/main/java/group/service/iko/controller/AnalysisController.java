package group.service.iko.controller;

import group.service.iko.dto.JobDTO;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.service.DetailedLaborHourService;
import group.service.iko.service.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    private Searcher searcher;

    @RequestMapping("/worker/jobs")
    public ModelAndView workerAnalysis(@RequestParam(value = "workerName", defaultValue = "") String workerName,
                                       @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                       @RequestParam(value = "endDate", defaultValue = "") String  endDate){
        ModelAndView modelAndView = new ModelAndView("workerJobList");
               if (!workerName.equals("")) {
              List<JobDTO> jobList = searcher.findJobsByWorker(workerName, startDate, endDate);
              modelAndView.addObject("jobList", jobList);
              modelAndView.addObject("workerName", workerName);
              modelAndView.addObject("startDate", startDate);
              modelAndView.addObject("endDate", endDate);
              modelAndView.addObject("totalTime", searcher.getTotalWorkTime());
          }
          return  modelAndView;
    }


}
