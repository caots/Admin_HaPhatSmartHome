package com.bksoftware.sellingweb.entities.product;

import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@SecondaryTables({
        @SecondaryTable(name = "partner"),
        @SecondaryTable(name = "product_details"),
        @SecondaryTable(name = "small_category")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @NotNull
    private String name;

    @Column(name = "origin_cost")
    @NotNull
    private double originCost;

    @Column(name = "sale_cost")
    private double saleCost;

    @Column(name = "img_url")
    @NotNull
    private String imgUrl;

    @Column(name = "init_date")
    @NotNull
    private LocalDateTime initDate;

    private int view;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "small_category_id", nullable = false)
    @NonNull
    private SmallCategory smallCategory;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    @JsonIgnore
    private ProductDetails productDetails;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id", nullable = false)
    @NotNull
    private Partner partner;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "products")
    private List<BuyForm> buyForms = new ArrayList<>();

    private boolean status;

    public Product() {
    }

}
