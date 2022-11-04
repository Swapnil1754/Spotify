package com.niit.backend.connectivity.SongService.Producer;

import com.niit.backend.connectivity.SongService.rabbitmq.Domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
    @Autowired
    Producer(RabbitTemplate rabbitTemplate,DirectExchange directExchange){
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }
    public void sendMessageToRabbitMq(UserDTO userDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(),"my_routing",userDTO);
    }
}
