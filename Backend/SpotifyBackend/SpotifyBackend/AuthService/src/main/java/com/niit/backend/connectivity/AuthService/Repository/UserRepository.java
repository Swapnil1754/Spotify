package com.niit.backend.connectivity.AuthService.Repository;

import com.niit.backend.connectivity.AuthService.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmailAndPassword(String email,String password);
}
