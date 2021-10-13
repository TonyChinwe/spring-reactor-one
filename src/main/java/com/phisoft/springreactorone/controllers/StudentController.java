package com.phisoft.springreactorone.controllers;

import com.phisoft.springreactorone.entities.Student;
import com.phisoft.springreactorone.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/id/{id}")
    public Student getStudentById(@PathVariable("id") Long id){
     return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("student/name/{name}")
    public Student getStudentByName(@PathVariable("name") String name){
        return studentService.getStudentByName(name);
    }

    @PostMapping("/save")
    public boolean saveStudent(@RequestBody Student student){
      return studentService.saveStudent(student);
    }

}
