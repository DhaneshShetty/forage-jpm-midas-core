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

    @Column(nullable = false)
    private float incentive;


    public TransactionRecord() {
    }

    public TransactionRecord(float amount, UserRecord sender, UserRecord receiver,float incentive) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.incentive = incentive;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getIncentive() {
        return this.incentive;
    }

    public void setIncentive(float incentive) {
        this.incentive = incentive;
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


    public TransactionRecord(long id, UserRecord sender, UserRecord receiver, float amount, float incentive) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.incentive = incentive;
    }

    public TransactionRecord amount(float amount) {
        setAmount(amount);
        return this;
    }

    public TransactionRecord incentive(float incentive) {
        setIncentive(incentive);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TransactionRecord)) {
            return false;
        }
        TransactionRecord transactionRecord = (TransactionRecord) o;
        return id == transactionRecord.id && Objects.equals(sender, transactionRecord.sender) && Objects.equals(receiver, transactionRecord.receiver) && amount == transactionRecord.amount && incentive == transactionRecord.incentive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, amount, incentive);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", sender='" + getSender() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", amount='" + getAmount() + "'" +
            ", incentive='" + getIncentive() + "'" +
            "}";
    }
   


}
