package com.example.letseat.plan;

import com.example.letseat.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private Date creation_date;

    private Date expriation_date;

    @ManyToMany(mappedBy = "plans")
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }
}
