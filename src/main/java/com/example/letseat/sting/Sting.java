package com.example.letseat.sting;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Sting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private Long receiverId;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private LocalDateTime stingDate;

    @Column(nullable = false)
    private Long countSting=1L;//몇번째 찌르기 했는지

}
