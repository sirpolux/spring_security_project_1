package com.luv2code.springboot.cruddemo.entity;


import jakarta.persistence.*;

@Entity

public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    private String pw;
    @Column(length = 1)
    private Integer active;

}
