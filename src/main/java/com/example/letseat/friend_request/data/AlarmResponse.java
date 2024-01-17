package com.example.letseat.friend_request.data;

import lombok.Data;

@Data
public class AlarmResponse {
    private Long requestingUserId;
    private String friendName;
    private String creationDate;
}
