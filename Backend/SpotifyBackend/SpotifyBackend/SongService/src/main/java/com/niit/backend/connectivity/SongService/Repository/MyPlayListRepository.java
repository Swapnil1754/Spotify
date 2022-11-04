package com.niit.backend.connectivity.SongService.Repository;

import com.niit.backend.connectivity.SongService.Domain.PlayList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyPlayListRepository extends MongoRepository<PlayList,String> {
    PlayList findByPlayListId(int playListId);
    PlayList findByEmail(String email);
}
