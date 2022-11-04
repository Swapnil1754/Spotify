package com.niit.backend.connectivity.SongService.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Song {
    @Id
    private String songName;
    private String artist,genre,file_p;
    private byte[] url;
}
