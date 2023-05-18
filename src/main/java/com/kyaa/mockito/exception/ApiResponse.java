package com.kyaa.mockito.exception;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ApiResponse {
    private String path;
    private ZonedDateTime zonedDateTime;
    private Object response;
    private boolean isSuccessful;
    private int statusCode;
}
