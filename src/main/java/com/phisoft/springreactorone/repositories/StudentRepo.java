package com.phisoft.springreactorone.repositories;

import com.phisoft.springreactorone.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepo {

    private static Map<Long, Student>studentMap=new HashMap<>();
    static {
        studentMap.put(1l,Student.builder()
        .id(1l)
        .name("Tony")
        .number(100)
        .build()); studentMap.put(2l,Student.builder()
                .id(2l)
                .name("Chinwe")
                .number(200)
                .build()); studentMap.put(3l,Student.builder()
                .id(3l)
                .name("Frank")
                .number(300)
                .build());studentMap.put(4l,Student.builder()
                .id(4l)
                .name("Ekwe")
                .number(400)
                .build());studentMap.put(5l,Student.builder()
                .id(5l)
                .name("Amo")
                .number(600)
                .build());

    }

    public Student getStudentById(Long id){
        return studentMap.get(id);
    }

    public Student getStudentByName(String name){
        Collection<Student> students =studentMap.values();
        for(Student student:students){
            if (student.getName().equalsIgnoreCase(name)){
                return student;
            }
        }
     return null;
    }

    public Student getStudentByNumber(int number){
        Collection<Student> students =studentMap.values();
        for(Student student:students){
            if (student.getNumber()==number){
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudents(){
        return new ArrayList<>(studentMap.values());
    }

    public boolean saveStudent(Student student){
        studentMap.put(student.getId(),student);
        return true;

    }
}
