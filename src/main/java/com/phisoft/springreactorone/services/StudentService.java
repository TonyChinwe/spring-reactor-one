package com.phisoft.springreactorone.services;

import com.phisoft.springreactorone.entities.Student;
import com.phisoft.springreactorone.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student getStudentById(Long id){
        return studentRepo.getStudentById(id);
    }

    public Student getStudentByName(String name){
       return studentRepo.getStudentByName(name);
    }

    public Student getStudentByNumber(int number){
       return studentRepo.getStudentByNumber(number);
    }

    public List<Student> getAllStudents(){
        return studentRepo.getStudents();
    }

    public boolean saveStudent(Student student){
        if(studentRepo.saveStudent(student)){
            return true;
        }
        return false;

    }

}
