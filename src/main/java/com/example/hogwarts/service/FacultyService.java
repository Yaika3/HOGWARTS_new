package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.hogwarts.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty){
        logger.info("Метод add запущен");
        return facultyRepository.save(faculty);
    }
    public Faculty get(long id){
        logger.info("Метод get запущен");
        return facultyRepository.getById(id);
    }
    public Faculty update(Faculty faculty){
        logger.info("Метод update запущен");
        return facultyRepository.save(faculty);
    }
    public void delete (long id){
        logger.info("Метод delete запущен");
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findByColorOrName(String part){
        logger.info("Метод findByColorOrName запущен");
        return facultyRepository.findByNameOrColourIgnoreCase(part,part);
    }
    public Collection<Faculty> getAllFaculty(){
        logger.info("Метод getAllFaculty запущен");
        return facultyRepository.findAll();
    }

    public String longestName(){
        logger.info("Метод longestName запущен");
       return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.naturalOrder())
               .orElse(null);
    }

}
