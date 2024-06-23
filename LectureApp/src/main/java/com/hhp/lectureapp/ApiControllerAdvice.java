package com.hhp.lectureapp;

import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> ExceptionHandler(CustomException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(new ErrorResponse(e.getStatusCode(), e.getMsg()));
    }
}
