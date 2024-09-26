package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!Main")
public class StudentServiceNotMain {
    private StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentServiceNotMain(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student add(Student student){

        logger.info("Метод add запущен");return studentRepository.save(student);
    }
}
