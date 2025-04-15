package com.example.GestionPae.service;

import com.example.GestionPae.model.Student;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.StudentRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    //List
    public List<Student> ListStudents(Long id) {
        return studentRepository.findByUserIdUser(id);
    }

    //Create
    public Student createStudent(Student student) {
        User school = userRepository.findById(student.getUser().getIdUser()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        student.setUser(school);

        return studentRepository.save(student);
    }

    //Update
    public Student updateStudent(Student newStudent) {
        return studentRepository.findById(newStudent.getIdStudent()).map(student -> {
            student.setName(newStudent.getName());
            student.setLastName(newStudent.getLastName());
            student.setGrade(newStudent.getGrade());

            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    //Delete
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Estudiante no encontrado");
        }
    }

}
