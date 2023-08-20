package com.luv2code.springboot.cruddemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authorities",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false,
            foreignKey = @ForeignKey(name = "authorities_fk_user"))
    private User user;

    @Column(length = 50, nullable = false)
    private String authority;


}
