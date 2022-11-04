package com.niit.backend.connectivity.SongService.Proxy;

import com.niit.backend.connectivity.SongService.Domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "AUTH-SERVICE")
public interface UserProxy {
    @PostMapping("/api/v1/register")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
