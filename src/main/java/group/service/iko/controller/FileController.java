package group.service.iko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/addfiles")
public class FileController {

    @RequestMapping("/addFiles/{historyRecordId}")
    public ModelAndView addFiles(@PathVariable("historyRecordId") int historyRecordId){
        ModelAndView modelAndView  = new ModelAndView("addFiles");
        modelAndView.addObject("historyRecordId", historyRecordId);
        return modelAndView;

    }
}
