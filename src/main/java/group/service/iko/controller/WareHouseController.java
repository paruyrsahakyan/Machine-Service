package group.service.iko.controller;

import group.service.iko.entities.InterChangeablePart;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/wareHouse")
public class WareHouseController {
    @Autowired
    StorageService storageService;
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private ExcelReaderWriter excelReaderWriter;
    @Autowired
    private WareHouseService wareHouseService;
    @Autowired
    private InterChangeablePartService interChangeablePartService;

    @RequestMapping(value = "")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("wareHouse/wareHouseHome");
        String updateDate = WareHouseService.updateDate;
        modelAndView.addObject("updateDate", updateDate);
        return modelAndView;
    }

    @RequestMapping(value = "/saved", method = RequestMethod.POST)
    public ModelAndView filesUpdated(@RequestParam(value = "wareHouseFile", required = false) MultipartFile multipartFile
    ) {
        storageService.saveWareHouseFile(multipartFile);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping(value = "/interChangeableParts/createNew")
    public ModelAndView getCreationPage() {
        ModelAndView modelAndView = new ModelAndView("wareHouse/interChangeableCreatePage");
        return modelAndView;
    }

    @RequestMapping(value = "/interChangeableParts/createdNewInterChangeable", method = RequestMethod.POST)
    public ModelAndView createNewInterChangeable(@RequestParam("basicPartNumber") String basicPartNumber,
                                                 @RequestParam("partNumber") String partNumber
    ) {
        ModelAndView modelAndView = new ModelAndView("wareHouse/interChangeableParts");
        InterChangeablePart interChangeablePart = new InterChangeablePart();
        interChangeablePart.setBasicPartNumber(basicPartNumber);
        interChangeablePart.setPartNumber(partNumber);
        interChangeablePartService.saveInterChangeablePart(interChangeablePart);
       modelAndView.addObject("interChangeableGroupList", wareHouseService.getInterChangeableGroupList());
      return modelAndView;
    }
}


