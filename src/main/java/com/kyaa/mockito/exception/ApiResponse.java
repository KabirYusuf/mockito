package com.kyaa.mockito.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ApiResponse {
    private String path;
    private ZonedDateTime zonedDateTime;
    private Object message;
    private boolean isSuccessful;
    private HttpStatus status;
    private int statusCode;
}
