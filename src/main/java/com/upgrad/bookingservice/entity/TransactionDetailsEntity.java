package com.upgrad.bookingservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionID;

    @Column
    private String paymentMode;

    @Column(nullable = false)
    private int bookingID;

    @Column(nullable = true)
    private String upiID;

    @Column(nullable = true)
    private String cardNumber;

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getUpiID() {
        return upiID;
    }

    public void setUpiID(String upiID) {
        this.upiID = upiID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "TransactionDetailsEntity{" +
                "transactionID=" + transactionID +
                ", paymentMode='" + paymentMode + '\'' +
                ", bookingID=" + bookingID +
                ", upiID='" + upiID + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}