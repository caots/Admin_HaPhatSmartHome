package com.bksoftware.sellingweb.entities.product;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class BuyFormDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product;

    private int quantity;

    private LocalDate soldDate;

}
