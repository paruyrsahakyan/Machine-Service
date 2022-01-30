package group.service.iko.entities;


import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String requestedPartName;
    @Column
    private String requestedPartNumber;
    @Column
    private String offeredPartName;
    @Column
    private String offeredPartNumber;
    @Column
    private int quantity;
    @Column
    private String unit;
    @Column
    private int price;
    @Column
    private int sum;
    @Column
    private int supplyDate;
    @Column
    private String producer;
    @ManyToOne()
    @JoinColumn(name = "offer_id")
    private Offer offer;



}
