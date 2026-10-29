package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(
            Arrays.asList(new Student(1, "Aadhi", "Java"),
            new Student(2, "Abi" , "Python")
    ));

    @GetMapping("/students")
    public List<Student> getStudent(){
        return students;
    }

    @GetMapping("/user/about")
    public String sampleOutput(){
        return "Welcome to the sample Output...";
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student){
        students.add(student);
    }
}
