package com.jpmc.midascore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.beans.factory.annotation.Autowired;


import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;

import java.util.NoSuchElementException;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class MidasCoreApplication {
    @Value("${general.kafka-topic}") 
    private String topicName;

    @Autowired
    private DatabaseConduit databaseConduit;

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
        try{
        UserRecord sender = databaseConduit.getUserById(transaction.getSenderId());
        UserRecord receiver = databaseConduit.getUserById(transaction.getRecipientId());
        if(sender.getBalance()>=transaction.getAmount()){
            sender.setBalance(sender.getBalance()-transaction.getAmount());
            receiver.setBalance(receiver.getBalance()+transaction.getAmount());
            databaseConduit.save(sender);
            databaseConduit.save(receiver);
            TransactionRecord newTransaction = new TransactionRecord(transaction.getAmount(),sender,receiver);
            databaseConduit.saveTransaction(newTransaction);
            System.out.println("Transaction:"+newTransaction);
            System.out.println("Sender:"+sender);
            System.out.println("Receiver:"+receiver);
        }
        else{
            System.out.println("Low balance in sender");
        }
        }
        catch(NoSuchElementException e){
            System.out.println("Invalid user id");
        }
    }

}
