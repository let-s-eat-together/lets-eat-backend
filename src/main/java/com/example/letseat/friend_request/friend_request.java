package com.example.letseat.friend_request;


import com.example.letseat.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity

public class friend_request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id2")
    private User user2;

    @Column(nullable = false)
    private LocalDateTime creation_date;

}
