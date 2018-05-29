package group.service.iko.controller;

import group.service.iko.Filters.HistoryRecordFilter;
import group.service.iko.dto.HistoryRecordDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/customer/machine/recordList/")
public class RecordsController {
    @RequestMapping("/customer/machine/recordList/{machineId}")
    public ModelAndView recordListOfMachine(@PathVariable("machineId") int machineId,
                                            @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                            @RequestParam(value = "endDate", defaultValue = "") String endDate) {

        MachineService machineService = new MachineService();
        Machine machine = machineService.getMachineById(machineId);
        List<HistoryRecord> recordList = machine.getHistoryRecordList();
        List<HistoryRecord> filtered = HistoryRecordFilter.filterRecordsByDate(recordList,startDate,endDate);
        ModelAndView modelAndView = new ModelAndView("recordList");
        modelAndView.addObject("recordList", HistoryRecordDTO.transformIntoDTO(filtered));
        modelAndView.addObject("machine", new MachineDTO(machine));
        modelAndView.addObject("startDate", startDate);
        modelAndView.addObject("endDate", endDate);
        return modelAndView;
    }
}
