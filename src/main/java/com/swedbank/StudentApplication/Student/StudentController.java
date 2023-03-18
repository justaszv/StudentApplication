package com.swedbank.StudentApplication.Student;


import com.swedbank.StudentApplication.Student.exeption.StudentNotFoundException;
import com.swedbank.StudentApplication.Student.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository repository;

    @GetMapping()
    public List<Student> all(){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) throws StudentNotFoundException {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setPersonalNumber(newStudent.getPersonalNumber());
        student.setName(newStudent.getName());
        student.setSurname(newStudent.getSurname());
        final var updatedStudent = repository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @PostMapping
    public void save(@RequestBody Student student){
        repository.save(student);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable final long id){
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)), HttpStatus.ACCEPTED);
    }



    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable final long id){
        repository.deleteById(id);
    }

    @GetMapping("/first")
    public Student getFirst() { return repository.getFirstStudent().get(0); }

    @GetMapping("/byname")
    public List<Student>  getByName(@RequestParam() String name) {return  repository.getByName(name);}
}