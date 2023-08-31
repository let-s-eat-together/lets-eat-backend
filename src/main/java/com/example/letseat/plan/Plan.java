package com.example.letseat.plan;

import com.example.letseat.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime creation_date;

    private LocalDateTime expiration_date;

    @JsonBackReference
    @ManyToMany(mappedBy = "plans")
    private List<User> users = new ArrayList<>();
}
