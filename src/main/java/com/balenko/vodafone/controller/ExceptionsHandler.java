package com.balenko.vodafone.controller;

import com.balenko.vodafone.dto.ResponseDto;
import com.balenko.vodafone.exceptions.ResourceNotFoundException;
import com.balenko.vodafone.exceptions.ResourceNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = ResourceNotUniqueException.class)
    public ResponseEntity handleResourceNotUniqueException(ResourceNotUniqueException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>(new ResponseDto.Error(400, e.getMessage())));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto<>(new ResponseDto.Error(404, e.getMessage())));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>(new ResponseDto.Error(400, "Format is not valid")));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleServerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto<>(new ResponseDto.Error(500, e.getMessage())));
    }

}
