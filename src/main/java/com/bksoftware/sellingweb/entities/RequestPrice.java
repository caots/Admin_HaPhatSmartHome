package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "request_price")
public class RequestPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private long phone;

    private String email;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "name_company")
    private String nameCompany;

    private String address;

    private String file;

    private boolean status;

}
