package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.hogwarts.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student add(Student student){

        logger.info("Метод add запущен");return studentRepository.save(student);
    }
    public Student get(long id){
        return studentRepository.getById(id);
    }
    public Student update(Student student){
        return studentRepository.save(student);
    }
    public void delete (long id){
        studentRepository.deleteById(id);
    }
    public Student getByAge (long age){
         return (Student) studentRepository.findByAgeBetween(age,age);
    }
    public Collection<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentNumber (){
        return studentRepository.getAllStudentNumber();
    }
}
// комент для пула