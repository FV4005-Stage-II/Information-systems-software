package com.example.POIS.repos;

import com.example.POIS.data.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepos extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);
    List<Student> findAll();


    ///void saveAll(List<Student> students);
}
