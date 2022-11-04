package com.niit.backend.connectivity.SongService.Repository;

import com.niit.backend.connectivity.SongService.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongsRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
