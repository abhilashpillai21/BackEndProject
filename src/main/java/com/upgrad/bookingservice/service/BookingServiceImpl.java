package com.upgrad.bookingservice.service;

import com.upgrad.bookingservice.dao.BookingInfoDao;
import com.upgrad.bookingservice.dto.TransactionDetailsDTO;
import com.upgrad.bookingservice.entity.BookingInfoEntity;
import com.upgrad.bookingservice.entity.TransactionDetailsEntity;
import com.upgrad.bookingservice.exception.RecordNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService{
    private final BookingInfoDao bookingInfoDao;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(BookingInfoDao bookingInfoDao, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.bookingInfoDao = bookingInfoDao;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }


    /*
    fromDate
    toDate
    aadharNumber
    numOfRooms
   */

    @Override
    public BookingInfoEntity acceptBookingRequest(BookingInfoEntity bookingRequest) {
        bookingRequest.setRoomNumbers("123, 125, 126"); //create randomly.
        bookingRequest.setRoomPrice(15000); //create programatically.
        bookingRequest.setBookedOn(new Date());
        BookingInfoEntity savedrequest = bookingInfoDao.save(bookingRequest);
        return savedrequest;
    }

    /*
    paymentMode
    bookingID
    upiId
    cardNumber
     */
    @Override
    public BookingInfoEntity sendPaymentDetails(int id, TransactionDetailsEntity transactionDetails) {
        //POST to payment
        postPaymentDetails(transactionDetails);
        //Receive from payment
        BookingInfoEntity bookingInfo = getDetailsFromPaymentService(id);
        return bookingInfo;
    }

    private BookingInfoEntity getDetailsFromPaymentService(int id) {
        String paymentAppURL = "http://localhost:8083/payment/transaction/{id}";
        Map<String, String> paymentURLMap = new HashMap<>();
        paymentURLMap.put("id",String.valueOf(id));

        TransactionDetailsEntity transaction = restTemplate.getForObject(paymentAppURL,
                TransactionDetailsEntity.class,
                paymentURLMap);
        if(transaction==null)
            throw new NullPointerException();

        BookingInfoEntity bookingInfo = bookingInfoDao.getById(id);
        bookingInfo.setTransactionId(transaction.getTransactionID());

        return bookingInfo;
    }

    private void postPaymentDetails(TransactionDetailsEntity transactionDetails) {
        String paymentAppURL = "http://localhost:8083/payment/transaction";
        TransactionDetailsDTO transactionDetailsDTO = modelMapper.map(transactionDetails, TransactionDetailsDTO.class);
        restTemplate.postForObject(paymentAppURL, transactionDetailsDTO, Void.class);
    }
}
