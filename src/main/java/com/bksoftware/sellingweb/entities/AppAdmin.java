package com.bksoftware.sellingweb.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "admin")
public class AppAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    // dat password duoi database cung la password
    private String password;

    private String email;

    private int phone;

//    public Set<GrantedAuthority> getGrantedAuthority() {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add((GrantedAuthority) () -> "ADMIN");
//        return grantedAuthorities;
//    }
}
