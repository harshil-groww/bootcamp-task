package com.task.portfolio.portfolio.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(name = "name", unique = false)
    private String Name;

    @Column(name = "phoneNo", unique = true, nullable = false)
    private String PhoneNo;

    @Column(name = "pan", unique = true, nullable = false)
    private String PAN;

    @Column(name = "emailId", unique = true, nullable = false)
    private String EmailId;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Portfolio> portfolios;

    public User() {

    }

    public User(long userId, String name, String phoneNo, String PAN, String emailId) {
        UserId = userId;
        Name = name;
        PhoneNo = phoneNo;
        this.PAN = PAN;
        EmailId = emailId;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
}
