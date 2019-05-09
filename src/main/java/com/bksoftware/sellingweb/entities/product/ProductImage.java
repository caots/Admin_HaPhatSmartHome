package com.bksoftware.sellingweb.entities.product;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product_image")
@SecondaryTables({
        @SecondaryTable(name = "product_details")
})
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String url;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id", nullable = false)
    @NotNull
    private ProductDetails productDetails;

    @NotNull
    private boolean status;

}
