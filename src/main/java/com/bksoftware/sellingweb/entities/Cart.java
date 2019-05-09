package com.bksoftware.sellingweb.entities;

import com.bksoftware.sellingweb.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {
    private Product product;

    private int quantity;

    public Cart() {
    }

}
