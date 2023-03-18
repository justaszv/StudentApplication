package com.swedbank.StudentApplication.Student;

import com.swedbank.StudentApplication.Student.exeption.StudentNotFoundException;
import com.swedbank.StudentApplication.building.BuildingNotFoundException;
import com.swedbank.StudentApplication.errors.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorMessage> studentNotFoundException(StudentNotFoundException ex, WebRequest request){
        ErrorMessage error = ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    public ResponseEntity<ErrorMessage> buildingNotFoundException(BuildingNotFoundException ex, WebRequest request){
        ErrorMessage error = ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }
}
