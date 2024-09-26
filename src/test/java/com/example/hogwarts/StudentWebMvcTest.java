package com.example.hogwarts;


import com.example.hogwarts.controller.StudentController;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.StudentRepository;
import com.example.hogwarts.service.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static javax.swing.text.html.parser.DTDConstants.ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentController studentController;
    
    @SpyBean
    private StudentService studentService;


    @Test
    public void saveStudentTest() throws Exception{
        final String name = " Nick ";
        final long id = 1;
        final long age = 30;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name ", name);
        studentObject.put("age ", age);
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findAllById(List<T> findAllById(Iterable<ID> ids);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/student")
                .content(studentObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));


    }




}
// комент для пула