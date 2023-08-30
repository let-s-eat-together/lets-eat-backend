package com.example.letseat.plan;

import com.example.letseat.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class planDto {
    private Long id;
    private Date creation_date;
    private Date expriation_date;
    private List<User> user;
}
