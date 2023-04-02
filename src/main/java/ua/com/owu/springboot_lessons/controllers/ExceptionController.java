package ua.com.owu.springboot_lessons.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
//    @ExceptionHandler(MethodArgumentNotValidException.class) //
//    public String exceptionHandler(MethodArgumentNotValidException e){
//        return e.getFieldError().getDefaultMessage();
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> exceptionHandler(MethodArgumentNotValidException e){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("headerName","value");

        ResponseEntity<String> response = new ResponseEntity<>(e.getFieldError().getDefaultMessage(),httpHeaders, HttpStatus.BAD_REQUEST);
        return response;
    }
}

//@ExceptionHandler annotation can then be used to define methods that handle specific types of exceptions that might occur during the execution of a RESTful web service.
//In the provided code, the exceptionHandler() method takes in the MethodArgumentNotValidException exception as an argument, and returns the default error message associated
// with the first field that failed validation, as obtained from the getFieldError() method of the exception object. This message can then be returned to the client as a response to the failed request.