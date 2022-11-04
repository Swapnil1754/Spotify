package com.niit.backend.connectivity.SongService.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class User {
    @Id
    private String email;
    @Transient
    private String password;
    private String userName;
    private long phoneNo;
    private List<PlayList> playLists;
}
