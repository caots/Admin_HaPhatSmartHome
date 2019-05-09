package com.bksoftware.sellingweb.entities.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reply")
@SecondaryTable(name = "feedback")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String username;

    private String email;

    @NotNull
    private LocalDateTime time;

    @NotNull
    private String content;

    @NotNull
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "feedback_id",nullable = false)
    @NotNull
    private Feedback feedback;

    @NotNull
    private String role;
}
