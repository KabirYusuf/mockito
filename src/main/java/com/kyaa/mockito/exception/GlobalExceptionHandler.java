package com.kyaa.mockito.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse> userExceptionHandler(UserException userException,
                                               HttpServletRequest httpServletRequest){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setPath(httpServletRequest.getRequestURI());
        apiResponse.setSuccessful(false);
        apiResponse.setMessage(userException.getMessage());
        apiResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
