package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.example.hogwarts.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Profile("Main")
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
        logger.info("Метод get запущен");
        return studentRepository.getById(id);
    }
    public Student update(Student student){
        logger.info("Метод updata запущен");
        return studentRepository.save(student);
    }
    public void delete (long id){
        logger.info("Метод delete запущен");
        studentRepository.deleteById(id);
    }
    public Student getByAge (long age){
        logger.info("Метод getByAge запущен");
        return (Student) studentRepository.findByAgeBetween(age,age);
    }
    public Collection<Student> getAllStudent(){
        logger.info("Метод getAllStudent запущен");
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentNumber (){
        logger.info("Метод getAllStudentNumber запущен");
        return studentRepository.getAllStudentNumber();
    }

    public List<String> getNameStartsA (){
        logger.info("Метод getNameStartsA запущен");
        return studentRepository.
                findAll()
                .stream()
                .map(Student ::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());}

    public double getStudentAvgAge() {
        logger.info("Метод getStudentAvgAge запущен");
        return studentRepository.findAll()
                .stream()
                .mapToLong(Student::getAge)
                .average()
                .orElse(0d);
    }

    public int getAverageAge(){
        return studentRepository.getAverageAge();
    }
    public int getStudentCount(){
        return studentRepository.getCount();
    }
    public List <Student> getLastStudents(){
        return studentRepository.getLastStudents();
    }

    public void printUnsync(){
        var students = studentRepository.findAll();

        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() ->{
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();
        new Thread(() ->{
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
        new Thread(() ->{
            print(students.get(2));
            print(students.get(3));
        }).start();
        new Thread(() ->{
            print(students.get(4));
            print(students.get(5));
        }).start();
    }

    public synchronized void print (Student o){
            System.out.println(o.toString());
        }

    }


