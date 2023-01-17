package com.jpmc.midascore.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue()
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserRecord sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private UserRecord receiver;

    @Column(nullable = false)
    private float amount;


    public TransactionRecord() {
    }

    public TransactionRecord(float amount, UserRecord sender, UserRecord receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRecord getSender() {
        return this.sender;
    }

    public void setSender(UserRecord sender) {
        this.sender = sender;
    }

    public UserRecord getReceiver() {
        return this.receiver;
    }

    public void setReceiver(UserRecord receiver) {
        this.receiver = receiver;
    }

    public TransactionRecord id(long id) {
        setId(id);
        return this;
    }

    public TransactionRecord sender(UserRecord sender) {
        setSender(sender);
        return this;
    }

    public TransactionRecord receiver(UserRecord receiver) {
        setReceiver(receiver);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TransactionRecord)) {
            return false;
        }
        TransactionRecord transaction = (TransactionRecord) o;
        return id == transaction.id && Objects.equals(sender, transaction.sender) && Objects.equals(receiver, transaction.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", sender='" + getSender() + "'" +
            ", receiver='" + getReceiver() + "'" +
            "}";
    }


}
