package group.service.iko.controller;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.service.*;
import group.service.iko.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller("/")
public class MainController {
    @Autowired
    private CustomerService customerService;

    private MachineService machineService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;

    }

    @RequestMapping("/allCustomers")
    public ModelAndView allCustomers() {
        ModelAndView modelAndView = new ModelAndView("allCustomers");
        List<Customer> customers = customerService.getAllCustomers();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @RequestMapping("/customer/{customerId}")
    public ModelAndView customerPage(@PathVariable("customerId") int id) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @RequestMapping("/createCustomer")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("createCustomer");
        return modelAndView;

    }

    @RequestMapping("/newCustomer")
    public ModelAndView newCustomerPage(@RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("otherInfo") String otherInfo) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContacts(contacts);
        customer.setOtherInfo(otherInfo);
        customerService.saveCustomer(customer);
        Customer createdCustomer = customerService.getCustomerByName(name);
        modelAndView.addObject("customer", createdCustomer);
        return modelAndView;
    }

    @RequestMapping("/updateCustomer/{customerId}")
    public ModelAndView updateCustomer(@PathVariable("customerId") int id) {
        ModelAndView modelAndView = new ModelAndView("updateCustomer");
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject(customer);
        return modelAndView;
    }

    @RequestMapping("/updatedCustomer/{customerId}")
    public ModelAndView updatedCustomer(@RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("otherInfo") String otherInfo,
                                        @PathVariable("customerId") int customerId) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContacts(contacts);
        customer.setOtherInfo(otherInfo);
        customer.setId(customerId);
        customerService.updateCustomer(customer);
        Customer updatedCustomer = customerService.getCustomerById(customerId);
        modelAndView.addObject("customer", updatedCustomer);
        return modelAndView;
    }

    @RequestMapping("/allMachines")
    public ModelAndView allMachines() {
        ModelAndView modelAndView = new ModelAndView("allMachines");
        machineService = new MachineService();
        List<Machine> machineList = machineService.getAllMachines();
        modelAndView.addObject("machineList", machineList);
        return modelAndView;
    }

    @RequestMapping("/machine/{machine.Id}")
    public ModelAndView machine(@PathVariable("machine.Id") int machineId) {
        ModelAndView modelAndView = new ModelAndView("machine");
        machineService = new MachineService();
        Machine machine = machineService.getMachineById(machineId);
        modelAndView.addObject("machine", machine);
        return modelAndView;
    }

    @RequestMapping("newMachine/{customerName}")
    public ModelAndView newMachine(@RequestParam("model") String model,
                                   @RequestParam("serialNumber") String serialNumber,
                                   @RequestParam("engineModel") String engineModel,
                                   @RequestParam("engineSerialNumber") String engineSerialNumber,
                                   @RequestParam(name = "productionYear", defaultValue = "0") int productonYear,
                                   @PathVariable("customerName") String customerName,
                                   @RequestParam("otherInfo") String otherInfo
    ) {
        ModelAndView modelAndView = new ModelAndView("machine");
        Machine machine = new Machine();
        machine.setModel(model);
        machine.setSerialNumber(serialNumber);
        machine.setEngineModel(engineModel);
        machine.setEngineSerialNumber(engineSerialNumber);
        machine.setProductionYear(productonYear);
        machine.setOtherInfo(otherInfo);

        machine.setCustomer(new CustomerService().getCustomerByName(customerName));
        MachineService machineService = new MachineService();
        machineService.saveMachine(machine);
        modelAndView.addObject("machine",
                machineService.getMachineByModelAndSerialNumber(model, serialNumber));
        return modelAndView;
    }

    @RequestMapping("/createMachine/{customerId}")
    public ModelAndView createMachine(@PathVariable("customerId") int customerId) {
        ModelAndView modelAndView = new ModelAndView("createMachine");
        CustomerService customerService = new CustomerService();
        String customerName = customerService.getCustomerById(customerId).getName();
        modelAndView.addObject("customerName", customerName);
        return modelAndView;
    }

    @RequestMapping("/updateMachine/{machineId}")
    public ModelAndView updateMachine(@PathVariable("machineId") int id) {
        ModelAndView modelAndView = new ModelAndView("updateMachine");
        MachineService machineService = new MachineService();
        Machine machine = machineService.getMachineById(id);
        String customerName = machine.getCustomer().getName();
        modelAndView.addObject(machine);
        modelAndView.addObject(customerName);
        return modelAndView;
    }

    @RequestMapping("/updatedMachine/{machineId}")
    public ModelAndView updatedMachine(@RequestParam("model") String model,
                                       @RequestParam("serialNumber") String serialNumber,
                                       @RequestParam("engineModel") String engineModel,
                                       @RequestParam("engineSerialNumber") String engineSerialNumber,
                                       @RequestParam(name = "productionYear", defaultValue = "0") int productonYear,
                                       @PathVariable("machineId") int machineId,
                                       @RequestParam("otherInfo") String otherInfo,
                                       @RequestParam("customerName") String customerName
    ) {
        ModelAndView modelAndView = new ModelAndView("machine");
        Machine machine = new Machine();
        machine.setId(machineId);
        machine.setModel(model);
        machine.setSerialNumber(serialNumber);
        machine.setEngineModel(engineModel);
        machine.setEngineSerialNumber(engineSerialNumber);
        machine.setOtherInfo(otherInfo);
        machine.setProductionYear(productonYear);
        MachineService machineService = new MachineService();
        Customer customer = new CustomerService().getCustomerByName(customerName);
        machine.setCustomer(customer);
        machineService.updateMachine(machine);
        modelAndView.addObject("machine", machineService.getMachineById(machineId));
        modelAndView.addObject("customerName", customer.getName());
        return modelAndView;

    }

    @RequestMapping("/createHistoryRecord/{machineId}")
    public ModelAndView createHistoryRecord(@PathVariable("machineId") int machineId) {
        MachineService machineService = new MachineService();
        Machine machine = machineService.getMachineById(machineId);
        String model = machine.getModel();
        String serialNumber = machine.getSerialNumber();

        ModelAndView modelAndView = new ModelAndView("createHistoryRecord");
        modelAndView.addObject("model", model);
        modelAndView.addObject("serialNumber", serialNumber);
        System.out.println(model + ":" + serialNumber);
        return modelAndView;
    }

    @RequestMapping("/newHistoryRecord/{model}/{serialNumber}")
    public ModelAndView newHistoryRecord(@PathVariable("model") String model,
                                         @PathVariable("serialNumber") String serialNumber,
                                         @RequestParam("title") String title,
                                         @RequestParam(name = "SMR", defaultValue = "0") int SMR,

                                         @RequestParam("date") String date,
                                         @RequestParam(name = "laborHour", defaultValue = "0") int laborhour,
                                         @RequestParam("workerName1") String workerName1,
                                         @RequestParam(name = "manHour1", defaultValue = "0") int manHour1,
                                         @RequestParam("workerName2") String workerName2,
                                         @RequestParam(name = "manHour2", defaultValue = "0") int manHour2,
                                         @RequestParam("workerName3") String workerName3,
                                         @RequestParam(name = "manHour3", defaultValue = "0") int manHour3,
                                         @RequestParam("workerName4") String workerName4,
                                         @RequestParam(name = "manHour4", defaultValue = "0") int manHour4

    ) {
        ModelAndView modelAndView = new ModelAndView("historyRecord");
        MachineService machineService = new MachineService();
        Machine machine = machineService.getMachineByModelAndSerialNumber(model, serialNumber);
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setMachine(machine);
        historyRecord.setTitle(title);
        historyRecord.setSMR(SMR);
        historyRecord.setLaborHour(laborhour);
        historyRecord.setRecordDate(CalendarAdapter.getGregCalendar(date));
        HistoryRecordService historyRecordService = new HistoryRecordService();
        historyRecordService.saveHistoryRecord(historyRecord);
        HistoryRecord savedHistoryRecord = historyRecordService.getLastRecord();
        List<DetailedLaborHour> detailedLaborList = new ArrayList<DetailedLaborHour>();
        detailedLaborList.add(new DetailedLaborHour(workerName1, manHour1));
        detailedLaborList.add(new DetailedLaborHour(workerName2, manHour2));
        detailedLaborList.add(new DetailedLaborHour(workerName3, manHour3));
        detailedLaborList.add(new DetailedLaborHour(workerName4, manHour4));
                DetailedLaborHourService detailedLaborHourService = new DetailedLaborHourService();

        for (DetailedLaborHour detailedLabor : detailedLaborList) {
            if (!detailedLabor.getWorkerName().equals("")) {
                detailedLabor.setHistoryRecord(savedHistoryRecord);
                detailedLaborHourService.saveDetailedLaborHour(detailedLabor);
        }
        }
        List<DetailedLaborHour> savedDetailedLaborList =
                detailedLaborHourService.getDetailedLaborByRecordId(savedHistoryRecord.getId());
System.out.println(savedHistoryRecord);
System.out.println(savedDetailedLaborList);
        modelAndView.addObject("historyRecord", savedHistoryRecord);
        modelAndView.addObject("laborHourList", savedDetailedLaborList);
        String recordDate = CalendarAdapter.getStringFormat(savedHistoryRecord.getRecordDate());
        modelAndView.addObject("recordDate",  recordDate);
               return modelAndView;

    }

    @RequestMapping("/updateHistoryRecord/{historyRecordId}")
