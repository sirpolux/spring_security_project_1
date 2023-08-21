package com.luv2code.springboot.cruddemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "roles"})})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "roles_fk_members"))
    private Members members;
    @Column(name="roles",length = 50, nullable = false)
    private String roles;

}
