package com.bksoftware.sellingweb.entities.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductGuarantee {


    private int id;

    private String name;

    private long day;

    public ProductGuarantee() {
    }
}