public ModelAndView updateHistoryRecord(@PathVariable("historyRecordId") int historyRecordId){

        ModelAndView modelAndView = new ModelAndView("updateHistoryRecord");
        HistoryRecordService historyRecordService = new HistoryRecordService();
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);

        modelAndView.addObject("historyRecord", historyRecord);
        String recordDate = CalendarAdapter.getStringFormat(historyRecord.getRecordDate());
        DetailedLaborHourService laborHourService = new DetailedLaborHourService();
        List<DetailedLaborHour> laborHourList = laborHourService.getDetailedLaborByRecordId(historyRecordId);
        modelAndView.addObject("recordDate", recordDate);
        modelAndView.addObject("laborHourList", laborHourList);
        return modelAndView;
    }
    @RequestMapping("/updatedHistoryRecord/{historyRecordId}")
    public ModelAndView updatedHistoryRecord(@PathVariable("historyRecordId") int historyRecordId,
                                            @RequestParam("title") String title,
                                            @RequestParam(name = "SMR", defaultValue = "0" ) int SMR,
                                            @RequestParam("date") String date,
                                            @RequestParam(name = "laborHour", defaultValue = "0") int laborHour,
                                            @RequestParam("workerName[]") String[] workerNames,
                                            @RequestParam(value = "manHour[]")  String[] manHours
                                             )
    {
        ModelAndView modelAndView = new ModelAndView("historyRecord");
        HistoryRecordService historyRecordService = new HistoryRecordService();
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        historyRecord.setTitle(title);
        historyRecord.setSMR(SMR);
        historyRecord.setRecordDate(CalendarAdapter.getGregCalendar(date));
        historyRecord.setLaborHour(laborHour);
        historyRecordService.updateHistoryRecord(historyRecord);
        DetailedLaborHour  detailedLaborHour;
        DetailedLaborHourService laborHourService = new DetailedLaborHourService();
        laborHourService.deleteAllByHistoryRecordId(historyRecordId);
        for(int i = 0; i<workerNames.length; i++) {
            if (!workerNames[i].equals("")) {
                detailedLaborHour = new DetailedLaborHour();
                detailedLaborHour.setWorkerName(workerNames[i]);
                detailedLaborHour.setHistoryRecord(historyRecord);
                if (!manHours[i].equals("")) {
                    detailedLaborHour.setJobDuration(Integer.parseInt(manHours[i]));
                                   }
                laborHourService.saveDetailedLaborHour(detailedLaborHour);
            }
        }
HistoryRecord updatedHistoryRecord = historyRecordService.getHistoryRecordById(historyRecordId);
                 modelAndView.addObject("historyRecord", updatedHistoryRecord);
                 String recordDate = CalendarAdapter.getStringFormat(updatedHistoryRecord.getRecordDate());
                 List<DetailedLaborHour> laborHourList = laborHourService.getDetailedLaborByRecordId(historyRecordId);
                  System.out.println(recordDate);
                 modelAndView.addObject("recordDate", recordDate);
                 modelAndView.addObject("laborHourList", laborHourList);
       return  modelAndView;
                    }


    @PostMapping("/fileAdded/{historyRecordId}")
public  ModelAndView fileAdded(@PathVariable("historyRecordId") int historyRecordId,
                               @RequestParam("file")  MultipartFile  file){
        System.out.println("insidee servlet");
        ModelAndView modelAndView = new ModelAndView("historyRecord");
        StorageService storageService = new StorageService();
        storageService.storeFile(file, historyRecordId);
       return modelAndView;

}



}





