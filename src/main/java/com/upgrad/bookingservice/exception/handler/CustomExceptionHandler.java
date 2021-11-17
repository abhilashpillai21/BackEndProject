package com.upgrad.bookingservice.exception.handler;

import com.upgrad.bookingservice.dto.ErrorDTO;
import com.upgrad.bookingservice.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorDTO> handleRecordNotFoundException(Exception ex, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }


}
