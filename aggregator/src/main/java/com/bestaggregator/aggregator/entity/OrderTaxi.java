package com.bestaggregator.aggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_taxi")
@Data
@AllArgsConstructor
public class OrderTaxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long id_order;

    @Column
    private Long transaction;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OrderTaxi(){}

    public boolean getStatus(){
        return status;
    }
}
