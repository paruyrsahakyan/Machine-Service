package group.service.iko.controller;

import group.service.iko.dto.HistoryRecordDTO;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.RecordFile;
import group.service.iko.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wareHouse")
public class WareHouseController {
    @Autowired
    StorageService storageService;

    @RequestMapping(value = "/saved",
            method = RequestMethod.POST)
    public ModelAndView filesUpdated(@RequestParam(value = "wareHouseFile", required = false) MultipartFile multipartFile
    ) {

        storageService.saveWareHouseFile(multipartFile);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;

    }


}
