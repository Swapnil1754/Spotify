package com.niit.backend.connectivity.SongService.Producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
    private String exchangeName = "data_exchange";
    private String queueName = "my_queue";
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue registerQueue(){
        return new Queue(queueName);
    }
    @Bean
    public Binding userBinding(Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("my_routing");
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerConverter());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter producerConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
