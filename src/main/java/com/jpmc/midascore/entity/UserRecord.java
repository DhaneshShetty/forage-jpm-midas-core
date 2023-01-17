package com.jpmc.midascore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class UserRecord {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float balance;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<TransactionRecord> sentTransaction = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<TransactionRecord> receivedTransaction = new ArrayList<>();

    protected UserRecord() {
    }

    public UserRecord(String name, float balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', balance='%f'", id, name, balance);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<TransactionRecord> getSentTransaction() {
        return this.sentTransaction;
    }

    public void setSentTransaction(List<TransactionRecord> sentTransaction) {
        this.sentTransaction = sentTransaction;
    }

    public List<TransactionRecord> getReceivedTransaction() {
        return this.receivedTransaction;
    }

    public void setReceivedTransaction(List<TransactionRecord> receivedTransaction) {
        this.receivedTransaction = receivedTransaction;
    }
}
