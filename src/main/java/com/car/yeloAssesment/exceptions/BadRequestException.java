package com.car.yeloAssesment.exceptions;

public class BadRequestException  extends  RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super();
    }
}
