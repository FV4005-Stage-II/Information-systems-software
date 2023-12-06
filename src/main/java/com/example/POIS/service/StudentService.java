package com.example.POIS.service;


import com.example.POIS.data.Student;
import com.example.POIS.dto.StudentDto;
import com.example.POIS.repos.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepos studentRepos;

    public boolean saveStudent(StudentDto studentDto)  {
        Student student = new Student();
        student.setEmail(studentDto.getEmail());
        student.setName(studentDto.getName());
        student.setExam_scores(studentDto.getExam_scores());
        Student savedStudent = studentRepos.save(student);

        return savedStudent.getId() != null;
    }

    public List<Student> getStudent(String name) {
        return studentRepos.findByName(name);
    }
    public List<Student> getAllStudent() {
        return studentRepos.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepos.deleteById(id);
    }
}
