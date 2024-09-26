package com.example.hogwarts.controller;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/Faculty")
@RestController
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }
    @PostMapping("/FacultyAdd")
    public Faculty add(@RequestBody Faculty faculty){
        return service.add(faculty);
    }
    @GetMapping("/FacultyGet")
    public Faculty get(@RequestParam long id){
        return  service.get(id);
    }
    @PutMapping("/FacultyUpdate")
    public Faculty update(@RequestBody Faculty faculty){
        return service.update(faculty);
    }
    @DeleteMapping("/FacultyDelete")
    public ResponseEntity delete (@RequestParam long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/FacultyGetAll")
    public ResponseEntity<Collection<Faculty>> getAllFaculty(){
        return ResponseEntity.ok(service.getAllFaculty());
    }
    @GetMapping("/FacultyGetColour")
    public ResponseEntity getByColourOrName(@RequestParam String colour, @RequestParam String name){
        if(name!= null && !name.isBlank()){
            return ResponseEntity.ok(service.findByColorOrName(name));
        }
        if(colour!= null && !colour.isBlank()){
            return ResponseEntity.ok(service.findByColorOrName(colour));
        }
        return ResponseEntity.ok(getAllFaculty());
    }

}
