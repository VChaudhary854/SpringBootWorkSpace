package com.example.MyFirstApp.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController  {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public String registerStudent(@RequestBody Student student){
        studentService.addNewStudent(student);

        return "Added Student " + student.getName() + " successfully";
    }

    @DeleteMapping("deleteStudentByName")
    public String deleteStudent(@RequestBody String Name){
        studentService.deleteStudentByName(Name);

        return "Deleted Student " + Name + " successfully";
    }

    @DeleteMapping(path = "{studentId}")
    public String deleteStudent(
            @PathVariable("studentId") Long studentId){
        System.out.println("deleteStudent: id: " + studentId);
        studentService.deleteStudentById(studentId);

        return "Deleted Student with id " + studentId + " successfully";
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){

        studentService.updateStudent(studentId, name, email);

    }
}
