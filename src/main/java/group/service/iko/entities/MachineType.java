package group.service.iko.entities;

        import group.service.iko.dto.PeriodicMaintenanceDTO;
        import org.hibernate.annotations.Cascade;
        import org.hibernate.annotations.LazyCollection;
        import org.hibernate.annotations.LazyCollectionOption;
        import org.springframework.security.core.parameters.P;


        import javax.persistence.*;
        import java.util.ArrayList;
        import java.util.Comparator;
        import java.util.List;
        import java.util.Set;
        import java.util.stream.Collectors;

@Entity
@Table(name = "machine_type")
public class MachineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="type_description")
    private String typeDescription;

    @OneToMany(mappedBy = "machineType", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<PeriodicMaintenance> periodicMaintenanceList;

    @OneToMany(mappedBy = "machineType", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Machine> machineList;


    @Override
    public String toString() {
        return "MachineType{" +
                "id=" + id +
                ", typeDescription='" + typeDescription + '\'' +
                ", periodicMaintenanceList=" + periodicMaintenanceList +
                ", machineList=" + machineList +
                '}';
    }
    public MachineType(){

    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeriodicMaintenanceList(Set<PeriodicMaintenance> periodicMaintenanceList) {
        this.periodicMaintenanceList = periodicMaintenanceList;
    }

    public Set<PeriodicMaintenance> getPeriodicMaintenanceList() {
        return periodicMaintenanceList;
    }

    public Set<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(Set<Machine> machineList) {
        this.machineList = machineList;
    }

    public List<PeriodicMaintenanceDTO> getSortedMaintenanceList(){

        List<PeriodicMaintenance> periodicMaintenanceList=getPeriodicMaintenanceList().stream().
                sorted(Comparator.comparing(PeriodicMaintenance::getSmr)).collect(Collectors.toList());
        List<PeriodicMaintenanceDTO> periodicMaintenanceDTOList = new ArrayList<>();
        for (PeriodicMaintenance periodicMaintenance: periodicMaintenanceList){
            periodicMaintenanceDTOList.add(new PeriodicMaintenanceDTO(periodicMaintenance));
        }
        return periodicMaintenanceDTOList;
    }
}
