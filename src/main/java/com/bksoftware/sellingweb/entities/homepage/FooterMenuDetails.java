package com.bksoftware.sellingweb.entities.homepage;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "footer_menu_details")
@SecondaryTable(name = "footer_menu")
public class FooterMenuDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "footer_menu_id")
    private FooterMenu footerMenu;


    @NotNull
    private boolean status;
}
