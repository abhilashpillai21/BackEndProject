package com.upgrad.bookingservice.controller;

import com.upgrad.bookingservice.dto.BookingInfoDTO;
import com.upgrad.bookingservice.dto.TransactionDetailsDTO;
import com.upgrad.bookingservice.entity.BookingInfoEntity;
import com.upgrad.bookingservice.entity.TransactionDetailsEntity;
import com.upgrad.bookingservice.exception.RecordNotFoundException;
import com.upgrad.bookingservice.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class BookingInfoController {

    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingInfoController(BookingService bookingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/booking", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoDTO> sendBookingRequest(@RequestBody BookingInfoDTO request){
        BookingInfoEntity bookingInfo = modelMapper.map(request, BookingInfoEntity.class);
        BookingInfoEntity savedBookingInfo = bookingService.acceptBookingRequest(bookingInfo);
        BookingInfoDTO savedBookingInfoDTO = modelMapper.map(savedBookingInfo, BookingInfoDTO.class);
        return new ResponseEntity(savedBookingInfoDTO, HttpStatus.CREATED);
    }

    @PostMapping(path = "/booking/{bookingId}/transaction", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoDTO> sendPaymentDetails(@PathVariable(name="bookingId") int bookingId,
                                                             @RequestBody TransactionDetailsDTO request){

        TransactionDetailsEntity transactionDetails = modelMapper.map(request, TransactionDetailsEntity.class);
        BookingInfoEntity bookingInfo = bookingService.sendPaymentDetails(bookingId, transactionDetails);
        BookingInfoDTO bookingInfoDTO = modelMapper.map(bookingInfo, BookingInfoDTO.class);
        return new ResponseEntity(bookingInfoDTO, HttpStatus.CREATED);
    }

}
