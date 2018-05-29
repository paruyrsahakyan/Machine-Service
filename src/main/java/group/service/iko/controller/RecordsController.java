package group.service.iko.controller;

import group.service.iko.dto.HistoryRecordDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.service.HistoryRecordService;
import group.service.iko.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/customer/machine/recordList/{machineId}")
public class RecordsController {

    public ModelAndView recordListOfMachine(@PathVariable("machineId") int machineId){
        MachineService machineService = new MachineService();
       Machine machine = machineService.getMachineById(machineId);
        List<HistoryRecord> recordList = machine.getHistoryRecordList();
        ModelAndView modelAndView = new ModelAndView("historyRecordList");
        modelAndView.addObject("recordList", HistoryRecordDTO.transformIntoDTO(recordList));
        modelAndView.addObject("machine", new MachineDTO(machine));
        return  modelAndView;
    }
}
