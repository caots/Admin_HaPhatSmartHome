package com.bksoftware.sellingweb.entities.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product_details")
@SecondaryTable(name = "product")
public class ProductDetails implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    private int id;

    @Column(name = "product_status")
    private boolean productStatus;

    private int guarantee;

    private String present;

    private String news;

    private String info;

    private boolean status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;

    public ProductDetails() {
    }


}
