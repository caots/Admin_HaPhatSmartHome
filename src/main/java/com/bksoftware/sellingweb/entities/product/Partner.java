package com.bksoftware.sellingweb.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "partner")
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "partner")
    private List<Product> lstProduct = new ArrayList<>();

    @Column(name = "img_url")
    private String imgUrl;

    private String present;

    @NotNull
    private boolean status;
}
