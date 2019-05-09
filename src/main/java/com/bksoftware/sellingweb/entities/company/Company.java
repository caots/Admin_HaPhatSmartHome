package com.bksoftware.sellingweb.entities.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "company")
public class Company  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private int hotline;

    @NotNull
    private String email;

    private String coordinates;

    @NotNull
    private boolean status;
}
