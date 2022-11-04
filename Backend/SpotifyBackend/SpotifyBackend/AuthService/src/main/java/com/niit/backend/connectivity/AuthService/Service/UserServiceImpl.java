package com.niit.backend.connectivity.AuthService.Service;

import com.niit.backend.connectivity.AuthService.Domain.User;
import com.niit.backend.connectivity.AuthService.Exceptions.UserAlreadyExistException;
import com.niit.backend.connectivity.AuthService.Exceptions.UserNotFoundException;
import com.niit.backend.connectivity.AuthService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistException {
        if (userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }else {
            return userRepository.save(user);
        }
    }
    @Override
    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
