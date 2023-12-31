package com.example.letseat.plan;

import com.example.letseat.user.User;
import com.example.letseat.userPlan.UserPlan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate creation_date;

    @Column(nullable = false)
    private LocalDate expiration_date;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<UserPlan> userPlans = new ArrayList<>();

    @Column(columnDefinition = "boolean default false")
    private boolean met;

    public void addUser(User user) {
        user.addPlan(this);
    }

    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for(UserPlan userPlan : userPlans) {
            users.add(userPlan.getUser());
        }
        return users;
    }
}
