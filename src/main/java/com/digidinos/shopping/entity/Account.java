package com.digidinos.shopping.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Accounts")
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    @Id
    @Column(name = "User_Name", length = 20, nullable = false)
    private String userName;
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;
    @Column(name = "Active", length = 1, nullable = false)
    private boolean active;
    @Column(name = "User_Role", length = 20, nullable = false)
    private String userRole;
}
