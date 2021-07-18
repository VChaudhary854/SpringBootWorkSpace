package com.example.MyFirstApp.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken!");
        }

        studentRepository.save(student);
    }

    public void deleteStudentByName(String name) {
        Optional<Student> studentOptional = studentRepository.findStudentByName(name);

        if (!studentOptional.isPresent()){
            throw new IllegalStateException("Student does not exist!");
        }

        studentRepository.delete(studentOptional.get());
    }

    public void deleteStudentById(Long id) {
        Boolean idExists = studentRepository.existsById(id);

        if (!idExists){
            throw new IllegalStateException("Student with id " + id + " does not exists!");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(" Student with id " + studentId + " does not exist!"));

        if(name != null && !Objects.equals(name, student.getName())){
            student.setName(name);
        }

        if(email != null && !Objects.equals(email, student.getEmail())){
            student.setEmail(email);
        }
    }
}
