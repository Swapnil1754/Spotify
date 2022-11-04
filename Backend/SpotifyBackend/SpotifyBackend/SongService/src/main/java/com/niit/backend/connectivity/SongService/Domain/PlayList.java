package com.niit.backend.connectivity.SongService.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class PlayList {
    @Id
    String email;
    private int playListId;
    private String playListName;
    private List<Song> songList;
}
