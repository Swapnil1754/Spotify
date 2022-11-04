package com.niit.backend.connectivity.SongService.Repository;

import com.niit.backend.connectivity.SongService.Domain.PlayList;
import com.niit.backend.connectivity.SongService.Domain.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistRepository extends MongoRepository<Song,String> {
    Song findBySongName(String songName);
}
