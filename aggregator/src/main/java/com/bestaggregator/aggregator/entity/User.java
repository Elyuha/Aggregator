package com.bestaggregator.aggregator.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String phone;

    @Column
    @NotNull
    private String password;

    @Column
    private String card;

    @Column
    private String cardData;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public void setRole(Role byId) {
        role = byId;
    }
}
