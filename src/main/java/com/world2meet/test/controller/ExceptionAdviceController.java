package com.world2meet.test.controller;

import com.world2meet.test.payload.response.ErrorResponse;
import com.world2meet.test.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionAdviceController {

    //Validator for params fields
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new ErrorResponse(Constants.MISSING_PARAM_FIELDS_CODE), HttpStatus.BAD_REQUEST);
    }
    //Validator for number fields
    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<Object> httpMessageNotReadable(NumberFormatException ex) {
        return new ResponseEntity<>(new ErrorResponse(Constants.INCORRECT_FORMAT_FIELDS_CODE), HttpStatus.BAD_REQUEST);
    }


}
