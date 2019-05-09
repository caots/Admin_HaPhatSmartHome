package com.bksoftware.sellingweb.entities.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "buy_form_has_product")
public class BuyFormHasProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "buy_form_id")
    private int buyFormId;

    @Column(name = "product_id")
    private int productId;

    private Integer quantity;

    @Column(name = "sold_date")
    private LocalDate soldDate;

    private Boolean status = true;

}

