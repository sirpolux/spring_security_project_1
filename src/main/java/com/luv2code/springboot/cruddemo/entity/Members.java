package com.luv2code.springboot.cruddemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "members")
public class Members {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(length = 68, nullable = false)
    private String pw;

    @Column(length = 1)
    private Integer enabled;
}
