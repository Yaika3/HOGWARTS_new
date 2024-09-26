package com.example.hogwarts.repositories;

import com.example.hogwarts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {

    public Collection<Student> findByAgeBetween(Long age, Long age2);
    @Query(value = "select COUNT(*) from student ", nativeQuery = true)
    List<Student> getAllStudentNumber();


    @Query(value = "select COUNT(*) from student ", nativeQuery = true)
    int getCount();
    @Query(value = "select AVG(age) from student ", nativeQuery = true)
    int getAverageAge();
    @Query(value = "select * from  student order by id desc limit 5 ", nativeQuery = true)
    List <Student> getLastStudents();



}
