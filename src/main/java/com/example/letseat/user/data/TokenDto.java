package com.example.letseat.user.data;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String token;
    private Long user_id;
    private String name;

}
