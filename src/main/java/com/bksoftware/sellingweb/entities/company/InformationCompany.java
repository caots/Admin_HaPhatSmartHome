package com.bksoftware.sellingweb.entities.company;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "company_information")
@SecondaryTables({
        @SecondaryTable(name = "company")
})
public class InformationCompany implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    @NotNull
    private Company company;

    @NotNull
    private boolean status;

}
