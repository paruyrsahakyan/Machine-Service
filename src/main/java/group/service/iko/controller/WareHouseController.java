package group.service.iko.controller;

import group.service.iko.entities.InterChangeablePart;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/updated", method = RequestMethod.POST)
    public ModelAndView filesUpdated(@RequestParam(value = "wareHouseFile", required = false) MultipartFile multipartFile
    ) {
        wareHouseService.updateWareHouse(multipartFile);
        ModelAndView modelAndView = new ModelAndView("wareHouse/wareHouseHome");
        modelAndView.addObject("updatedDate", WareHouseService.getUpdateDate());
        return modelAndView;
    }
    @RequestMapping(value = "/interChangeableParts")
    public ModelAndView getAllInterChangeableParts() {
        ModelAndView modelAndView = new ModelAndView("wareHouse/interChangeableParts");
        modelAndView.addObject("interChangeableGroupList", wareHouseService.getInterChangeableGroupList());
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

    @RequestMapping(value = "/interChangeableGroup/{basicPartNumber}" )
    public ModelAndView getInterchangeableGroup(@PathVariable("basicPartNumber") String basicPartNumber){
        ModelAndView modelAndView= new ModelAndView("wareHouse/interChangeableGroup");
        modelAndView.addObject("interChangeablePartList", interChangeablePartService.getInterChangeableGroupParts(basicPartNumber));
        modelAndView.addObject("basicPartNumber", basicPartNumber );
        return  modelAndView;
    }

    @RequestMapping (value = "/interChangeablePart/{id}/deleted")
    public ModelAndView deleteInterChangeablePart (@PathVariable("id") int id){
        InterChangeablePart interChangeablePart = interChangeablePartService.getInterChangeablePartById(id);
        String basicPart = interChangeablePart.getBasicPartNumber();
         interChangeablePartService.deleteInterchangeablePart(interChangeablePart);
          ModelAndView modelAndView = new ModelAndView("wareHouse/interChangeableGroup");
          modelAndView.addObject("interChangeable", basicPart);
          modelAndView.addObject("interChangeablePartList", interChangeablePartService.getInterChangeableGroupParts(basicPart));
          return  modelAndView;
            }
    @RequestMapping (value = "/interChangeableGroup/{basicPartNumber}/deleted")
    public ModelAndView deleteInterChangeableGroup(@PathVariable("basicPartNumber") String basicPartNumber){
        interChangeablePartService.deleteInterchangeableGroup(basicPartNumber);
        ModelAndView modelAndView = new ModelAndView("wareHouse/interChangeableParts");
        modelAndView.addObject("interChangeablePartList", interChangeablePartService.getInterChangeableGroupParts(basicPartNumber));
        modelAndView.addObject("basicPartNumber", basicPartNumber);
        return  modelAndView;
    }
}


