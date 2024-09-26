package com.example.hogwarts;

import com.example.hogwarts.controller.StudentController;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//Забыл ренейм сделать) Этот класс для теста студентов
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    StudentRepository studentRepository;
    @Test
    void contextLoads() throws Exception{
        Assertions.assertThat(studentController).isNotNull();
    }

//    @Test
//    void testPostStudent(){
//        Student result = restTemplate.postForEntity();
//        Assertions.assertThat(result.getName()).isEqualTo(1L);
//    }

    @Test
    public void testAddStudent()throws Exception{
        Student student = new Student();
        student.setAge(20L);
        student.setName(" Nick ");
        var actual = this.restTemplate.postForObject("http://localhost:" + port + "/student", student,Student.class);
    }

    @Test
    public void testGetStudent()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+ port+ "/student", String.class))
                .isNotNull();

    }

    @Test
    void testPutStudent()throws Exception{
        Student student = new Student();
        HttpEntity <Student> studentHttp = new HttpEntity<>(student);
        student.setAge(20L);
        student.setName(" Nick ");
        ResponseEntity<Student> studentResponseEntity = restTemplate.exchange(
                "http://localhost:"+ port + "/student",
                HttpMethod.PUT,
                studentHttp,
                Student.class);
        Assertions.assertThat(studentResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testPostStudent ()throws  Exception{
        Student student= new Student();
        student.setId(1L);
        student.setName("Lee");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost: " + port + "/student",student,String.class))
                .isNotNull();
    }

    @BeforeEach
    void setUp(){
        studentRepository.deleteAll();
    }

}
// комент для пула