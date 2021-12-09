package com.car.yeloAssesment.exceptions;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private Date timeStamp;
    private String message ;
    private String details;
}
