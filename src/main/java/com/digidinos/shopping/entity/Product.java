package com.digidinos.shopping.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Products")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -1000119078147252957L;
    @Id
    @Column(name = "Code", length = 20, nullable = false)
    private String code;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;
    @Column(name = "Price", nullable = false)
    private double price;
    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;
}