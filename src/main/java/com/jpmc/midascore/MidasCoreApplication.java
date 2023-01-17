package com.jpmc.midascore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

import com.jpmc.midascore.foundation.Transaction;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class MidasCoreApplication {
    @Value("${general.kafka-topic}") 
    private String topicName;

    public static void main(String[] args) {
        System.out.println("Started");
        SpringApplication.run(MidasCoreApplication.class, args);
        
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
        .partitions(1)
        .replicas(1)
        .build();
    }

    @KafkaListener(topics = "topic1",
    containerFactory = "kafkaListenerContainerFactory",groupId = "group_id")
    public void listen(Transaction transaction){
        System.out.println(transaction);
    }

}
