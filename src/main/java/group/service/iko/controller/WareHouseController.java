package group.service.iko.controller;

import group.service.iko.dto.HistoryRecordDTO;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.RecordFile;
import group.service.iko.entities.WorkOrder;
import group.service.iko.service.ExcelReaderWriter;
import group.service.iko.service.StorageService;
import group.service.iko.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
@RequestMapping("/wareHouse")
public class WareHouseController {
    @Autowired
    StorageService storageService;
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private ExcelReaderWriter excelReaderWriter;

    @RequestMapping(value = "/saved",
            method = RequestMethod.POST)
    public ModelAndView filesUpdated(@RequestParam(value = "wareHouseFile", required = false) MultipartFile multipartFile
    ) {

        storageService.saveWareHouseFile(multipartFile);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;

    }
    @RequestMapping(value = "/{id}/wareHouseRequest", method = RequestMethod.GET)
    public void downloadWareHouseRequest(HttpServletResponse response,
                             @PathVariable("id") int workOrderId) throws IOException {
        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
        File file = excelReaderWriter.getWareHouseRequest(workOrder);
        String mimeType;
//      mimeType = URLConnection.guessContentTypeFromName(recordFile.getFileName());
        mimeType = "application/vnd.ms-excel";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"Ware house request.xlsx\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
