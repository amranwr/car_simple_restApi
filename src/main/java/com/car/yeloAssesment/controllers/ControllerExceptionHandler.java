package com.car.yeloAssesment.controllers;

import com.car.yeloAssesment.exceptions.ExceptionResponse;
import com.car.yeloAssesment.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.lang.NumberFormatException;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e , WebRequest req){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timeStamp(new Date()).message(e.getMessage()).details(req.getDescription(false)).build();
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(MethodArgumentTypeMismatchException e ,WebRequest req){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),e.getMessage(),req.getDescription(false));
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"not valid arguments"
                ,e.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
