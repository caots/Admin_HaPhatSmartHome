package com.bksoftware.sellingweb.entities.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class BuyFormCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private long phoneNumber;

    private String email;

    private String address;

    private String note;

    private LocalDate date;

    private boolean checked;

    private Set<String> products = new HashSet<>();

    private long price;

    private boolean status;

    private int quantity;

    public BuyFormCart(){}
}
