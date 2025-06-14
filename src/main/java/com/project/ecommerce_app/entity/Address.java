package com.project.ecommerce_app.entity;

import jakarta.persistence.*;

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    private String phone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
