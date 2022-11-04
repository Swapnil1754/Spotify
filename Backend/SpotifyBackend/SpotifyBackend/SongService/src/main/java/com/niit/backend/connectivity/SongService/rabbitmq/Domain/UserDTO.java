package com.niit.backend.connectivity.SongService.rabbitmq.Domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String email;
    private String password;
}
