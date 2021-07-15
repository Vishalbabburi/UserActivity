package com.miracle.UserActivity.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ExceptionResponse {
    private String errorMessage;
    private int errorCode;
    private LocalDateTime timestamp;

   
}
