package com.upgrad.bookingservice.service;

import com.upgrad.bookingservice.entity.BookingInfoEntity;
import com.upgrad.bookingservice.entity.TransactionDetailsEntity;
import com.upgrad.bookingservice.exception.RecordNotFoundException;

public interface BookingService {
    public BookingInfoEntity acceptBookingRequest(BookingInfoEntity bookingRequest);
    public BookingInfoEntity sendPaymentDetails(int id, TransactionDetailsEntity transactionDetails);
}
