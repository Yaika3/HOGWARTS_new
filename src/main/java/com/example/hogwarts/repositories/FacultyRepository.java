package com.example.hogwarts.repositories;

import com.example.hogwarts.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour);
}
