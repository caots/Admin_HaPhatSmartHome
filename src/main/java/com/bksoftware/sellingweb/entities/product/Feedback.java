package com.bksoftware.sellingweb.entities.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "feedback")
@SecondaryTables({
        @SecondaryTable(name = "product_details")
})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String username;

    private String email;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime time;

    @NotNull
    private int star;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_details_id" ,nullable = false)
    @NotNull
    private ProductDetails productDetails;

    private boolean status;

}
