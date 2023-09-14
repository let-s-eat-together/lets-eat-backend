package com.example.letseat.userPlan.dto;

import com.example.letseat.userPlan.UserPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StingResponse {

    private  String sender_name;

    private String receiver_name;

    private String now_date;

}
