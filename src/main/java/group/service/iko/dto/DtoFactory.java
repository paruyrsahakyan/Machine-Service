package group.service.iko.dto;

import group.service.iko.entities.Customer;
import group.service.iko.entities.Machine;

import java.util.ArrayList;
import java.util.List;

public class DtoFactory {



    static public List<MachineDTO> makeMachineDtoList(List<Machine> machineList) {
        List<MachineDTO> machineDTOList = new ArrayList<MachineDTO>();
        for (Machine machine : machineList) {
            machineDTOList.add(new MachineDTO(machine));

        }
        return machineDTOList;
    }

    public static List<CustomerDTO> makeCustomerDtoList(List<Customer> customerList) {

        List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
        for (Customer customer : customerList) {
            customerDTOList.add(new CustomerDTO(customer));
        }
        return customerDTOList;
    }

}
