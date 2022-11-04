package com.niit.backend.connectivity.AuthService.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niit.backend.connectivity.AuthService.Domain.User;
import com.niit.backend.connectivity.AuthService.Exceptions.UserAlreadyExistException;
import com.niit.backend.connectivity.AuthService.Exceptions.UserNotFoundException;
import com.niit.backend.connectivity.AuthService.Service.SecurityTokenGenerator;
import com.niit.backend.connectivity.AuthService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserService userService,SecurityTokenGenerator securityTokenGenerator){
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/login")
    @HystrixCommand(fallbackMethod = "fallBackLogin",commandKey = "loginKey",groupKey = "login")
    @HystrixProperty(name = "execution.isolation.thread.timeoutMilliseconds",value = "10000")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        try{
            User user1 = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
            if (user1.getEmail().equals(user.getEmail())){
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map,HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error...!!! Try Again After Sometime...!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    public ResponseEntity<?> fallBackLogin(@RequestBody User user){
        String msg = "Login Failed...!!!";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistException {
        try {
            User createUser = userService.saveUser(user);
            return responseEntity = new ResponseEntity("User Data Saved Successfully...!!!",HttpStatus.CREATED);

        }catch (UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error...!!! User Already Exists, Try Again Later...!!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> list = userService.getAllUsers();
        responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }
}
