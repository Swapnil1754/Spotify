package com.niit.backend.connectivity.AuthService.rabbitmq.Domain;

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
