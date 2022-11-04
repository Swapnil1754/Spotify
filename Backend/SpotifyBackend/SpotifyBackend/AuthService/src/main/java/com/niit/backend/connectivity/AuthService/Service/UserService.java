package com.niit.backend.connectivity.AuthService.Service;

import com.niit.backend.connectivity.AuthService.Domain.User;
import com.niit.backend.connectivity.AuthService.Exceptions.UserAlreadyExistException;
import com.niit.backend.connectivity.AuthService.Exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws UserAlreadyExistException;
    public User findByEmailAndPassword(String email,String password) throws UserNotFoundException;
    List<User> getAllUsers();
}
