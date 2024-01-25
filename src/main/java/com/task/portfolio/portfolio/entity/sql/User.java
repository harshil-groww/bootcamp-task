package com.task.portfolio.portfolio.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNo", unique = true, nullable = false)
    private String phoneNo;

    @Column(name = "pan", unique = true, nullable = false)
    private String pan;

    @Column(name = "emailId", unique = true, nullable = false)
    private String emailId;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Portfolio> portfolios;

}
