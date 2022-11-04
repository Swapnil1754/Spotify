//package com.niit.backend.connectivity.AuthService.Consumer;
//
//import com.niit.backend.connectivity.AuthService.Domain.User;
//import com.niit.backend.connectivity.AuthService.Exceptions.UserAlreadyExistException;
//import com.niit.backend.connectivity.AuthService.Service.UserServiceImpl;
//import com.niit.backend.connectivity.AuthService.rabbitmq.Domain.UserDTO;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//    @Autowired
//    private UserServiceImpl userService;
//    @RabbitListener(queues ="my_queue")
//    public void getDataFromRabbitMq(UserDTO userDTO) throws UserAlreadyExistException {
//        User user = new User();
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        System.out.println("Trying to Save Data to MySQL..." +user.getEmail()+" "+user.getPassword());
//        userService.saveUser(user);
//    }
//}
