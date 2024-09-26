package com.example.hogwarts;

import com.example.hogwarts.controller.FacultyController;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.FacultyRepository;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyApplicationTests {
    @LocalServerPort
    private int port;
    
    @Autowired
    FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    FacultyRepository facultyRepository;
    @Test
    void contextLoads() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testAddFaculty()throws Exception{
        Faculty faculty = new Faculty();
        faculty.setColour(" Green ");
        faculty.setName(" Alex ");
        var actual = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty,Faculty.class);
    }

    @Test
    public void testGetFaculty()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+ port+ "/faculty", String.class))
                .isNotNull();

    }

    @Test
    void testPutStudent()throws Exception{
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> facultyHttp = new HttpEntity<>(faculty);
        faculty.setColour("Red");
        faculty.setName(" Bob ");
        ResponseEntity<Student> studentResponseEntity = restTemplate.exchange(
                "http://localhost:"+ port + "/student",
                HttpMethod.PUT,
                facultyHttp,
                Student.class);
        Assertions.assertThat(studentResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    public void testPostFaculty ()throws  Exception{
        Faculty faculty= new Faculty();
        faculty.setId(1L);
        faculty.setName("Lee");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost: " + port + "/faculty",faculty,String.class))
                .isNotNull();
    }

    @BeforeEach
    void setUp(){
        facultyRepository.deleteAll();
    }
}
// комент для пула