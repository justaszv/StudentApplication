package com.swedbank.StudentApplication.Student.exeption;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id){
        super("Could not find student id: " + id);
    }
}
