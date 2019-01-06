package group.service.iko.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "periodic_maintenence")
public class PeriodicMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int smr;
    @OneToMany(mappedBy = "periodic_maintenence", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Part> partList;
    @Column
    private String serviceMachine;


}
