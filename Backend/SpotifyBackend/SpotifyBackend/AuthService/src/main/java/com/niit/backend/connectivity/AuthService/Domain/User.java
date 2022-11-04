package com.niit.backend.connectivity.AuthService.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    private String email;
    private String userName;
    private String password;
}
