package com.bksoftware.sellingweb.entities.product;

import com.bksoftware.sellingweb.entities.product.ProductDetails;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "feature")
@SecondaryTables({
        @SecondaryTable(name = "product_details")
})
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String detail;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_details_id")
    @NotNull
    private ProductDetails productDetails;

    @NotNull
    private boolean status;
}
