package com.example.letseat.user;

import com.example.letseat.friend_relation.FriendRelation;
import com.example.letseat.plan.Plan;
import com.example.letseat.userPlan.UserPlan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String deviceId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlan> userPlans = new ArrayList<>();

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    private List<FriendRelation> friendRelationsAsUser1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    private List<FriendRelation> friendRelationsAsUser2 = new ArrayList<>();

    public void addPlan(Plan plan) {
        UserPlan userPlan = new UserPlan();
        userPlan.setPlan(plan);
        userPlan.setUser(this);
        plan.getUserPlans().add(userPlan);
        userPlans.add(userPlan);
    }

    public List<Plan> getPlans() {
        ArrayList<Plan> plans = new ArrayList<>();
        for (UserPlan userPlan : userPlans) {
            plans.add(userPlan.getPlan());
        }
        return plans;
    }

    public void addFriend(User user) {
        FriendRelation friendRelation = new FriendRelation();
        friendRelation.setUser1(this);
        friendRelation.setUser2(user);
        user.getFriendRelationsAsUser2().add(friendRelation);
        friendRelationsAsUser1.add(friendRelation);
    }

    public List<User> getFriends() {
        List<User> friends = new ArrayList<>();

        for(FriendRelation friendRelation : friendRelationsAsUser1) {
            friends.add(friendRelation.getUser2());
        }

        for(FriendRelation friendRelation : friendRelationsAsUser2) {
            friends.add(friendRelation.getUser1());
        }

        return friends;
    }
}

