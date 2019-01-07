package group.service.iko.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_machine")
public class ServiceMachine {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   @Column
   private String name;

    @Override
    public String toString() {
        return "ServiceMachine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
